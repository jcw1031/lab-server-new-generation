package knu.networksecuritylab.appserver.service.book;

import knu.networksecuritylab.appserver.controller.book.dto.BookListResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import knu.networksecuritylab.appserver.entity.book.Book;
import knu.networksecuritylab.appserver.entity.book.BookTag;
import knu.networksecuritylab.appserver.entity.book.Tag;
import knu.networksecuritylab.appserver.exception.BookDuplicateException;
import knu.networksecuritylab.appserver.exception.BookErrorCode;
import knu.networksecuritylab.appserver.repository.book.BookRepository;
import knu.networksecuritylab.appserver.repository.book.BookTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicBookService implements BookService {

    private final BookRepository bookRepository;
    private final TagService tagService;
    private final BookTagRepository bookTagRepository;

    @Override
    @Transactional
    public Long registerBook(final BookRegisterRequestDto bookRegisterRequestDto) {
        List<Tag> tagList = tagService.tagArrangement(bookRegisterRequestDto.getBookTags());

        Book book = checkDuplicateBook(bookRegisterRequestDto);
        bookRepository.save(book);
        bookTagging(tagList, book);
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

    private void bookTagging(final List<Tag> tagList, final Book book) {
        tagList.forEach(tag -> bookTagRepository.save(new BookTag(book, tag)));
    }

    @Override
    @Transactional
    public List<BookListResponseDto> bookList() {
        List<BookListResponseDto> bookList = new ArrayList<>();
        bookRepository.findBookRandomList()
                .forEach(book -> bookList.add(book.toBookListDto()));
        return bookList;
    }
}
