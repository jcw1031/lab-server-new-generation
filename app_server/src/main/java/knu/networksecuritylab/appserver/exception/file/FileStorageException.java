package knu.networksecuritylab.appserver.exception.file;

public class FileStorageException extends FileException {

    public FileStorageException(FileErrorCode fileErrorCode) {
        super(fileErrorCode);
    }
}
