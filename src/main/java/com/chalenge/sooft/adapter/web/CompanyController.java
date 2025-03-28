package com.chalenge.sooft.controller;


import com.chalenge.sooft.application.usecase.CompanyService;
import com.chalenge.sooft.infrastructure.repository.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Company> getAllCompany() {
        return companyService.getAllCompanies();
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
    public Company createCompany(@RequestBody Company company) {
        return companyService.saveOrUpdateCompany(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Optional<Company> existingCompany = companyService.getCompanyById(id);
        if (existingCompany.isPresent()) {
            company.setId(id);
            return ResponseEntity.ok(companyService.saveOrUpdateCompany(company));
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
