package knu.networksecuritylab.appserver.service.book;

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
}
