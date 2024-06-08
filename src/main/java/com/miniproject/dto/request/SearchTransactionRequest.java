package com.miniproject.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchTransactionRequest {
    private String id;
    private String transactionType;
}
