package com.miniproject.entity;

import com.miniproject.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "phone_no",nullable = false)
    private String phoneNo;
    @Column(name = "is_active",nullable = false)
    private Boolean isActive;

}
