package com.miniproject.repository;

import com.miniproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,String> {

    // Query Native Param
    @Modifying
    @Query(value = "UPDATE m_customer SET is_active = :status WHERE id = :id", nativeQuery = true)
    void updateStatus(@Param("id") String id, @Param("status") Boolean status);

}
