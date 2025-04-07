package com.chalenge.sooft.application.usecase;


import com.chalenge.sooft.adapter.web.dto.CompanyDto;
import com.chalenge.sooft.adapter.web.exception.BusinessException;
import com.chalenge.sooft.adapter.web.exception.ErrorResponse;
import com.chalenge.sooft.infrastructure.repository.CompanyRepository;
import com.chalenge.sooft.infrastructure.repository.entity.Company;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company saveOrUpdateCompany(CompanyDto companyDto) throws BusinessException {
        Company company = new ModelMapper().map(companyDto, Company.class);
        // Only to test exception.
        if (company.getName().equals("Ex1")) {
            throw new BusinessException(HttpStatus.NOT_FOUND, new ErrorResponse("Example business rule - Company disabled."));
        } else if (company.getName().equals("Ex2")) {
            // Exception not
            int ex = 1 / 0;
        }
        return companyRepository.save(company);
    }

    public Page<Company> getAllCompanies(Pageable pageable, String name, String cuit) {
        return companyRepository.findAll(pageable, name, cuit);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public List<Company> getCompaniesWithTransfersLastMonth() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        return companyRepository.findCompaniesWithTransfersInPeriod(startDate, endDate);

    }

    public List<Company> getCompaniesAdheredLastMonth() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        return companyRepository.findByAdhesionDateBetween(startDate, endDate);
    }
}
