package com.chalenge.sooft.adapter.web.dto;

import lombok.Data;

@Data
public class TransferDto {
    private String debitAccount;
    private String creditAccount;
    private Double amount;
    private Long companyId;
}
