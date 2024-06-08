package com.miniproject.service.Impl;

import com.miniproject.constant.AccountType;
import com.miniproject.dto.request.CustomerRequest;
import com.miniproject.entity.Account;
import com.miniproject.entity.Customer;
import com.miniproject.repository.AccountRepository;
import com.miniproject.repository.CustomerRepository;
import com.miniproject.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final EntityManager entityManager;
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(readOnly = true)
    @Override
    public Customer getById(String id) {
        return findByIdOrThrow(id);
    }

    // Native Query
    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAll (Customer customer) {
        if (customer == null) {
            return customerRepository.findAll();
        }

        StringBuilder sql = new StringBuilder("SELECT * FROM m_customer WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (customer.getName() != null) {
            sql.append(" AND name LIKE ?");
            parameters.add("%" + customer.getName() + "%");
        }

        if (customer.getEmail() != null) {
            sql.append(" AND email LIKE ?");
            parameters.add("%" + customer.getEmail() + "%");
        }

        if (customer.getIsActive() != null) {
            sql.append(" AND is_active = ?");
            parameters.add(customer.getIsActive());
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Customer.class);

        for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i + 1, parameters.get(i));
        }

        return query.getResultList();
    }
    @Transactional(readOnly = true)
    @Override
    public Customer update(Customer customer) {
        getById(customer.getId());
        return customerRepository.saveAndFlush(customer); // set in properties spring.jpa.hibernate.ddl-auto=update
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatusById(String id, Boolean status) {
        findByIdOrThrow(id);
        customerRepository.updateStatus(id,status);
    }


}
