package controller;

import model.User;

public interface UserControllerInterface {
	boolean registerUser(User user);
	
	boolean loginUser(int accountNumber,String pwd);
}
