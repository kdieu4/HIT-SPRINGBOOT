package com.hit.LibrarySystem.controller;

import com.hit.LibrarySystem.dto.request.CreateAuthorRequest;
import com.hit.LibrarySystem.dto.request.UpdateAuthorRequest;
import com.hit.LibrarySystem.dto.response.AuthorResponse;
import com.hit.LibrarySystem.dto.response.ApiResponse;
import com.hit.LibrarySystem.entity.Book;
import com.hit.LibrarySystem.service.AuthorService;
import com.hit.LibrarySystem.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
//@Tag 	Controller class 	Nhóm API trong Swagger UI
@Tag(name = "Author Management", description = "API quản lý tác giả")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    //    GET 	/api/authors 	Danh sách tác giả (phân trang)
    @Operation(summary = "Lay danh sach tac gia co phan trang")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Thành công")
    })
    @GetMapping
    public ResponseEntity<ApiResponse<Page<AuthorResponse>>> getAllAuthors(
            @Parameter(description = "So trang, bat dau tu 0", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "So phan tu moi trang", example = "0")
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<AuthorResponse> result = authorService.findAll(
                PageRequest.of(page, size, Sort.by("createdAt").descending()));
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //    POST 	/api/authors 	Tạo tác giả mới
    @Operation(summary = "Tao tac gia moi")
    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponse>> createAuthor(
            @Valid
            @RequestBody CreateAuthorRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(authorService.create(request)));
    }

    //    GET 	/api/authors/{id} 	Chi tiết 1 tác giả
    @Operation(summary = "Lay thong tin tac gia theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> getAuthorByID(
            @Parameter(description = "ID tac gia", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(ApiResponse.success(authorService.findById(id)));
    }

    //    PUT 	/api/authors/{id} 	Cập nhật tác giả
    @Operation(summary = "Cap nhat thong tin tac gia theo ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAuthorRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Cap nhat thanh cong", authorService.update(id, request)));
    }

    //    DELETE 	/api/authors/{id} 	Xóa tác giả
    @Operation(summary = "Xoa tac gia theo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> delete(
            @PathVariable Long id
    ) {
        authorService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Xoa thanh cong", null));
    }

    //    GET 	/api/authors/{id}/books 	Danh sách sách của tác giả
    @Operation(summary = "Lay danh sach sach cua tac gia theo ID")
    @GetMapping("/{id}/books")
    public ResponseEntity<ApiResponse<Page<Book>>> getBooksByAuthorID(
            @PathVariable Long authorId,
            @Parameter(description = "So trang, bat dau tu 0", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "So phan tu moi trang", example = "0")
            @RequestParam(defaultValue = "10") int size,
            Pageable pageable) {
        Page<Book> books = bookService.getBooksByAuthor(
                authorId,
                PageRequest.of(size, page, Sort.by("createdAt").descending()));
        return ResponseEntity.ok(ApiResponse.success(books));
    }
}
