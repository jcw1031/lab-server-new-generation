package knu.networksecuritylab.appserver.service.book.impl;

import knu.networksecuritylab.appserver.controller.book.dto.BookInfoResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookListResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import knu.networksecuritylab.appserver.entity.book.Book;
import knu.networksecuritylab.appserver.entity.book.BookTag;
import knu.networksecuritylab.appserver.entity.book.Image;
import knu.networksecuritylab.appserver.entity.book.Tag;
import knu.networksecuritylab.appserver.exception.book.impl.BookDuplicateException;
import knu.networksecuritylab.appserver.exception.book.impl.BookNotFoundException;
import knu.networksecuritylab.appserver.repository.book.BookRepository;
import knu.networksecuritylab.appserver.repository.book.BookTagRepository;
import knu.networksecuritylab.appserver.service.book.BookService;
import knu.networksecuritylab.appserver.service.book.ImageService;
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

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicBookService implements BookService {

    private final BookRepository bookRepository;
    private final TagService tagService;
    private final FileService fileService;
    private final ImageService imageService;
    private final BookTagRepository bookTagRepository;

    @Override
    @Transactional
    public Long registerBook(
            final List<MultipartFile> files,
            final BookRegisterRequestDto bookRegisterRequestDto) throws IOException {
        log.info("tagList = {}", bookRegisterRequestDto.getBookTagList());
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
                        throw new BookDuplicateException();
                    }
                });

        return Book.from(bookRegisterRequestDto);
    }

    private void bookTagging(final List<Tag> tags, final Book book) {
        tags.forEach(tag -> bookTagRepository.save(BookTag.of(book, tag)));
    }

    @Override
    public List<BookListResponseDto> bookList() {
        List<BookListResponseDto> bookList = new ArrayList<>();
        bookRepository.findBookRandomList()
                .forEach(book -> bookList.add(book.toBookListDto()));
        return bookList;
    }

    @Override
    public BookInfoResponseDto bookInfo(final Long bookId) {
        List<Long> imageList = new ArrayList<>();
        Book book = bookRepository.findByIdIfImagesExists(bookId).orElse(null);
        if (book != null) {
            imageList = bookImagesToImageIdList(book.getImages());
        }

        book = bookRepository.findByIdWithBookTags(bookId)
                .orElseThrow(() -> new BookNotFoundException());
        List<String> tagList = tagService.bookTagsToTagNameList(book.getBookTags());

        return book.toBookInfoDto(tagList, imageList);
    }

    private List<Long> bookImagesToImageIdList(final List<Image> images) {
        List<Long> imageList = new ArrayList<>();
        for (Image image : images) {
            imageList.add(image.getId());
        }
        return imageList;
    }

    @Override
    public List<BookListResponseDto> bookSearch(final String keyword) {
        List<BookListResponseDto> bookList = new ArrayList<>();
        bookRepository.searchBookByName(keyword)
                .forEach(book -> bookList.add(book.toBookListDto()));
        return bookList;
    }

    @Override
    @Transactional
    public void removeBook(Long bookId) {
        bookRepository.findByIdIfImagesExists(bookId)
                .ifPresent(book -> {
                    List<String> imageNameList = imageService.imagesToImageNameList(book.getImages());
                    fileService.removeImages(imageNameList);
                });
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException());
        bookRepository.delete(book);
    }
}
