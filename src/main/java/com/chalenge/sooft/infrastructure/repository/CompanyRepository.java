package com.chalenge.sooft.infrastructure.repository;

import com.chalenge.sooft.infrastructure.repository.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT DISTINCT t.company FROM Transfer t WHERE t.transferDate BETWEEN :startDate AND :endDate")
    List<Company> findCompaniesWithTransfersInPeriod(LocalDate startDate, LocalDate endDate);

    List<Company> findByAdhesionDateBetween(LocalDate startDate, LocalDate endDate);
}
