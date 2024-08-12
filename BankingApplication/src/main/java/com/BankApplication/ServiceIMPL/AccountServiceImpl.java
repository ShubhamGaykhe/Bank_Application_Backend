package com.BankApplication.ServiceIMPL;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.BankApplication.DTO.AccountDTO;
import com.BankApplication.Mapper.AccountMapper;
import com.BankApplication.entity.Account;
import com.BankApplication.repository.AccountRepository;
import com.BankApplication.services.AccountService;
@Service
public class AccountServiceImpl implements AccountService{

	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	

	@Override
	public AccountDTO createAccount(AccountDTO accountDTO) {
		Account account=AccountMapper.mapToAccount(accountDTO);
		Account saveAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDTO(saveAccount);
	}


	@Override
	public AccountDTO getAccountById(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exits"));
		return AccountMapper.mapToAccountDTO(account);
	}


	@Override
	public AccountDTO deposite(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exits"));
		double totalBalance=account.getBalance()+amount;
		account.setBalance(totalBalance);
		Account saveAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDTO(saveAccount);
	}

	@Override
	public AccountDTO withdraw(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exits"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Balance");
			
		}
		double totalBalance=account.getBalance()-amount;
		account.setBalance(totalBalance);
		Account saveAccount=accountRepository.save(account);
		return  AccountMapper.mapToAccountDTO(saveAccount);
		}


	@Override
	public List<AccountDTO> getAllAccount() {
		return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());
	}


	@Override
	public void deleteAccount(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exits"));
		accountRepository.delete(account);
		
	}
}
