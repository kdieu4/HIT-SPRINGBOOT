package product.management.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//Tạo entity Category gồm:
// id (Long, auto increment),
// name (String, unique, không rỗng),
// description (String, tối đa 500 ký tự),
// createdAt (LocalDateTime, auto set khi tạo),
// updatedAt (LocalDateTime, auto set khi tạo/update).

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    // mappedBy	Xác định bên chủ quản (Product).	Sẽ tạo thêm một bảng phụ trung gian không cần thiết.
    // cascade	Tự động hóa việc lưu/xóa các con.	Bạn phải lưu từng Product thủ công trước khi lưu Category.
    // fetch	Tối ưu hóa tốc độ tải dữ liệu.	Mặc định của @OneToMany là LAZY, nhưng nên ghi rõ để dễ quản lý.

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setCategory(null);
    }
}
