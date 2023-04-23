package knu.networksecuritylab.appserver.entity.book;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    private BookTag(Book book, Tag tag) {
        this.book = book;
        this.tag = tag;
    }

    public static BookTag of(Book book, Tag tag) {
        BookTag bookTag = BookTag.builder()
                .book(book)
                .tag(tag)
                .build();
        book.getBookTags().add(bookTag);
        tag.getBookTags().add(bookTag);
        return bookTag;
    }
}
