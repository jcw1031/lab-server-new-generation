package knu.networksecuritylab.appserver.exception.file;

public class ImageNotFoundException extends FileException {

    public ImageNotFoundException(FileErrorCode fileErrorCode) {
        super(fileErrorCode);
    }
}
