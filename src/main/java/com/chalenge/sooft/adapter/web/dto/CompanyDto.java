package com.chalenge.sooft.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyDto {
    private Long id;

    @NotBlank(message = "El cuit no puede estar vacío")
    @Size(min = 11, max = 11, message = "El cuit debe tener 11 caracteres")
    private String cuit;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    private LocalDate adhesionDate;
}
