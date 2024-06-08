package com.miniproject.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {
    private String accountId;
    private Long amount;
    private String transactionType;
}
