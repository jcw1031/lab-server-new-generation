package knu.networksecuritylab.appserver.service.file;

import knu.networksecuritylab.appserver.entity.book.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<Image> parseFiles(List<MultipartFile> images) throws IOException;
}
