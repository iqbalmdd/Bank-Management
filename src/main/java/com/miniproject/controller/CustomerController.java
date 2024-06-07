package com.miniproject.controller;

import com.miniproject.constant.APIUrl;
import com.miniproject.dto.request.CustomerRequest;
import com.miniproject.dto.response.CommonResponse;
import com.miniproject.entity.Customer;
import com.miniproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> newCustomer (@RequestBody CustomerRequest customerRequest){
        Customer customer = customerService.create(customerRequest);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("New Customer Created")
                .data(customer)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Customer>> getbyId (@PathVariable String id){
        Customer customer = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Get Data")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> SearchCustomer (@RequestParam(name = "name", required = false) String nameLike){
        List<Customer> customer = customerService.getAllNameLike(nameLike);
        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Get Data")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer (@RequestBody Customer request){
        Customer customer = customerService.update(request);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Update Data")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<String>> updateCustomerStatus (
            @PathVariable String id,
            @RequestParam(name = "status") Boolean status){
        customerService.updateStatusById(id,status);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Update Customer Status")
                .build();
        return ResponseEntity.ok(response);
    }
}
