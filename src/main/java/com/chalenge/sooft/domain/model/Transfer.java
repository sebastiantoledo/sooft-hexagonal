package com.chalenge.sooft.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Transfer {

    private Long id;
    private Company company;
    private String debitAccount;
    private String creditAccount;
    private Double amount;
    private LocalDate transferDate;

}
