package knu.networksecuritylab.appserver.exception.book.impl;

import knu.networksecuritylab.appserver.exception.book.BookErrorCode;
import knu.networksecuritylab.appserver.exception.book.BookException;

public class BookDuplicateException extends BookException {

    public BookDuplicateException() {
        super(BookErrorCode.BOOK_DUPLICATE);
    }
}
