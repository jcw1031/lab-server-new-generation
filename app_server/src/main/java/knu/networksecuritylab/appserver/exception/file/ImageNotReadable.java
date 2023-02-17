package knu.networksecuritylab.appserver.exception.file;

public class ImageNotReadable extends FileException {

    public ImageNotReadable(FileErrorCode fileErrorCode) {
        super(fileErrorCode);
    }
}
