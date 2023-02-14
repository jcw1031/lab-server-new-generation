package knu.networksecuritylab.appserver.exception.book;

public class BookNotFoundException extends BookException {

    public BookNotFoundException(BookErrorCode bookErrorCode) {
        super(bookErrorCode);
    }
}
