package knu.networksecuritylab.appserver.app.exception.file.impl;

import knu.networksecuritylab.appserver.app.exception.file.FileException;
import knu.networksecuritylab.appserver.app.exception.file.FileErrorCode;

public class ImageNotReadable extends FileException {

    public ImageNotReadable() {
        super(FileErrorCode.IMAGE_NOT_READABLE);
    }
}
