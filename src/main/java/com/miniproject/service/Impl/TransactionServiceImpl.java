package com.miniproject.service.Impl;

import com.miniproject.constant.TransactionType;
import com.miniproject.dto.request.SearchTransactionRequest;
import com.miniproject.dto.request.TransactionRequest;
import com.miniproject.dto.response.TransactionResponse;
import com.miniproject.entity.Account;
import com.miniproject.entity.Transaction;
import com.miniproject.repository.TransactionRepository;
import com.miniproject.service.AccountService;
import com.miniproject.service.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final EntityManager entityManager;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionResponse create(TransactionRequest transactionRequest) {
        // Set Amount After Transaction
        Account account = accountService.getById(transactionRequest.getAccountId());
        if (transactionRequest.getTransactionType().equals(TransactionType.DEPOSIT.toString()) ){
            account.setBalance(account.getBalance() + transactionRequest.getAmount());
            accountService.updateBalance(account);
        } else if (transactionRequest.getTransactionType().equals(TransactionType.WITHDRAWAL.toString()) ||
                transactionRequest.getTransactionType().equals(TransactionType.PAYMENT.toString())) {
            account.setBalance(account.getBalance() - transactionRequest.getAmount());
            accountService.updateBalance(account);
        }
        // Check Balance >= 0
        if (account.getBalance() < 0){
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Save Transaction to Database
        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(transactionRequest.getAmount())
                .transactionType(TransactionType.valueOf(transactionRequest.getTransactionType()))
                .build();
        transactionRepository.saveAndFlush(transaction);

        // Set Output Transaction
        return TransactionResponse.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccount().getId())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType().toString())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionResponse> getAll(SearchTransactionRequest searchTransactionRequest) {
        // Filter Check
        if (searchTransactionRequest.getId() != null || searchTransactionRequest.getTransactionType() != null){
            StringBuilder sql = new StringBuilder("SELECT * FROM t_transaction WHERE 1=1");
            List<Object> parameters = new ArrayList<>();

            if (searchTransactionRequest.getId() != null){
                sql.append(" AND id = ?");
                parameters.add(searchTransactionRequest.getId());
            }

            if (searchTransactionRequest.getTransactionType() != null){
                sql.append(" AND transaction_type = ?");
                parameters.add(searchTransactionRequest.getTransactionType());
            }

            Query query = entityManager.createNativeQuery(sql.toString(), Transaction.class);

            for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i + 1, parameters.get(i));
            }

            List<Transaction> transactionList = query.getResultList();
            List<TransactionResponse> transactionResponses = transactionList.stream().map(
                    transactionparam ->
                        TransactionResponse.builder()
                                .id(transactionparam.getId())
                                .accountId(transactionparam.getAccount().getId())
                                .amount(transactionparam.getAmount())
                                .transactionType(transactionparam.getTransactionType().toString())
                                .build()
            ).toList();
            return transactionResponses;
        } else {
            List<Transaction> transactionList = transactionRepository.findAll();
            List<TransactionResponse> transactionResponses = transactionList.stream().map(
                    transactionparam ->
                            TransactionResponse.builder()
                                    .id(transactionparam.getId())
                                    .accountId(transactionparam.getAccount().getId())
                                    .amount(transactionparam.getAmount())
                                    .transactionType(transactionparam.getTransactionType().toString())
                                    .build()
            ).toList();
            return transactionResponses;
        }
    }
}
