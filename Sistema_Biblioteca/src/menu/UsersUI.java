package menu;

import java.util.Scanner;

import entities.InputValidator;
import entities.Users;
import services.UserServices;

public class UsersUI {
	
	private final Scanner sc;
	private final InputValidator validator;
	private final UserServices userService;

	public UsersUI(Scanner sc, InputValidator validator, UserServices userService) {
		this.sc = sc;
		this.validator = validator;
		this.userService = userService;
	}
	
	public void showUserMenu() {
		
		while(true) {

			System.out.println("        USER MENU         ");
			System.out.println("---------------------------");
			System.out.println("[1] Register user");
			System.out.println("[2] Remove user");
			System.out.println("[3] View registred user");
			System.out.println("[4] Return to library menu");
			System.out.println("---------------------------");
			System.out.print("Select an option: ");
			int option = Integer.parseInt(sc.nextLine());
			System.out.println();

			switch(option) {
			case 1: 
				showRegisterUserMenu();
				break;
			case 2:
				showRemoveUserMenu();
				break;
			case 3:
				showUserList();
				break;
			case 4:
				System.out.println("Returning to library menu...\n\n");
				return;
			default:
				System.out.println("Wrong option, try again ");
				break;
			}
		}	
	}
	public void showRegisterUserMenu() {
		
		while(true) {

			System.out.println("     REGISTER USERS    ");
			System.out.println("-----------------------");
			System.out.print("Enter the user name: ");
			String userName = sc.nextLine();
			System.out.print("Enter the user ID: ");
			String userId = sc.nextLine();

			while (userService.correctUserIdLength(userId)) {
				System.out.print("Invalid user ID length, try again [type 0 to leave]: ");
				userId = sc.nextLine();
				if(userId.equals("0")) {
					return;
				}
			}

			while(userService.userIdDuplicated(userId)) {
				System.out.print("Already have a user with this ID, try again [type 0 to leave]: ");
				userId = sc.nextLine();
				if(userId.equals("0")) {
					return;
				}
			}
			
			
			System.out.print("Enter the user cellphone: ");
			String userCellphone = sc.nextLine();
			
			System.out.println("\nUser registered sucessfully\n");

			Users users = new Users(userName, userId, userCellphone);
			
			userService.registerUser(users);

			if(!validator.askYesOrNot(sc)) break;	
		}
	}
	public void showRemoveUserMenu() {
		while(true) {

			if(userService.checkUserListStatus()) {
				System.out.println("\nList is empty, returning to menu...\n");
				return;
			}

			System.out.println("      REMOVE USERS     ");
			System.out.println("-----------------------");
			System.out.print("Enter user ID: ");
			String userId = sc.nextLine().trim();

			//Verify that the book exists
			while(!userService.userIdDuplicated(userId)) {
				System.out.println("This user doesn't exist, try again [type 0 to leave]:");
				System.out.print("User ID: ");
				userId = sc.nextLine().trim();
				if(userId.equals("0")) {
					return;
				}
			}
			//Confirm that the user really wants to delete the book
			if(userService.removeUser(sc, userId)) {
				System.out.println("\nUser removed sucessfully\n");
				if(!validator.askYesOrNot(sc)) {
					break;
				}
			}else {
				System.out.println("\nUser kept\n");
				return;
			}
		}
	}
	public void showUserList() {
		System.out.println("    USER LIST    ");
		
		if(userService.checkUserListStatus()) {
			System.out.println("-----------------");
			System.out.println("      EMPTY      ");
		} else {
			userService.showUserList();
		}
		
	}
}
