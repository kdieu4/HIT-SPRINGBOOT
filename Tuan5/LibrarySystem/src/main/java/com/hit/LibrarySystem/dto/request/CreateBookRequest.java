package com.hit.LibrarySystem.dto.request;

import com.hit.LibrarySystem.entity.BookStatus;
import com.hit.LibrarySystem.entity.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {
    @NotBlank(message = "Ten sach khong duoc de trong")
    @Size(min = 2, max = 200, message = "Ten sach phai tu 2 den 200 ky tu")
    private String title;

    @NotBlank(message = "Ma ISBN khong duoc de trong")
    private String isbn;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category khong duoc de trong")
    private Category category;

    @NotNull(message = "So luong sach khong duoc de trong")
    @Min(value = 1, message = "So luong sach phai toi thieu la 1")
    private Integer totalCopies;

//    @NotNull(message = "So luong sach co san khong duoc de trong")
//    @Positive(message = "So luong sach co san phai lon hon 0")
//    private Integer availableCopies;

    private Integer publishYear;

    @NotNull(message = "Ma tac gia khong duoc de trong")
    private Long authorId;
}
