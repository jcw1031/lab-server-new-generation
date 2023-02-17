package knu.networksecuritylab.appserver.service.book.impl;

import knu.networksecuritylab.appserver.controller.book.dto.BookInfoResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookListResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import knu.networksecuritylab.appserver.entity.book.Book;
import knu.networksecuritylab.appserver.entity.book.BookTag;
import knu.networksecuritylab.appserver.entity.book.Image;
import knu.networksecuritylab.appserver.entity.book.Tag;
import knu.networksecuritylab.appserver.exception.book.impl.BookDuplicateException;
import knu.networksecuritylab.appserver.exception.book.BookErrorCode;
import knu.networksecuritylab.appserver.exception.book.impl.BookNotFoundException;
import knu.networksecuritylab.appserver.repository.book.BookRepository;
import knu.networksecuritylab.appserver.repository.book.BookTagRepository;
import knu.networksecuritylab.appserver.service.book.BookService;
import knu.networksecuritylab.appserver.service.book.TagService;
import knu.networksecuritylab.appserver.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicBookService implements BookService {

    private final BookRepository bookRepository;
    private final TagService tagService;
    private final FileService fileService;
    private final BookTagRepository bookTagRepository;

    @Override
    @Transactional
    public Long registerBook(
            final List<MultipartFile> files,
            final BookRegisterRequestDto bookRegisterRequestDto) throws IOException {
        List<Tag> tags = tagService.tagArrangement(bookRegisterRequestDto.getBookTagList());

        Book book = checkDuplicateBook(bookRegisterRequestDto);
        fileService.multipartFilesStoreAndConvertToImages(files).forEach(image -> image.setBook(book));

        bookRepository.save(book); // cascade 설정으로 image도 모두 저장
        bookTagging(tags, book);
        return book.getId();
    }

    private Book checkDuplicateBook(final BookRegisterRequestDto bookRegisterRequestDto) {
        bookRepository.findByBookName(bookRegisterRequestDto.getBookName())
                .ifPresent(book -> {
                    if (book.getBookAuthor().equals(bookRegisterRequestDto.getBookAuthor())) {
                        throw new BookDuplicateException(BookErrorCode.BOOK_DUPLICATE);
                    }
                });

        return Book.of(bookRegisterRequestDto);
    }

    private void bookTagging(final List<Tag> tags, final Book book) {
        tags.forEach(tag -> {
            Optional<BookTag> findBookTag = bookTagRepository.findByBookAndTag(book, tag);
            if (findBookTag.isEmpty()) {
                bookTagRepository.save(new BookTag(book, tag));
            }
        });
    }

    @Override
    public List<BookListResponseDto> bookList() {
        List<BookListResponseDto> bookList = new ArrayList<>();
        bookRepository.findBookRandomList()
                .forEach(book -> bookList.add(book.toBookListDto()));
        return bookList;
    }

    @Override
    public BookInfoResponseDto bookInfo(Long bookId) {
        List<Long> imageList = new ArrayList<>();
        Book book = bookRepository.findByIdIfImagesExists(bookId).orElse(null);
        if (book != null) {
            imageList = bookImagesToImageIdList(book.getImages());
        }

        book = bookRepository.findByIdWithBookTags(bookId)
                .orElseThrow(() -> new BookNotFoundException(BookErrorCode.BOOK_NOT_FOUND));
        List<String> tagList = tagService.bookTagsToTagNameList(book.getBookTags());

        return book.toBookInfoDto(tagList, imageList);
    }

    private List<Long> bookImagesToImageIdList(List<Image> images) {
        List<Long> imageList = new ArrayList<>();
        for (Image image : images) {
            imageList.add(image.getId());
        }
        return imageList;
    }

    @Override
    public List<BookListResponseDto> bookSearch(String keyword) {
        List<BookListResponseDto> bookList = new ArrayList<>();
        bookRepository.searchBookByName(keyword)
                .forEach(book -> bookList.add(book.toBookListDto()));
        return bookList;
    }
}
