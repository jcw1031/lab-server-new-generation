package knu.networksecuritylab.appserver.service.book;

import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import knu.networksecuritylab.appserver.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicBookService implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Long registerBook(BookRegisterRequestDto bookRegisterRequestDto) {
        return null;
    }
}
