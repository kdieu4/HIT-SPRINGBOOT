package com.example.Tuan2.controller;

import com.example.Tuan2.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(3);

    public ProductController() {
        products.add(new Product(1, "iPhone 15"));
        products.add(new Product(2, "Samsung S24"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            return ResponseEntity.ok(products);
        }
        List<Product> filteredProducts = products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        return ResponseEntity.ok(filteredProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        // Tim san pham trong danh sach co id trung voi id truyen vao
        Product product = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        // Neu thay: tra ve ResponseEntity.ok(product)
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        // Neu 0: tra ve ResponseEntity.notFound().build()
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //    POST /api/products — Tạo sản phẩm mới (@RequestBody)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        // tang id
        long newId = idCounter.getAndIncrement();
        // gan id cho sp moi
        newProduct.setId(newId);
        products.add(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newProduct);
    }
//    PUT /api/products/{id} — Cập nhật sản phẩm

    @PutMapping("{id}")
    public ResponseEntity<Product> put(@PathVariable long id, @RequestBody Product updatedProduct) {
        // 1. Tim san pham
        Product found = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        // 2. Neu tim thay: cap nhat sp, tra ve 200
        if (found != null) {
            found.setName(updatedProduct.getName());
            return ResponseEntity.ok(found);
        }
        // 3. Tra ve 404 NotFound
        return ResponseEntity.notFound().build();
    }

    //    DELETE /api/products/{id} — Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
