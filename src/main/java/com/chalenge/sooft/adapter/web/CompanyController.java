package com.chalenge.sooft.adapter.web;


import com.chalenge.sooft.adapter.web.dto.CompanyDto;
import com.chalenge.sooft.adapter.web.exception.BusinessException;
import com.chalenge.sooft.application.usecase.CompanyService;
import com.chalenge.sooft.infrastructure.repository.entity.Company;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    public Page<Company> getAllCompany(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cuit,
            Pageable page
    ) {
        return companyService.getAllCompanies(page, name, cuit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public Company createCompany(@RequestBody @Valid CompanyDto companyDto) throws BusinessException {
        return companyService.saveOrUpdateCompany(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyDto companyDto) throws BusinessException {
        Optional<Company> existingCompany = companyService.getCompanyById(id);
        if (existingCompany.isPresent()) {
            companyDto.setId(id);
            return ResponseEntity.ok(companyService.saveOrUpdateCompany(companyDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        Optional<Company> existingCompany = companyService.getCompanyById(id);
        if (existingCompany.isPresent()) {
            companyService.deleteCompany(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/with-transfers-last-month")
    public List<Company> getCompaniesWithTransfersLastMonth() {
        return companyService.getCompaniesWithTransfersLastMonth();
    }

    @GetMapping("/adhered-last-month")
    public List<Company> getCompaniesAdheredLastMonth() {
        return companyService.getCompaniesAdheredLastMonth();
    }
}
