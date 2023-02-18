package knu.networksecuritylab.appserver.exception.file.impl;

import knu.networksecuritylab.appserver.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.exception.file.FileException;

public class ImageNotReadable extends FileException {

    public ImageNotReadable() {
        super(FileErrorCode.IMAGE_NOT_READABLE);
    }
}
