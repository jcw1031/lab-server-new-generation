package knu.networksecuritylab.appserver.exception.book.impl;

import knu.networksecuritylab.appserver.exception.book.BookErrorCode;
import knu.networksecuritylab.appserver.exception.book.BookException;

public class BookNotFoundException extends BookException {

    public BookNotFoundException(BookErrorCode bookErrorCode) {
        super(bookErrorCode);
    }
}
