package product.management.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import product.management.api.dto.request.CreateCategoryRequest;
import product.management.api.entity.Category;
import product.management.api.exception.extended.BadRequestException;
import product.management.api.exception.extended.ResourceNotFoundException;
import product.management.api.repository.CategoryRepository;
import product.management.api.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> findAll() {
        log.info("Lay danh sach tat ca category");
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        log.info("Tim user voi id: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    public Category create(CreateCategoryRequest request) {
        // 1. log
        log.info("Tao category moi : {}", request.getName());
        // 3. Tao category
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        // 4. luu category vao db
        Category savedCategory = categoryRepository.save(category);

        // 5. log
        log.info("Da tao category thanh cong voi: {}", savedCategory);
        return savedCategory;
    }

    public Category update(Long id, CreateCategoryRequest request) {
        // 1. log
        log.info("Cap nhat category id: {}", id);

        // 2. tim kiem id
        Category category = findById(id);

        // 3. cap nhat category
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        // 4. luu category
        Category updatedCategory = categoryRepository.save(category);

        // 5. log
        log.info("Da cap nhat category id: {}", id);
        return updatedCategory;
    }

    public void delete(Long id) {
        log.info("Xoa category id: {}", id);
        // 1. Tim category_id co ton tai ko
        Category category = findById(id);

        // 2. Kiem tra xem co product trong category id ko
        if(productRepository.existsByCategory_Id(id)){
            throw new BadRequestException("Khong the xoa category nay vi dang chua san pham");
        }
        categoryRepository.delete(category);
        log.info("Da xoa category id: {} thanh cong", id);
    }
}
