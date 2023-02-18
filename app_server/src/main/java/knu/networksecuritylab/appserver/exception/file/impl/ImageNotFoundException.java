package knu.networksecuritylab.appserver.exception.file.impl;

import knu.networksecuritylab.appserver.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.exception.file.FileException;

public class ImageNotFoundException extends FileException {

    public ImageNotFoundException(FileErrorCode fileErrorCode) {
        super(fileErrorCode);
    }
}
