package product.management.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateProductRequest {
    @NotBlank(message = "Ten san pham khong duoc de trong")
    private String name;

    @Positive(message = "Gia san pham phai lon hon khong")
    private Double price;

    @PositiveOrZero(message = "So luong san pham phai lon hon hoac bang khong")
    private Integer quantity;

    @Size(max = 1000, message = "Mo ta san pham khong duoc qua 1000 tu")
    private String description;

    @NotNull(message = "Id khong duoc de trong")
    private Long categoryId;
}
