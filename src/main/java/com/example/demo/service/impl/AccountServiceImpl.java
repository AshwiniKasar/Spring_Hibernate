package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account  = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository
						  .findById(id)  
						  .orElseThrow();
		
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto depositt(Long id, double amount) {
		Account account = accountRepository
				  .findById(id)  
				  .orElseThrow();
		
		double total  = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withDraw(Long id, double amount) {
		Account account = accountRepository
				  .findById(id)  
				  .orElseThrow();
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("U=Insufficient amount");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
								.collect(Collectors.toList());		
	}


	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository
				  .findById(id)  
				  .orElseThrow();
		
		
		accountRepository.deleteById(id);
	}
 
	
}
