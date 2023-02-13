package knu.networksecuritylab.appserver.controller.book.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookListResponseDto {

    private String bookName;
    private String bookAuthor;

    @Builder
    public BookListResponseDto(String bookName, String bookAuthor) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }
}
