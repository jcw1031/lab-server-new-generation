package knu.networksecuritylab.appserver.entity.book;

import knu.networksecuritylab.appserver.controller.book.dto.BookInfoResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookListResponseDto;
import knu.networksecuritylab.appserver.controller.book.dto.BookRegisterRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @NotBlank(message = "책 이름은 비어있을 수 없습니다.")
    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    @PositiveOrZero(message = "책 재고는 0 또는 양수이어야 합니다.")
    private int bookStock;

    @OneToMany(mappedBy = "book")
    private final List<BookTag> bookTags = new ArrayList<>();

    @Builder
    public Book(String bookName, String bookAuthor, String bookPublisher, int bookStock) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookStock = bookStock;
    }

    public static Book of(BookRegisterRequestDto bookRegisterRequestDto) {
        return Book.builder()
                .bookName(bookRegisterRequestDto.getBookName())
                .bookAuthor(bookRegisterRequestDto.getBookAuthor())
                .bookPublisher(bookRegisterRequestDto.getBookPublisher())
                .bookStock(bookRegisterRequestDto.getBookStock())
                .build();
    }

    public BookListResponseDto toBookListDto() {
        return BookListResponseDto.builder()
                .id(this.id)
                .bookName(this.bookName)
                .bookAuthor(this.bookAuthor)
                .build();
    }

    public BookInfoResponseDto toBookInfoDto(final List<String> tags) {
        return BookInfoResponseDto.builder()
                .bookName(this.bookName)
                .bookAuthor(this.bookAuthor)
                .bookPublisher(this.bookPublisher)
                .bookStock(this.bookStock)
                .bookTags(tags)
                .build();
    }
}
