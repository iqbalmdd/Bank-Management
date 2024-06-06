package com.miniproject.service.Impl;

import com.miniproject.constant.AccountType;
import com.miniproject.dto.request.CustomerRequest;
import com.miniproject.entity.Account;
import com.miniproject.entity.Customer;
import com.miniproject.repository.AccountRepository;
import com.miniproject.repository.CustomerRepository;
import com.miniproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    @Override
    public Customer create(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .phoneNo(customerRequest.getPhoneNo())
                .isActive(true)
                .build();
        customerRepository.saveAndFlush(customer);

        Account account = Account.builder()
                .customer(customer)
                .balance(0L)
                .accountType(AccountType.REGULAR)
                .build();

        accountRepository.saveAndFlush(account);
        return customer;
    }

    public Customer findByIdOrThrow (String id){
        return customerRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found"));
    }

    @Override
    public Customer getById(String id) {
        return findByIdOrThrow(id);
    }

    @Override
    public List<Customer> getAll(CustomerRequest customerRequest) {
        return List.of();
    }

    @Override
    public Customer update(Customer customer) {
        getById(customer.getId());
        return customerRepository.saveAndFlush(customer); // set in properties spring.jpa.hibernate.ddl-auto=update
    }

    @Override
    public void updateStatusById(String id, Boolean status) {
        findByIdOrThrow(id);
        customerRepository.updateStatus(id,status);
    }


}
