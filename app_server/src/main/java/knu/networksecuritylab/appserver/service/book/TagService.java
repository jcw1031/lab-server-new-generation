package knu.networksecuritylab.appserver.service.book;

import knu.networksecuritylab.appserver.entity.book.BookTag;
import knu.networksecuritylab.appserver.entity.book.Tag;

import java.util.List;

public interface TagService {

    List<String> tagList();

    List<Tag> tagArrangement(List<String> tagList);

    List<String> bookTagsToTagNameList(List<BookTag> bookTags);
}
