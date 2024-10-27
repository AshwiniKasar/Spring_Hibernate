package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountDto;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	//Add account rest API
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) { //convert Json into the Java Object,
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	//input:
//	{
//	    "accountHolderName": "Ashu",
//	    "balance": 25000
//	}
	
	//Get account Rest API
	@GetMapping("/{Id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long Id) {
		AccountDto accountDto = accountService.getAccountById(Id);
		return ResponseEntity.ok(accountDto);
	}
	
	//Deposit REST API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depositt(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.depositt(id, amount);
		return ResponseEntity.ok(accountDto);
	}

	
	//WithDraw Rest API
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withDraw(@PathVariable Long id,@RequestBody Map<String, Double> request) {
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withDraw(id, amount);	
		return ResponseEntity.ok(accountDto);
		
	}
	
	//Get All Account
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	//Delete Account Rest API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully!");		
	}
}


//http://localhost:8080/api/accounts/3  Get
//http://localhost:8080/api/accounts/1/deposit put
//http://localhost:8080/api/accounts/2/withdraw put
//http://localhost:8080/api/accounts/3 delete
//http://localhost:8080/api/accounts Get
