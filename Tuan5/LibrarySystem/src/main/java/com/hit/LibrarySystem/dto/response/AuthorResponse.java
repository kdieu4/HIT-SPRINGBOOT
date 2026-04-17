package com.hit.LibrarySystem.dto.response;

import com.hit.LibrarySystem.entity.Author;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Schema(description = "Thong tin tac gia tra ve")
public class AuthorResponse {
    @Schema(description = "ID tac gia", example = "1")
    private Long id;

    @Schema(description = "Ten day du", example = "Nguyen Van An")
    private String name;

    @Schema(description = "Email", example = "a@gmail.com")
    private String email;

    @Schema(description = "So dien thoai", example = "0912345678")
    private String phone;

    @Schema(description = "Thoi diem tao")
    private LocalDateTime createdAt;

    public static AuthorResponse from(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .phone(author.getPhone())
                .createdAt(author.getCreatedAt())
                .build();
    }
}
