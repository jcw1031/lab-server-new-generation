package knu.networksecuritylab.appserver.service.book.impl;

import knu.networksecuritylab.appserver.entity.book.Image;
import knu.networksecuritylab.appserver.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.exception.file.impl.ImageNotFoundException;
import knu.networksecuritylab.appserver.repository.book.ImageRepository;
import knu.networksecuritylab.appserver.service.book.ImageService;
import knu.networksecuritylab.appserver.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicImageService implements ImageService {

    private final ImageRepository imageRepository;
    private final FileService fileService;

    @Override
    public byte[] bookImage(final Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() ->
                new ImageNotFoundException(FileErrorCode.IMAGE_NOT_FOUND));
        return fileService.imageConvertToBytes(image);
    }
}
