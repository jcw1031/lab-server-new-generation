package knu.networksecuritylab.appserver.service.file;

import knu.networksecuritylab.appserver.entity.book.Image;
import knu.networksecuritylab.appserver.exception.book.InvalidFileExtensionException;
import knu.networksecuritylab.appserver.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.exception.file.FileStorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ImageFileService implements FileService {

    private Path fileLocation;
//    private final String LOCATION = "C:\\Users\\jcw00\\lab_service_images";
    private final String LOCATION = "/Users/jcw/lab_service_images";

    @PostConstruct
    private void init() {
        this.fileLocation = Paths.get(LOCATION).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (IOException e) {
            throw new FileStorageException(FileErrorCode.CAN_NOT_CREATE_DIRECTORY);
        }
    }

    @Override
    public List<Image> parseFiles(final List<MultipartFile> files) throws IOException {
        log.info("ImageFileService.parseFiles() start");
        List<Image> images = new ArrayList<>();
        if (CollectionUtils.isEmpty(files)) {
            log.info("files is empty");
            return images;
        }

        for (MultipartFile multipartFile : files) {
            log.info("MultipartFile List Parsing Start");
            if (multipartFile.isEmpty()) {
                break;
            }

            String extension = getFileExtension(multipartFile);

            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + extension;
            File file = new File(fileLocation + File.separator + fileName);
            multipartFile.transferTo(file);

            file.setWritable(true);
            file.setReadable(true);

            long fileSize = multipartFile.getSize();
            Image image = Image.builder()
                    .imageName(fileName)
                    .imageSize(fileSize)
                    .build();
            images.add(image);
        }

        log.info("MultipartFile List Parsing End");
        log.info("images size = {}", images.size());
        return images;
    }

    private String getFileExtension(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        if (contentType.contains("image/jpeg")) {
            return ".jpg";
        } else if (contentType.contains("image/png")) {
            return ".png";
        }

        throw new InvalidFileExtensionException(FileErrorCode.INVALID_FILE_EXTENSION);
    }
}
