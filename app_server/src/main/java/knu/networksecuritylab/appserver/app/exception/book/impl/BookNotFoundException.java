package knu.networksecuritylab.appserver.app.exception.book.impl;

import knu.networksecuritylab.appserver.app.exception.book.BookException;
import knu.networksecuritylab.appserver.app.exception.book.BookErrorCode;

public class BookNotFoundException extends BookException {

    public BookNotFoundException() {
        super(BookErrorCode.BOOK_NOT_FOUND);
    }
}
