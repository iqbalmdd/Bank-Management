package com.miniproject.service;

import com.miniproject.dto.request.AccountTypeRequest;
import com.miniproject.dto.request.SearchAccountRequest;
import com.miniproject.dto.response.AccountResponse;
import com.miniproject.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    Account getById (String id);
    List<AccountResponse> getAll(SearchAccountRequest request);
    Account updateAccountType (AccountTypeRequest accountTypeRequest);
}
