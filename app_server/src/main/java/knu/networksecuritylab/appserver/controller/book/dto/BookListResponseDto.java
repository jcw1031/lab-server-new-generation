package knu.networksecuritylab.appserver.controller.book.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookListResponseDto {

    private Long id;
    private String bookName;
    private String bookAuthor;
    private List<String> bookTagList;

    @Builder
    public BookListResponseDto(Long id, String bookName, String bookAuthor, List<String> bookTagList) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookTagList = bookTagList;
    }
}
