package com.miniproject.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
    private String id;
    @Pattern(regexp = "^(REGULAR|PRIORITY|PREMIUM)$", message = "Nilai harus REGULAR, PRIORITY, atau PREMIUM")
    private String accountType;

}
