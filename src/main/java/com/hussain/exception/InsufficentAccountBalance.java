package com.hussain.exception;

public class InsufficentAccountBalance extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficentAccountBalance(String msg) {
		super(msg);
	}

}
