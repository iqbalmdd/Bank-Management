package com.miniproject.controller;

import com.miniproject.constant.APIUrl;
import com.miniproject.dto.request.SearchTransactionRequest;
import com.miniproject.dto.request.TransactionRequest;
import com.miniproject.dto.response.CommonResponse;
import com.miniproject.dto.response.TransactionResponse;
import com.miniproject.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.TRANSACTION_API)
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<CommonResponse<TransactionResponse>> newTransaction (@RequestBody TransactionRequest transactionRequest){
        TransactionResponse transactionResponses = transactionService.create(transactionRequest);
        CommonResponse<TransactionResponse> response = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Transaction Success")
                .data(transactionResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TransactionResponse>>> getTransaction (
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "transType", required = false) String transType
    ){
        SearchTransactionRequest request = SearchTransactionRequest.builder()
                .id(id)
                .transactionType(transType)
                .build();

        List<TransactionResponse> transactionResponseList = transactionService.getAll(request);
        CommonResponse<List<TransactionResponse>> response = CommonResponse.<List<TransactionResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Get Data")
                .data(transactionResponseList)
                .build();
        return ResponseEntity.ok(response);
    }
}
