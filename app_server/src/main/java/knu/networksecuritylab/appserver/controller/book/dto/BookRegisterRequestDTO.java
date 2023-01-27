package knu.networksecuritylab.appserver.controller.book.dto;

import knu.networksecuritylab.appserver.entity.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookRegisterRequestDTO {

    @NotBlank(message = "책 이름은 비어있을 수 없습니다.")
    private String bookName;
    private String bookAuthor;
    @Enumerated(value = EnumType.STRING)
    private Category bookCategory;
    private String bookPublisher;
    @PositiveOrZero(message = "책 재고는 0 또는 양수이어야 합니다.")
    private int bookStock;
}
