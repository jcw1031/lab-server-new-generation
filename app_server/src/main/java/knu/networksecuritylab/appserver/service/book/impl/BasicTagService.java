package knu.networksecuritylab.appserver.service.book.impl;

import knu.networksecuritylab.appserver.entity.book.BookTag;
import knu.networksecuritylab.appserver.entity.book.Tag;
import knu.networksecuritylab.appserver.repository.book.TagRepository;
import knu.networksecuritylab.appserver.service.book.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicTagService implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<String> tagList() {
        List<Tag> tags = tagRepository.findAll();
        List<String> tagList = new ArrayList<>();
        for (Tag tag : tags) {
            tagList.add(tag.getTagName());
        }

        return tagList;
    }

    @Override
    public List<Tag> tagArrangement(final List<String> tagList) {
        List<Tag> tags = new ArrayList<>();
        tagList.forEach(tagName -> {
            Tag tag = tagRepository.findByTagName(tagName)
                    .orElseGet(() -> tagRepository.save(Tag.from(tagName)));
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        });
        return tags;
    }

    @Override
    public List<String> bookTagsToTagNameList(final List<BookTag> bookTags) {
        List<String> tagList = new ArrayList<>();
        bookTags.forEach(bookTag -> {
            String tagName = bookTag.getTag().getTagName();
            tagList.add(tagName);
        });
        return tagList;
    }
}
