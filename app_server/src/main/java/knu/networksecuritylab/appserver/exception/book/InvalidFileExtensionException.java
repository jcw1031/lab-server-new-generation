package knu.networksecuritylab.appserver.exception.book;

import knu.networksecuritylab.appserver.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.exception.file.FileException;

public class InvalidFileExtensionException extends FileException {

    public InvalidFileExtensionException(FileErrorCode fileErrorCode) {
        super(fileErrorCode);
    }
}
