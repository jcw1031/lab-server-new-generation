package knu.networksecuritylab.appserver.app.service.book;

import knu.networksecuritylab.appserver.app.entity.book.BookTag;
import knu.networksecuritylab.appserver.app.entity.book.Tag;

import java.util.List;

public interface TagService {

    List<String> tagList();

    List<Tag> tagArrangement(List<String> tagList);

    List<String> bookTagsToTagNameList(List<BookTag> bookTags);
}
