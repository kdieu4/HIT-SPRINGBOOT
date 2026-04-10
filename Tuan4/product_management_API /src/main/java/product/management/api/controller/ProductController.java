package product.management.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.management.api.dto.request.CreateProductRequest;
import product.management.api.dto.request.UpdateProductRequest;
import product.management.api.entity.Product;
import product.management.api.dto.response.ApiResponse;
import product.management.api.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    //    GET 	/api/products 	Lấy tất cả
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        return ResponseEntity.ok(ApiResponse.success(productService.findAll()));
    }

    //    GET 	/api/products/{id} 	Lấy theo id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    //    POST 	/api/products 	Tạo mới (body chứa categoryId)
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody CreateProductRequest request) {
        Product created = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(created));
    }

    //    PUT 	/api/products/{id} 	Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest request) {
        Product updated = productService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cap nhat thanh cong", updated));
    }

    //    DELETE 	/api/products/{id} 	Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Xoa thanh cong", null));
    }

    //    GET 	/api/products/search?keyword=xxx 	Tìm theo tên
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByName(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.success(productService.searchByName(keyword)));
    }

    //    GET 	/api/products/category/{categoryId} 	Lấy theo danh mục
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(productService.findByCategory_Id(categoryId)));
    }

    //    GET 	/api/products/price?min=x&max=y 	Lọc theo khoảng giá
    @GetMapping("price")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max
    ) {
        return ResponseEntity.ok(ApiResponse.success(productService.findByPrice(min, max)));
    }
}