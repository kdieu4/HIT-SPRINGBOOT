package product.management.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.management.api.dto.response.ApiResponse;
import product.management.api.dto.request.CreateCategoryRequest;
import product.management.api.entity.Category;
import product.management.api.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //    GET 	/api/categories 	Lấy tất cả
    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        return ResponseEntity.ok(ApiResponse.success(categoryService.findAll()));
    }

    //    GET 	/api/categories/{id} 	Lấy theo id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(category));
    }

    //    POST 	/api/categories 	Tạo mới
    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        Category created = categoryService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(created));
    }

    //    PUT 	/api/categories/{id} 	Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(@PathVariable Long id, @Valid @RequestBody CreateCategoryRequest request) {
        Category updated = categoryService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cap nhat thanh cong", updated));
    }

    //    DELETE 	/api/categories/{id} 	Xóa (kiểm tra ràng buộc)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Xoa thanh cong", null));
    }
}