package product.management.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCategoryRequest {
    @NotBlank(message = "Ten danh muc khong duoc de trong")
    private String name;
    
    private String description;
}
