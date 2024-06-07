package com.miniproject.service;

import com.miniproject.dto.request.CustomerRequest;
import com.miniproject.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer create(CustomerRequest customerRequest);
    Customer getById(String id);
    List<Customer> getAllNameLike (String name);
    Customer update (Customer customer);
    void updateStatusById (String id, Boolean status);

}
