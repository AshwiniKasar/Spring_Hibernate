package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto depositt(Long id, double amount);
	
	AccountDto withDraw(Long id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}
