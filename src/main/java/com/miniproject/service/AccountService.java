package com.miniproject.service;

import com.miniproject.dto.request.AccountTypeRequest;
import com.miniproject.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    Account getById (String id);
    List<Account> getAll(Account account);
    Account updateAccount (AccountTypeRequest accountTypeRequest);
}
