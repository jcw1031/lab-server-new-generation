package knu.networksecuritylab.appserver.app.service.file;

import knu.networksecuritylab.appserver.app.entity.book.Image;
import knu.networksecuritylab.appserver.app.exception.file.impl.FileStorageException;
import knu.networksecuritylab.appserver.app.exception.file.impl.ImageNotReadable;
import knu.networksecuritylab.appserver.app.exception.file.impl.InvalidFileExtensionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
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
    //    private final String STORAGE_PATH = "C:\\Users\\Administrator\\lab-service\\lab_service_image";
    private final String STORAGE_PATH = "/Users/uknow/lab_service_images";

    @PostConstruct
    private void init() {
        this.fileLocation = Paths.get(STORAGE_PATH).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (IOException e) {
            throw new FileStorageException();
        }
    }

    @Override
    public List<Image> multipartFilesStoreAndConvertToImages(final List<MultipartFile> multipartFiles)
            throws IOException {
        List<Image> images = new ArrayList<>();
        if (CollectionUtils.isEmpty(multipartFiles)) {
            return images;
        }

        for (MultipartFile multipartFile : multipartFiles) {
            String storedFileName = multipartFileStore(multipartFile);

            Image image = convertToImage(multipartFile, storedFileName);
            images.add(image);
        }

        return images;
    }

    private static Image convertToImage(MultipartFile multipartFile, String fileName) {
        long fileSize = multipartFile.getSize();
        Image image = Image.builder()
                .imageName(fileName)
                .imageSize(fileSize)
                .build();
        return image;
    }

    private String multipartFileStore(MultipartFile multipartFile) throws IOException {
        String extension = getFileExtension(multipartFile);
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + extension;
        File file = new File(fileLocation + File.separator + fileName);
        multipartFile.transferTo(file);

        file.setWritable(true);
        file.setReadable(true);
        return fileName;
    }

    private String getFileExtension(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        if (contentType.contains("image/jpeg")) {
            return ".jpg";
        } else if (contentType.contains("image/png")) {
            return ".png";
        }

        throw new InvalidFileExtensionException();
    }

    @Override
    public byte[] imageConvertToBytes(Image image) {
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(STORAGE_PATH + File.separator + image.getImageName());
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new ImageNotReadable();
        }
    }

    @Override
    public void removeImages(List<String> imageNameList) {
        for (String imageName : imageNameList) {
            try {
                Files.delete(Paths.get(STORAGE_PATH + File.separator + imageName));
            } catch (IOException ignored) {
            }
        }
    }
}
