package knu.networksecuritylab.appserver.exception.book;

public class BookDuplicateException extends BookException {

    public BookDuplicateException(BookErrorCode bookErrorCode) {
        super(bookErrorCode);
    }
}
