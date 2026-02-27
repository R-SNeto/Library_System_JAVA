package menu;

import java.util.Scanner;

import entities.InputValidator;
import services.BookServices;
import services.LoanServices;
import services.UserServices;

public class MainUI {
	
	private final Scanner sc;
	private final InputValidator validator;
	private final UserServices userService;
	private final BookServices bookService;
	private final LoanServices loanService;
	private final BooksUI booksUi;
	private final UsersUI userUi;
	private final LoansUI loanUi;
	
	public MainUI() {
		this.sc = new Scanner(System.in);
		this.validator = new InputValidator();
		this.bookService = new BookServices(validator);
		this.userService = new UserServices(validator);
		this.loanService = new LoanServices(validator);
		this.booksUi = new BooksUI(sc, validator, bookService);
		this.userUi = new UsersUI(sc, validator, userService);
		this.loanUi = new LoansUI(sc, validator, userService, bookService, loanService);
	}
	//Starts the main menu
	public void startProgram() {
		mainMenu();
	}

	public void mainMenu() {
		
		boolean activeSystem = true;
		
		while(activeSystem) {
			
			System.out.println("  DE LA CRUZ LIBRARY ");
			System.out.println("---------------------");
			System.out.println("[1] Books menu");
			System.out.println("[2] Users menu");
			System.out.println("[3] Loans menu");
			System.out.println("[4} Exit the application");
			System.out.println("---------------------");
			System.out.print("Select an option: ");
			
			int option = Integer.parseInt(sc.nextLine());
			
			switch(option) {
			case 1: 
				this.booksUi.showBookMenu();
				break;
			case 2: 
				this.userUi.showUserMenu();
				break;
			case 3: 
				this.loanUi.showLoanMenu();
				break;
			case 4: 
				System.out.println("\nLeaving from the application...\n");
				activeSystem = false;
				break;
			default:
				System.out.println("\nInvalid option, choose again:\n ");
				break;
			}
		}
		sc.close();
	}	
}
