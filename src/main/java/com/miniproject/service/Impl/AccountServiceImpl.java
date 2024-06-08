package com.miniproject.service.Impl;

import com.miniproject.constant.AccountType;
import com.miniproject.dto.request.AccountTypeRequest;
import com.miniproject.dto.request.SearchAccountRequest;
import com.miniproject.dto.response.AccountResponse;
import com.miniproject.entity.Account;
import com.miniproject.repository.AccountRepository;
import com.miniproject.service.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final EntityManager entityManager;
    @Override
    public Account getById(String id) {
        return accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account Not Found"));
    }

    @Override
    public List<AccountResponse> getAll(SearchAccountRequest request) {

        if (request.getAccountType() != null){
            String sql = "SELECT * FROM m_account WHERE account_type :accountType";
            Query query = entityManager.createNativeQuery(sql, Account.class);
            query.setParameter("accountType", request.getAccountType());
            List<AccountResponse> queryResultList = query.getResultList();
            return queryResultList;
        } else {
            List<Account> queryResultList = accountRepository.findAll();
            List<AccountResponse> allAccount = queryResultList.stream().map(
                    accountparam ->
                            AccountResponse.builder()
                                    .id(accountparam.getId())
                                    .customer(accountparam.getCustomer())
                                    .balance(accountparam.getBalance())
                                    .accountType(accountparam.getAccountType())
                                    .build()
            ).toList();
            return allAccount;
        }



    }

    @Override
    public Account updateAccountType(AccountTypeRequest accountTypeRequest) {
        Account account = getById(accountTypeRequest.getId());
        account.setAccountType(AccountType.valueOf(accountTypeRequest.getAccountType()));
        return account;
    }
}
