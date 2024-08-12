package com.BankApplication.services;

import java.util.List;

import com.BankApplication.DTO.AccountDTO;

public interface AccountService {

	AccountDTO createAccount(AccountDTO accountDTO);
	
	AccountDTO getAccountById(Long id);
	
	AccountDTO deposite(Long id,double amount);
	
	AccountDTO withdraw(Long id,double amount);
	
	List<AccountDTO> getAllAccount();
	
	void deleteAccount(Long id);
}
