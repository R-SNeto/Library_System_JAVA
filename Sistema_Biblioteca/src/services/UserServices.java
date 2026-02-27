package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.InputValidator;
import entities.Users;

public class UserServices {
	
	private final List<Users> userList;
	private final InputValidator validator;
		
	public UserServices(InputValidator validator) {
		this.userList = new ArrayList<>();
		this.validator = validator;
		
	}
	
	//VALIDATION AREA
	
	//Check if the user ID has the correct length
	public boolean correctUserIdLength(String userIdTyped) {
		if(userIdTyped.length() < 3 || userIdTyped.length() > 10) {
			return true;
		}else {
			return false;
		}
	}
	//Check if there is a user with the same ID.
	public boolean userIdDuplicated(String userIdTyped) {
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserId().equals(userIdTyped)) {
				return true;
			}
		}
		return false;
	}
	//Check if the list is empty
	public boolean checkUserListStatus() {
		if(userList.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkUserStatus() {
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserAvailable() == true) {
				return true;
			}
		}
		return false;
	}
	
	//FUNCIONALITIES AREA
	
	//Add a user to the list
	public void registerUser(Users user) {
		userList.add(user);
	}
	//Remove a user from the list
	public boolean removeUser(Scanner sc, String userIdTyped) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserId().equals(userIdTyped)) {
				if (validator.confirmYesOrNot(sc)) {
					userList.remove(i);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	//View the user list
	public void showUserList() {
		for(Users uList: userList) {
			System.out.println(uList);
		}
	}
}
