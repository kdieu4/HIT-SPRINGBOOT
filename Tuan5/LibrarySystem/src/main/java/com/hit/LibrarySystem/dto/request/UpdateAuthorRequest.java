package com.hit.LibrarySystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuthorRequest {
    @NotBlank(message = "Ten khong duoc de trong")
    @Size(min = 2, max = 100, message = "Ten phai tu 2 den 100 ky tu")
    private String name;

    @Pattern(regexp = "^0[0-9]{9}$", message = "So dien thoai phai co 10 chu so va bat dau bang 0")
    private String phone;
}
