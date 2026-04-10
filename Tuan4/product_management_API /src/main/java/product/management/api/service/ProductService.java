package product.management.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import product.management.api.dto.request.CreateProductRequest;
import product.management.api.dto.request.UpdateProductRequest;
import product.management.api.entity.Category;
import product.management.api.entity.Product;
import product.management.api.exception.extended.DuplicateResourceException;
import product.management.api.exception.extended.ResourceNotFoundException;
import product.management.api.repository.CategoryRepository;
import product.management.api.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
// Tạo constructor cho các field final hoặc @NonNull.
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> findAll() {
        log.info("Lay danh sach tat ca cac san pham");
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        log.info("Tim san pham voi id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    public Product create(CreateProductRequest request) {
        // 1. Luu log
        log.info("Tạo sản phẩm mới cho danh mục: {}", request.getCategoryId());

        // 2. Kiem tra id danh muc co ton tai ko. ko co -> nem loi
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));

        if (productRepository.existsByNameAndCategory_Id(request.getName(), request.getCategoryId())) {
            throw new DuplicateResourceException("Product", "CategoryId", request.getCategoryId());
        }

        // 3. Tao sp moi
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .description(request.getDescription())
                .category(category)
                .build();

        // 4. Luu sp vao database
        Product savedProduct = productRepository.save(product);

        // 5. log
        log.info("Đã tạo product id: {} cho category id: {}", savedProduct.getId(), product.getId());
        return savedProduct;
    }

    public Product update(Long id, UpdateProductRequest request) {
        // 1. log
        log.info("Cập nhật sản phẩm id: {}", id);

        // 2. Tim sp
        Product product = findById(id);

        // 3. Kiem tra neu co thay doi danh muc (neu request chua category id)
        if (request.getCategoryId() != null && !request.getCategoryId().equals(product.getCategory().getId())) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));
            product.setCategory(category);
        }

        // 4. Cap nhat thong tin khac
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());

        // 5. Luu thay doi
        Product updatedProduct = productRepository.save(product);

        // 6. log
        log.info("Da cap nhat thanh cong san pham id: {}", id);
        return updatedProduct;
    }

    public void delete(Long id) {
        log.info("Xoa san pham id: {}", id);
        // 1. Tim sp
        Product product = findById(id);
        // 2. Xoa sp
        productRepository.delete(product);
        // 3. log
        log.info("Da xoa san pham id: {} thanh cong", id);
    }

    public List<Product> searchByName(String name) {
        log.info("Tim san pham bang ten: {}", name);
        return productRepository.findByNameContaining(name);
    }

    public List<Product> findByCategory_Id(Long categoryId) {
        // 1. Luu log
        log.info("Lay danh sach san pham cua category id: {}", categoryId);

        // 2. Kiem tra tinh hop le cua danh muc. Neu ko ton tai -> nem loi
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Categoriy", "id", categoryId);
        }

        // 3. Lay danh sach san pham thuoc danh muc
        return productRepository.findByCategory_Id(categoryId);
    }

    public List<Product> findByPrice(Double min, Double max) {
        log.info("Tim san pham theo khoan gia: tu {} den {}", min, max);
        return productRepository.findByPriceBetween(min, max);
    }
}
