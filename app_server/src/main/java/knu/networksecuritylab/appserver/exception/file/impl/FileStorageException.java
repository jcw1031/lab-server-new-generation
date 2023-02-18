package knu.networksecuritylab.appserver.exception.file.impl;

import knu.networksecuritylab.appserver.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.exception.file.FileException;

public class FileStorageException extends FileException {

    public FileStorageException(FileErrorCode fileErrorCode) {
        super(fileErrorCode);
    }
}
