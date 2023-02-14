package knu.networksecuritylab.appserver.controller.book.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookListResponseDto {

    private Long id;
    private String bookName;
    private String bookAuthor;

    @Builder
    public BookListResponseDto(Long id, String bookName, String bookAuthor) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }
}
