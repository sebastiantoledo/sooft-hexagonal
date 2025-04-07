package com.chalenge.sooft.infrastructure.repository;

import com.chalenge.sooft.infrastructure.repository.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT DISTINCT t.company FROM Transfer t WHERE t.transferDate BETWEEN :startDate AND :endDate")
    List<Company> findCompaniesWithTransfersInPeriod(LocalDate startDate, LocalDate endDate);

    List<Company> findByAdhesionDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT c FROM Company c WHERE " +
            "(LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL) AND " +
            "(LOWER(c.cuit) LIKE LOWER(CONCAT('%', :cuit, '%')) OR :cuit IS NULL)")
    Page<Company> findAll(Pageable pageable, @Param("name") String name, @Param("cuit") String cuit);
}
