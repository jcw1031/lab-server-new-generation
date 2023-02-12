package knu.networksecuritylab.appserver.service.book;

import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;

public interface BookService {

    Long registerBook(final BookRegisterRequestDto bookRegisterRequestDto);
}
