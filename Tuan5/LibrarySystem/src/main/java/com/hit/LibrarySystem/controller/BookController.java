package com.hit.LibrarySystem.controller;

import com.hit.LibrarySystem.dto.request.CreateBookRequest;
import com.hit.LibrarySystem.dto.request.UpdateAuthorRequest;
import com.hit.LibrarySystem.dto.response.ApiResponse;
import com.hit.LibrarySystem.entity.Book;
import com.hit.LibrarySystem.entity.Category;
import com.hit.LibrarySystem.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Tag(name = "Book Management", description = "API quan ly sach")
public class BookController {
    private final BookService bookService;

    //    POST 	/api/books 	Thêm sách mới (gắn với Author qua authorId)
    @Operation(summary = "Them sach moi")
    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(
            @Valid @RequestBody CreateBookRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(bookService.create(request)));
    }

    //    GET 	/api/books 	Danh sách sách (phân trang)
    @Operation(summary = "Danh sach sach (phan trang)")
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Book>>> getAllBooks(
            @Parameter(description = "So trang bat dau tu 0", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "So sach moi trang") int size
    ) {
        Page<Book> results = bookService.findAll(
                PageRequest.of(page, size, Sort.by("createdAt").descending())
        );
        return ResponseEntity.ok(ApiResponse.success(results));
    }

    //    GET 	/api/books/{id} 	Chi tiết 1 cuốn sách
    @Operation(summary = "Chi tiet mot cuon sach")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(bookService.findBookById(id)));
    }

    //    PUT 	/api/books/{id} 	Cập nhật thông tin sách
    @Operation(summary = "Cap nhat thong tin sach theo id")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody CreateBookRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success("Cap nhat thanh cong", bookService.update(id, request)));
    }

    //    DELETE 	/api/books/{id} 	Xóa sách
    @Operation(summary = "Xoa sach theo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Xoa thanh cong", null));
    }

    //    POST 	/api/books/{id}/borrow 	Mượn sách
    @Operation(summary = "Muon sach theo id")
    @PostMapping("/{id}/borrow")
    public ResponseEntity<ApiResponse<Book>> borrowBook(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Muon thanh cong", bookService.borrow(id)));
    }

    //    POST 	/api/books/{id}/return 	Trả sách
    @Operation(summary = "Tra sach")
    @PostMapping("/{id}/return")
    public ResponseEntity<ApiResponse<Book>> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Tra thanh cong", bookService.returnBook(id)));
    }

    //    GET 	/api/books/search?keyword= 	Tìm sách theo title
    @Operation(summary = "Tim kiem sach theo title")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Book>>> searchBookByTitle(
            @RequestParam String keyword
    ) {
        return ResponseEntity.ok(ApiResponse.success(bookService.searchByName(keyword)));
    }

    //    GET 	/api/books/category/{category} 	Lọc sách theo thể loại}
    @Operation(summary = "Tim kiem theo theo loai")
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<Book>>> searchBookByCategory(
            @PathVariable String category
    ) {
        return ResponseEntity.ok(ApiResponse.success(bookService.searchByCategory(category)));
    }

}

