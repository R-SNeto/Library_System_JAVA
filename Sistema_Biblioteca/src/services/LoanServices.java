package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.InputValidator;
import entities.Loans;

public class LoanServices {
	
	private final List<Loans> loanList;
	private final InputValidator validator;
		
	public LoanServices(InputValidator validator) {
		this.loanList = new ArrayList<>();
		this.validator = validator;
	}
	
	//VALIDATION AREA
	
	//Check if the list is empty
	public boolean checkLoanListStatus() {
		if(loanList.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	//Check if the user is on the list
	public boolean checkUserInList(String userId) {
		for(int i = 0; i < loanList.size(); i++) {
			if (loanList.get(i).getUserId().equals(userId)) {
				return true;
			}
		}
		return false;
	}
	//Check if the book is on the list
	public boolean checkBookInList(String bookId) {
		for(int i = 0; i < loanList.size(); i++) {
			if (loanList.get(i).getBookId().equals(bookId)) {
				return true;
			}
		}
		return false;
	}
	
	//FUNCIONALITIES AREA
	
	//Register a loan
	public void registerLoan(Loans loan) {
		loanList.add(loan);
	}
	//Remove a loan
	public boolean removeLoan(Scanner sc, String userIdTyped, String bookIdTyped) {
		for (int i = 0; i < loanList.size(); i++) {
			if (loanList.get(i).getUserId().equals(userIdTyped) && loanList.get(i).getBookId().equals(bookIdTyped)) {
				if (validator.confirmYesOrNot(sc)) {
					loanList.remove(i);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	//Displays the loan list
	public void showLoanList() {
		for(Loans lList: loanList) {
			System.out.println(lList);
		}
	}
}
