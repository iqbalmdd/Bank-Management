package com.miniproject.entity;

import com.miniproject.constant.ConstantTable;
import com.miniproject.constant.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.TRANSACTION)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "amount")
    private Long amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
