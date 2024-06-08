package com.miniproject.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchAccountRequest {
    private String id;
    private String accountType;
}
