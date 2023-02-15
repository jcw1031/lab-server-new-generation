package knu.networksecuritylab.appserver.service.book;

import knu.networksecuritylab.appserver.entity.book.BookTag;
import knu.networksecuritylab.appserver.entity.book.Tag;
import knu.networksecuritylab.appserver.repository.book.TagRepository;
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
    public List<Tag> tagArrangement(final List<String> tags) {
        List<Tag> tagList = new ArrayList<>();
        tags.forEach(tagName -> {
            Tag tag = tagRepository.findByTagName(tagName).orElseGet(() ->
                    tagRepository.save(new Tag(tagName))
            );
            tagList.add(tag);
        });
        return tagList;
    }

    @Override
    public List<String> listConvertBookTagToString(List<BookTag> bookTags) {
        List<String> tags = new ArrayList<>();
        bookTags.forEach(bookTag -> {
            String tagName = bookTag.getTag().getTagName();
            tags.add(tagName);
        });
        return tags;
    }
}
