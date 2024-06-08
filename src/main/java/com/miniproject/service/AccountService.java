package com.miniproject.service;

import com.miniproject.dto.request.AccountRequest;
import com.miniproject.dto.response.AccountResponse;
import com.miniproject.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    Account getById (String id);
    List<AccountResponse> getAll(AccountRequest accountRequest);
    Account updateAccountType (AccountRequest accountRequest);
    Account updateBalance (Account account);
}
