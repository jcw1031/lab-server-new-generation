package knu.networksecuritylab.appserver.controller.book.dto;

import knu.networksecuritylab.appserver.entity.book.BookTag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInfoResponseDto {

    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private int bookStock;
    private final List<String> tags = new ArrayList<>();

    @Builder
    public BookInfoResponseDto(String bookName, String bookAuthor, String bookPublisher, int bookStock) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookStock = bookStock;
    }

    public void updateTags(List<BookTag> bookTagList) {
        bookTagList.forEach(bookTag -> this.tags.add(
                bookTag.getTag().getTagName())
        );
    }
}
