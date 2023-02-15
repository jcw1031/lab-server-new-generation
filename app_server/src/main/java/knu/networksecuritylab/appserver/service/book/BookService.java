package knu.networksecuritylab.appserver.service.book;

import knu.networksecuritylab.appserver.controller.book.dto.BookInfoResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookListResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;

import java.util.List;

public interface BookService {

    Long registerBook(final BookRegisterRequestDto bookRegisterRequestDto);

    List<BookListResponseDto> bookList();

    BookInfoResponseDto bookInfo(final Long bookId);

    List<BookListResponseDto> bookSearch(String keyword);
}
