package view;


import java.util.InputMismatchException;
import java.util.Random;
import customExceptions.InvalidOptionException;
import java.util.Scanner;
import controller.AccountControllerInterface;
import controller.AccountController;
import controller.UserController;
import controller.UserControllerInterface;
import model.User;


public class View {
	static UserControllerInterface controller= new UserController();
	Scanner sc= new Scanner(System.in);
	
	public static void main(String[] args) {
		View view=new View();
		view.StartingMenu();
			}
	
	
//	Display To User 
	public void StartingMenu() {
	while(true) {
	
	System.out.println("Welcome To Bank");
	System.out.println("Press 1 to Register Your Account");
	System.out.println("Press 2 to Login Your Account");
	System.out.println("Press 3 to Exit");
	System.out.println("Select Your Choice: ");
	
	try {
	int choice= sc.nextInt();
	switch(choice) {
	case 1: 
		registration();
		break;
	
	case 2:
		login();
		break;
		
	case 3: 
		System.out.println("Thank You, Visit Again");
		System.exit(0);
	
	default:
		try {
			throw new InvalidOptionException();
		}
		catch(InvalidOptionException e) {
			System.out.println(e.getMessage());
		}
		break;
	}
	}catch(InputMismatchException e) {
		System.out.println("Please Enter a Valid Input");
		sc.nextLine();
	}
		
	}
	}

	
	
//	To Create New Account
	public void registration() {
		try {
		
		Random rand= new Random();
		int accountNumber= rand.nextInt(999999);
		System.out.println("Enter Your Name");
		sc.nextLine();
		String name= sc.nextLine();
		System.out.println("Enter your Password");
		String pwd= sc.next();
		System.out.println("Enter your email");
		String email= sc.next();
		System.out.println("Enter Your Phone Number");
		long phone= sc.nextLong();
		System.out.println("Enter the balance you want to deposit");
		double balance= sc.nextDouble();
		boolean accountCreated= controller.registerUser(new User(name,pwd,email,phone,accountNumber,balance));
		
		if(accountCreated)
		System.out.println("Your Account Number is:" + accountNumber);
		
	}
	catch(InputMismatchException e) {
		System.out.println("Please enter the details correctly");
		sc.next();
		registration();
	}
	}
	
//	To Login
	public void login() {
		try {
		System.out.println("Enter Your Account Number");
		int accountNumber=sc.nextInt();
		System.out.println("Enter your password");
		String pwd= sc.next();
		boolean result= controller.loginUser(accountNumber, pwd);
		
		if(result) {
			userMenu(accountNumber);
		}
		else {
			return;
		}
		}
		catch(InputMismatchException e) {
			System.out.println("Please enter the details correctly");
			sc.nextLine();
			login();
		}
		
	}
	
	public void userMenu(int accountNumber) {
		while(true) {
			try {
			System.out.println("Welcome");
			System.out.println("Press 1 to Deposit");
			System.out.println("Press 2 to Withdraw");
			System.out.println("Press 3 to Check Balance");
			System.out.println("Press 4 to Change Phone Number");
			System.out.println("Press 5 to Change Email");
			System.out.println("Press 6 to Change Your Password");
			System.out.println("Press 7 to Log Out");
			System.out.println("Select Your Choice: ");
			
			int choice= sc.nextInt();
			AccountControllerInterface accountController= new AccountController();
			switch (choice) {
			case 1: {
				System.out.println("Enter the amount you want to deposit");
				double amount= sc.nextDouble();
				double newBal= accountController.deposit(accountNumber, amount);
				System.out.println("Deposit Successfull");
				System.out.println("Your new Balance is: "+ newBal);
				break;
				
			}
			case 2:{
				System.out.println("Enter the amount you want to withdraw...");
				double amount= sc.nextDouble();		
				boolean checkWithdraw= accountController.withdraw(accountNumber, amount);
				
				if(checkWithdraw) {
					System.out.println("Withdrawal Successsfull!!");
					System.out.println("Your new Balance is: "+ accountController.checkBal(accountNumber));
				}
				else {
					System.out.println("Your Current Balance is: "+ accountController.checkBal(accountNumber));
				}
				break;
			}
			case 3:{
				System.out.println("Your Balance is: " + accountController.checkBal(accountNumber));
				break;
			}
			case 4:{
				System.out.println("Enter your new Phone Number");
				long phoneNumber= sc.nextLong();
				System.out.println("Your new Phone Number is: "+ accountController.changePhoneNumber(accountNumber, phoneNumber));
				break;
			}
			case 5:{
				System.out.println("Enter your new Email");
				String email= sc.next();
				System.out.println("Your new Email is: "+ accountController.changeEmail(accountNumber, email));
				break;
			}
			case 6:{
				System.out.println("Enter Your Old Password");
				String oldPassword= sc.next();
				System.out.println("Enter Your New Password");
				String newPassword= sc.next();
				boolean result= accountController.changePassword(accountNumber, oldPassword, newPassword);
				if(result) {
					System.out.println("Password Changed Successfully");
					break;
				}

			}
			
			case 7:{
				System.out.println("Logged out Successfully");
				return;
			}
			default:
				try {
					throw new InvalidOptionException();
				}
				catch(InvalidOptionException e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				}
				break;
			}
			}
			catch(InputMismatchException e) {
				System.out.println("Please Enter a Valid Input");
				sc.nextLine();
			}
		}
	}

}
