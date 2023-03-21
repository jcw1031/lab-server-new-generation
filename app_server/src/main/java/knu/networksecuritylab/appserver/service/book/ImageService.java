package knu.networksecuritylab.appserver.service.book;

import knu.networksecuritylab.appserver.entity.book.Image;

import java.util.List;

public interface ImageService {

    byte[] bookImage(final Long imageId);

    List<String> imagesToImageNameList(List<Image> images);
}
