package com.hussain.service;

import java.util.List;

import com.hussain.dto.FundTransferRequestDTO;
import com.hussain.entites.TransactionHistory;
import com.hussain.exception.AccountNumberNotExist;
import com.hussain.exception.InsufficentAccountBalance;

public interface FundTransferService {
	public String fundTransfer(FundTransferRequestDTO fundTransferRequestDTO) throws AccountNumberNotExist, InsufficentAccountBalance;
	public List<TransactionHistory> getTransactionHistory(String accountNumber, boolean isFromAccount);
}
