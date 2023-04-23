package knu.networksecuritylab.appserver.service.book.impl;

import knu.networksecuritylab.appserver.entity.book.Image;
import knu.networksecuritylab.appserver.exception.file.impl.ImageNotFoundException;
import knu.networksecuritylab.appserver.repository.book.ImageRepository;
import knu.networksecuritylab.appserver.service.book.ImageService;
import knu.networksecuritylab.appserver.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicImageService implements ImageService {

    private final ImageRepository imageRepository;
    private final FileService fileService;

    @Override
    public byte[] bookImage(final Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        return fileService.imageConvertToBytes(image);
    }

    @Override
    public List<String> imagesToImageNameList(List<Image> images) {
        List<String> imageNameList = new ArrayList<>();
        for (Image image : images) {
            imageNameList.add(image.getImageName());
        }
        return imageNameList;
    }
}
