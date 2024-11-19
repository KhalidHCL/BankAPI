package com.hussain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hussain.dto.FundTransferRequestDTO;
import com.hussain.entites.Account;
import com.hussain.entites.TransactionHistory;
import com.hussain.exception.AccountNumberNotExist;
import com.hussain.exception.InsufficentAccountBalance;
import com.hussain.repository.AccountRepository;
import com.hussain.repository.CustomerRepository;
import com.hussain.repository.TransationRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransationRepository transationRepository;
	
	@Value("${toAccount}")
	private String toAccountGlobal;

	@Override
	public String fundTransfer(FundTransferRequestDTO fundTransferRequestDTO) throws AccountNumberNotExist, InsufficentAccountBalance {
		Account fromAccount = accountRepository.findByAccountNumber(fundTransferRequestDTO.getFromAccountNumber()).orElseThrow(()->new AccountNumberNotExist("Invalid From Account Number"));
		
		if(toAccountGlobal==null || !toAccountGlobal.equalsIgnoreCase(fundTransferRequestDTO.getToAccountNumber())) {
			throw new AccountNumberNotExist("To account is not exist Or Ivalid");
		}
		if (fromAccount.getBalance().compareTo(fundTransferRequestDTO.getAmount()) < 0) {
            throw new InsufficentAccountBalance("Insufficient balance in the from account.");
        }
//		fromAccount.setBalance(fromAccount.getBalance().subtract(fundTransferRequestDTO.getAmount()));
		
		fromAccount.setBalance(fromAccount.getBalance()-fundTransferRequestDTO.getAmount());
		
//      toAccount.setBalance(toAccount.getBalance().add(amount));
		accountRepository.save(fromAccount);
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setFromAccount(fundTransferRequestDTO.getFromAccountNumber());
        transactionHistory.setToAccount(toAccountGlobal);
        transactionHistory.setTransferAmount(fundTransferRequestDTO.getAmount());
        transactionHistory.setTransactionTime(LocalDateTime.now());
        transactionHistory.setComments(fundTransferRequestDTO.getComments());
        transactionHistory.setTotalBalance(fromAccount.getBalance().doubleValue());
        transationRepository.save(transactionHistory);
        return "success";
	}
	
	public List<TransactionHistory> getTransactionHistory(String accountNumber, boolean isFromAccount) {
        return isFromAccount
                ? transationRepository.findByFromAccount(accountNumber)
                : transationRepository.findByToAccount(accountNumber);
    }

}
