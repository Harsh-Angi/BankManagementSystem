package controller;

import customExceptions.InsufficientBalanceException;
import customExceptions.MatchedPasswordException;
import model.User;

public class AccountController implements AccountControllerInterface {
	
	@Override
	public double deposit(int accountNumber, double amount) {
		User user= UserController.map.get(accountNumber);
		double newBal= user.getBalance()+ amount;
		user.setBalance(newBal);
		return user.getBalance();
	}

	@Override
	public boolean withdraw(int accountNumber, double amount) {
		User user= UserController.map.get(accountNumber);
		try {
		
		double bal= user.getBalance();
		if(amount>bal) {
			throw new InsufficientBalanceException();
		}
		double newBal= bal-amount;
		user.setBalance(newBal);
		return true;
		}
		catch(InsufficientBalanceException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public double checkBal(int accountNumber) {
		User user= UserController.map.get(accountNumber);
		return user.getBalance();
	}

	@Override
	public long changePhoneNumber(int accountNumber, long phoneNumber) {
		User user= UserController.map.get(accountNumber);
		user.setPhone(phoneNumber);
		return user.getPhone();
	}

	@Override
	public String changeEmail(int accountNumber, String email) {
		User user= UserController.map.get(accountNumber);
		user.setEmail(email);
		return user.getEmail();
	}

	@Override
	public String updateName(int accountNumber, String name) {
		User user= UserController.map.get(accountNumber);
		user.setName(name);
		return user.getName();
	}

	@Override
	public boolean changePassword(int accountNumber, String oldPwd,String newPwd) {
		try {
		User user= UserController.map.get(accountNumber);
		if(user.getPwd().equals(oldPwd)) {
			if(oldPwd.equals(newPwd)) {
				throw new MatchedPasswordException();
				
			}
			
			user.setPwd(newPwd);
			return true;	
		}
		else {
			System.out.println("Old Password is Incorrect, Please Try Again...");
			return false;
		}
		}
		catch(MatchedPasswordException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
