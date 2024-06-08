package com.miniproject.controller;

import com.miniproject.constant.APIUrl;
import com.miniproject.dto.request.AccountRequest;
import com.miniproject.dto.response.AccountResponse;
import com.miniproject.dto.response.CommonResponse;
import com.miniproject.entity.Account;
import com.miniproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.ACCOUNT_API)
public class AccountController {
    private final AccountService accountService;

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Account>> getCustById (@PathVariable String id){
        Account account = accountService.getById(id);
        CommonResponse<Account> response = CommonResponse.<Account>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Get Account")
                .data(account)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<AccountResponse>>> getAllAccount (
            @RequestParam(name = "id",required = false) String id,
             @RequestParam(name = "accountType",required = false) String accountType
            ){

        AccountRequest request = AccountRequest.builder()
                .id(id)
                .accountType(accountType)
                .build();

        List<AccountResponse> accountResponses = accountService.getAll(request);

        CommonResponse<List<AccountResponse>> response = CommonResponse.<List<AccountResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Get All Data")
                .data(accountResponses)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Account>> updateAccType (
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "accountType", required = true) String accountType
    ){
        AccountRequest request = AccountRequest.builder()
                .id(id)
                .accountType(accountType)
                .build();

        Account account = accountService.updateAccountType(request);
        CommonResponse<Account> response = CommonResponse.<Account>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Account Type Updated")
                .data(account)
                .build();
        return ResponseEntity.ok(response);
    }
}
