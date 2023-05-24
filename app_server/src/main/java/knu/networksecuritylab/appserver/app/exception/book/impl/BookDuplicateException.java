package knu.networksecuritylab.appserver.app.exception.book.impl;

import knu.networksecuritylab.appserver.app.exception.book.BookException;
import knu.networksecuritylab.appserver.app.exception.book.BookErrorCode;

public class BookDuplicateException extends BookException {

    public BookDuplicateException() {
        super(BookErrorCode.BOOK_DUPLICATE);
    }
}
