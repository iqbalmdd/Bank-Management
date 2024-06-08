package com.miniproject.service;

import com.miniproject.dto.request.SearchTransactionRequest;
import com.miniproject.dto.request.TransactionRequest;
import com.miniproject.dto.response.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    TransactionResponse create(TransactionRequest transactionRequest);
    List<TransactionResponse> getAll(SearchTransactionRequest searchTransactionRequest);
}
