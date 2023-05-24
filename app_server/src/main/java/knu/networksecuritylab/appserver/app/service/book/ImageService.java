package knu.networksecuritylab.appserver.app.service.book;

import knu.networksecuritylab.appserver.app.entity.book.Image;

import java.util.List;

public interface ImageService {

    byte[] bookImage(final Long imageId);

    List<String> imagesToImageNameList(List<Image> images);
}
