package product.management.api.repository;

import org.springframework.stereotype.Repository;
import product.management.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
