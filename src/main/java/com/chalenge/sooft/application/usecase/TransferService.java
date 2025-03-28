package com.chalenge.sooft.application.usecase;

import com.chalenge.sooft.adapter.web.dto.TransferDto;
import com.chalenge.sooft.infrastructure.repository.CompanyRepository;
import com.chalenge.sooft.infrastructure.repository.TransferRepository;
import com.chalenge.sooft.infrastructure.repository.entity.Company;
import com.chalenge.sooft.infrastructure.repository.entity.Transfer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Transfer saveOrUpdateTransfer(TransferDto transferDto) {
        Optional<Company> companyOp = companyRepository.findById(transferDto.getCompanyId());
        Transfer transfer = new ModelMapper().map(transferDto, Transfer.class);
        transfer.setId(null);
        transfer.setCompany(companyOp.get());
        return transferRepository.save(transfer);
    }

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public List<Transfer> getTransfersByCompany(Long companyId) {
        Optional<Company> companyOp = companyRepository.findById(companyId);
        if (companyOp.isPresent()) {
            return companyOp.get().getTransfers();
        } else {
            return new ArrayList<>();
        }
    }

}
