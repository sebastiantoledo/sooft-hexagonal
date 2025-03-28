package com.chalenge.sooft.adapter.web;

import com.chalenge.sooft.adapter.web.dto.TransferDto;
import com.chalenge.sooft.application.usecase.TransferService;
import com.chalenge.sooft.infrastructure.repository.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("/")
    public List<Transfer> getAllTransfer() {
        return transferService.getAllTransfers();
    }

    @PostMapping("/")
    public Transfer createTransfer(@RequestBody TransferDto transfer) {
        return transferService.saveOrUpdateTransfer(transfer);
    }

    @GetMapping("/by-company/{companyId}")
    public List<Transfer> getTransfersByCompany(@PathVariable("companyId") Long companyId) {
        return transferService.getTransfersByCompany(companyId);
    }

}
