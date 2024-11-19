package com.hussain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hussain.dto.FundTransferRequestDTO;
import com.hussain.entites.TransactionHistory;
import com.hussain.exception.AccountNumberNotExist;
import com.hussain.exception.InsufficentAccountBalance;
import com.hussain.service.FundTransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class FundTransferController {
	
	@Autowired
	private FundTransferService service;
	
	@PostMapping("/transfer")
    public String transferFunds(@Valid @RequestBody FundTransferRequestDTO fundtransferDto) throws AccountNumberNotExist, InsufficentAccountBalance {
		String fundTransfer = service.fundTransfer(fundtransferDto);
        return fundTransfer;
    }
    
	@PostMapping("/history")
    public List<TransactionHistory> getTransactionHistory(
            @RequestParam String accountNumber,
            @RequestParam(defaultValue = "true") boolean isFromAccount) {
        return service.getTransactionHistory(accountNumber, isFromAccount);
    }
}
