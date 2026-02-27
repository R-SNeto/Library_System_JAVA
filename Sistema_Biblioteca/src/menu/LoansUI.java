package menu;

import java.time.LocalDateTime;
import java.util.Scanner;

import entities.InputValidator;
import entities.Loans;
import services.BookServices;
import services.LoanServices;
import services.UserServices;

public class LoansUI {
	
	private final Scanner sc;
	private final InputValidator validator;
	private final UserServices userService;
	private final BookServices bookService;
	private final LoanServices loanService;
	
	public LoansUI(Scanner sc, InputValidator validator, UserServices userService, BookServices bookService, LoanServices loanService) {
		this.sc = sc;
		this.validator = validator;
		this.userService = userService;
		this.bookService = bookService;
		this.loanService = loanService;
	}

	public void showLoanMenu() {
		
		while(true) {
			System.out.println("        LOANS MENU         ");
			System.out.println("---------------------------");
			System.out.println("[1] Register loan");
			System.out.println("[2] Return book");
			System.out.println("[3] Check loans");
			System.out.println("[4] Return to library menu");
			System.out.println("---------------------------");
			System.out.print("Select an option: ");
			int option = Integer.parseInt(sc.nextLine());
			System.out.println();

			switch(option) {
			case 1: 
				showRegisterLoanMenu();
				break;
			case 2:
				showReturnBookMenu();
				break;
			case 3:
				showLoanList();
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
	
	public void showRegisterLoanMenu() {
		
		while(true) {

			if(userService.checkUserListStatus()) {
				System.out.println("User list is empty");
				break;
			}
			
			if(bookService.checkBookListStatus()) {
				System.out.println("Book list is empty");
				break;
			}
			
			System.out.println("     REGISTER LOANS    ");
			System.out.println("-----------------------");
			System.out.print("Enter the user ID: ");
			String userId = sc.nextLine();

			while(!userService.userIdDuplicated(userId)) {
				System.out.print("This user doesn't exist, try again [type 0 to leave]: ");
				userId = sc.nextLine();
				if(userId.equals("0")) {
					return;
				}
			}
			
			if(!userService.checkUserStatus()) {
				System.out.println("This user already have an outstanding loan");
				return;
			}
			
			System.out.print("Enter the bookd ID: ");
			String bookId = sc.nextLine();
			
			while(!bookService.bookIdDuplicated(bookId)) {
				System.out.print("This book doesn't exist, try again [type 0 to leave]: ");
				bookId = sc.nextLine();
				if(bookId.equals("0")) {
					return;
				}
			}
			
			if(!bookService.checkBookStatus()) {
				System.out.println("This book isn't available");
				return;
			}
			
			System.out.println("\nBook registered sucessfully\n");
			
			Loans loan = new Loans(userId, bookId, LocalDateTime.now());
			
			loanService.registerLoan(loan);

			if(!validator.askYesOrNot(sc)) break;	
		}
	}
	public void showReturnBookMenu() {
		
		while(true) {
			
			if(loanService.checkLoanListStatus()) {
				System.out.println("\nList is empty, returning to menu...\n");
			}

			System.out.println("     Return a Book     ");
			System.out.println("-----------------------");
			System.out.print("Enter the user ID: ");
			String userId = sc.nextLine();

			while(!loanService.checkUserInList(userId)) {
				System.out.print("This user doesn't exist in the list, try again [type 0 to leave]:");
				if(userId.equals("0")) {
					return;
				}
			}

			System.out.print("Enter the book ID: ");
			String bookId = sc.nextLine();

			while(!loanService.checkBookInList(bookId)) {
				System.out.print("This book doesn't exist in the list, try again [type 0 to leave]: ");
				if(bookId.equals("0")) {
					return;
				}
			}

			loanService.removeLoan(sc, userId, bookId);

			if(!validator.askYesOrNot(sc)) break;	
		}
	}
	public void showLoanList() {
		System.out.println("    LOAN LIST   ");
		
		if(loanService.checkLoanListStatus()) {
			System.out.println("-----------------");
			System.out.println("      EMPTY      ");
		} else {
			loanService.showLoanList();
		}
	}
}
