package knu.networksecuritylab.appserver.app.exception.file.impl;

import knu.networksecuritylab.appserver.app.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.app.exception.file.FileException;

public class InvalidFileExtensionException extends FileException {

    public InvalidFileExtensionException() {
        super(FileErrorCode.INVALID_FILE_EXTENSION);
    }
}
