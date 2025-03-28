package com.chalenge.sooft.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Company {

    private Long id;
    private String cuit;
    private String name;
    private LocalDate adhesionDate;
    private List<Transfer> transfers;

}
