package com.hussain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FundTransferGlobalException {
	
	@ExceptionHandler(AccountNumberNotExist.class)
	public ResponseEntity<String> accoundNumberNotExist(AccountNumberNotExist ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InsufficentAccountBalance.class)
	public ResponseEntity<String> insufficentAccountBalance(InsufficentAccountBalance ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
	}
   
	public ResponseEntity<String> transactionFailExceptionEntity(TransactionFailExceptio ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.EXPECTATION_FAILED);
	}
}
