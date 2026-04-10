package product.management.api.repository;

import org.springframework.stereotype.Repository;
import product.management.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    List<Product> findByCategory_Id(Long categoryId);

    List<Product> findByPriceBetween(Double min, Double max);

    List<Product> findByActiveTrue();

    boolean existsByNameAndCategory_Id(String name, Long categoryId);

    boolean existsByCategory_Id(Long categoryId);
}
