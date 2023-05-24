package knu.networksecuritylab.appserver.app.exception.file.impl;

import knu.networksecuritylab.appserver.app.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.app.exception.file.FileException;

public class ImageNotFoundException extends FileException {

    public ImageNotFoundException() {
        super(FileErrorCode.IMAGE_NOT_FOUND);
    }
}
