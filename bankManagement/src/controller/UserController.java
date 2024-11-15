package controller;
import java.util.HashMap;

import java.util.Map;

import customExceptions.AccountAlreadyExistException;
import customExceptions.AccountNotFoundException;
import customExceptions.InsufficientBalanceException;
import customExceptions.InvalidPasswordException;
import customExceptions.MatchedPasswordException;
import model.User;

public class UserController implements UserControllerInterface{
//	Map	
//	In Key we will store account number, and in User we will store object
	static Map<Integer,User> map= new HashMap<Integer,User>();
	boolean isAdded=false;
	
	
//	For Registering the user
	@Override
	public boolean registerUser(User user) {
		try {
		if(!map.containsKey(user.getAccountNumber())) {
			map.put(user.getAccountNumber(), user);
			isAdded=true;
		}
		else {
			throw new AccountAlreadyExistException();
		}
		
		if(isAdded) {
			System.out.println("Account Created Succcessfully");
			return true;
		}
		else {
			System.out.println("Account Not Created....");
			return false;
		}}
		catch(AccountAlreadyExistException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	
//	For login into the account
	@Override
	public boolean loginUser(int accountNumber,String pwd) {
		try {
		if(map.containsKey(accountNumber)) {
			User user= map.get(accountNumber);
			
			if(user.getPwd().equals(pwd)) {	
				System.out.println("Login Successfull");
				return true;
				
			}
			else {
				throw new InvalidPasswordException();
			}
		}
		else {
			throw new AccountNotFoundException();
		}}
		catch(InvalidPasswordException e) {
			System.out.println(e.getMessage());
			return false;
		}
		catch(AccountNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	
	

}
}
