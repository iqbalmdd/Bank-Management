package com.miniproject.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCustomerRequest {
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Alamat email tidak valid. Pastikan formatnya benar, contoh: example@example.com")
    private String email;
    @Pattern(regexp = "^(?:\\+62|62|0)(?:\\d{9,15})$", message = "Nomor telepon harus dimulai dengan +62, 62, atau 0, diikuti oleh 9-15 digit angka")
    private String phoneNo;
    private Boolean isActive;
}
