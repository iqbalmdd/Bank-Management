package com.miniproject.entity;

import com.miniproject.constant.AccountType;
import com.miniproject.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.ACCOUNT)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "balance", columnDefinition = "BIGINT CHECK (balance >= 0)")
    private Long balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
