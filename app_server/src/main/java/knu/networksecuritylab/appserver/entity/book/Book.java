package knu.networksecuritylab.appserver.entity.book;

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
@NoArgsConstructor
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
    public Book(Long id, String bookName, String bookAuthor, String bookPublisher, int bookStock) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookStock = bookStock;
    }
}
