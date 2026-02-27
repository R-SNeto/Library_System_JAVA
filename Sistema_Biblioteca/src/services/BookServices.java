package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Books;
import entities.InputValidator;

public class BookServices {
	
	private final List <Books> bookList;
	private final InputValidator validator;
	
	public BookServices(InputValidator validator) {
		this.bookList = new ArrayList<>();
		this.validator = validator;
	}
	//VALIDATION AREA
	
	//Check if there is a book with the same title and writer
	public boolean bookDuplicated(String bookTitleTyped, String bookWriterTyped) {
		for (int i = 0; i < bookList.size(); i++) {
			if(bookList.get(i).getBookTitle().equals(bookTitleTyped) && bookList.get(i).getBookWriter().equals(bookWriterTyped)) {
				return true;
			}
		}
		return false;
	}
	//Check if there is a book with the same ID.
	public boolean bookIdDuplicated(String bookIdTyped) {
		for(int i = 0; i < bookList.size(); i++) {
			if(bookList.get(i).getBookId().equals(bookIdTyped)) {
				return true;
			}
		}
		return false;
	}
	//Check if the book ID has the correct length
	public boolean correctBookIdLength(String bookIdTyped) {
		if(bookIdTyped.length() < 3 || bookIdTyped.length() > 10) {
			return true;
		}else {
			return false;
		}

	}
	//Check if the list is empty
	public boolean checkBookListStatus() {
		if(bookList.isEmpty()) {
			return true;
		}else {
			return false;
		}
		
	}
	//Check if the book is available for a loan
	public boolean checkBookStatus() {
		for(int i = 0; i < bookList.size(); i++) {
			if(bookList.get(i).getBookAvailable() == true) {
				return true;
			}
		}
		return false;
	}
	
	//FUNCIONALITIES AREA
	
	//Add a book to the list
	public void registerBook(Books books) {
		bookList.add(books);
	}
	//Remove a book from the list
	public boolean removeBook(Scanner sc, String bookIdTyped) {	
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getBookId().equals(bookIdTyped)) {
				if (validator.confirmYesOrNot(sc)) {
					bookList.remove(i);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	//View the book list
	public void showBookList() {
		for(Books bList: bookList) {
			System.out.println(bList);
		}
	}
}
