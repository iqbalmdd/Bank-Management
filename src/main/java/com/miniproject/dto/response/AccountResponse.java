package com.miniproject.dto.response;

import com.miniproject.constant.AccountType;
import com.miniproject.entity.Customer;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {
    private String id;
    private Customer customer;
    private Long balance;
    private AccountType accountType;
}
