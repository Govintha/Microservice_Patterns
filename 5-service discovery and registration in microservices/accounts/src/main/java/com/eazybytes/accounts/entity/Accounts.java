package com.eazybytes.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @ToString @AllArgsConstructor @NoArgsConstructor @Setter
public class Accounts extends  BaseEntity{

    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    private Long customerId;
}
