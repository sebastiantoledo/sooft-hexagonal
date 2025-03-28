package com.chalenge.sooft.infrastructure.repository;

import com.chalenge.sooft.infrastructure.repository.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
