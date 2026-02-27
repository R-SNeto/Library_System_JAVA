package menu;

import java.util.Scanner;

import entities.Books;
import entities.InputValidator;
import services.BookServices;

public class BooksUI {
	
	private final Scanner sc;
	private final InputValidator validator;
	private final BookServices bookService;
	
	public BooksUI(Scanner sc, InputValidator validator, BookServices bookService) {
		this.sc = sc;
		this.validator = validator;
		this.bookService = bookService;
	}

	public void showBookMenu() {
		
		while(true) {

			System.out.println("        BOOKS MENU         ");
			System.out.println("---------------------------");
			System.out.println("[1] Register books");
			System.out.println("[2] Remove books");
			System.out.println("[3] View registred books");
			System.out.println("[4] Return to library menu");
			System.out.println("---------------------------");
			System.out.print("Select an option: ");
			int option = Integer.parseInt(sc.nextLine());
			System.out.println();

			switch(option) {
			case 1: 
				showRegisterBookMenu();
				break;
			case 2:
				showRemoveBookMenu();
				break;
			case 3:
				showBookList();
				break;
			case 4:
				System.out.println("Returning to library menu...\n");
				return;
			default:
				System.out.println("Invalid option, choose again\n ");
				break;
			}

		}
	}
	
	public void showRegisterBookMenu() {
		
		while(true) {

			System.out.println("     Register Books    ");
			System.out.println("-----------------------");
			System.out.print("Enter book title: ");
			String bookTitle = sc.nextLine();
			System.out.print("Enter book writer: ");
			String bookWriter = sc.nextLine();

			//Check if there is a book with the same title and writer
			while(bookService.bookDuplicated(bookTitle, bookWriter)) {
				
				System.out.println("Already has a book with this title and writer registered. ");

				if(validator.confirmYesOrNot(sc)) {
					break;
				}

				System.out.print("Book title [type 0 to leave]: ");
				bookTitle = sc.nextLine();

				System.out.println("Book writer [type 0 to leave]: ");
				bookWriter = sc.nextLine();
				
				if(bookTitle.equals("0") || bookWriter.equals("0")) {
					return;
				}
			}

			System.out.print("Enter book ID [min 3, max 10]: ");
			String bookId = sc.nextLine().trim();

			//Check if the book has the correct length
			while(bookService.correctBookIdLength(bookId)) {
				System.out.print("Invalid book ID length, try again [type 0 to leave]: ");
				bookId = sc.nextLine().trim();
				if(bookId.equals("0")) {
					return;
				}
			}
			//Check if there is a book with the same ID
			while(bookService.bookIdDuplicated(bookId)) {
				System.out.println("Book ID already registered, try again [type 0 to leave]: ");

				System.out.print("Book ID: ");
				bookId = sc.nextLine().trim();
				if(bookId.equals("0")) {
					return;
				}
				
			}
			
			System.out.println("\nBook registered sucessfully\n");
			
			Books book = new Books(bookTitle, bookWriter, bookId);
			
			bookService.registerBook(book);
			
			if(!validator.askYesOrNot(sc)) break;
		}
	}
	
	public void showRemoveBookMenu() {
		
		while(true) {
			//Check if the book list is empty
			if(bookService.checkBookListStatus()) {
				System.out.println("\nList is empty, returning to menu...\n");
				return;
			}

			System.out.println("      REMOVE BOOKS     ");
			System.out.println("-----------------------");
			System.out.print("Enter book ID: ");
			String bookId = sc.nextLine().trim();

			//Verify that the book exists
			while(!bookService.bookIdDuplicated(bookId)) {
				System.out.println("This book doesn't exist, try again [type 0 to leave]:");
				System.out.print("Book ID: ");
				bookId = sc.nextLine().trim();
				if(bookId.equals("0")) {
					return;
				}
			}
			//Check if the book data is correct, if yes it will remove the book 
			if(bookService.removeBook(sc, bookId)) {
				System.out.println("\nBook removed sucessfully\n");
				if(!validator.askYesOrNot(sc)) {
					break;
				}
			}else {
				System.out.println("\nBook kept\n");
				return;
			}
		}
	}
	
	public void showBookList() {
		System.out.println("    BOOK LIST    ");
		//Check is the book list is empty
		if(bookService.checkBookListStatus()) {
			System.out.println("-----------------");
			System.out.println("      EMPTY      ");
		} else {
			bookService.showBookList();
		}
	}
	
}
