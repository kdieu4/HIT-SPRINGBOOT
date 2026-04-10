package product.management.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

//Tạo entity Product gồm:
// id (Long, auto increment),
// name (String, 2–100 ký tự, không rỗng),
// price (Double, > 0),
// quantity (Integer, >= 0),
// description (String, tối đa 1000 ký tự, nullable),
// active (Boolean, default true),
// createdAt,
// updatedAt.

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Positive
    @Column(nullable = false)
    private Double price;

    @Min(value = 0)
    private Integer quantity = 0;

    @Column(length = 1000, nullable = true)
    private String description;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false) // FK nằm ở bảng "nhiều" (orders)
    private Category category;
}
