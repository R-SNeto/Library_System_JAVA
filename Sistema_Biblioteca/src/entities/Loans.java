package entities;

import java.time.LocalDateTime;

public class Loans {
	
	private String userId;
	private String bookId;
	private LocalDateTime loanDate;
	private LocalDateTime returnDate;
	
	public Loans(String userId, String bookId, LocalDateTime loanDate) {
		this.userId = userId;
		this.bookId = bookId;
		this.loanDate = loanDate;
		this.returnDate = this.loanDate.plusDays(7);
	}

	
	
	public String getUserId() {
		return userId;
	}
	public String getBookId() {
		return bookId;
	}
	public LocalDateTime getLoanDate() {
		return loanDate;
	}

	@Override
	public String toString() {
		return "--------------------------\n" 
				+ "User ID: " +  userId + "\n" 
				+ "Book ID: " + bookId + "\n"
				+ "Loan date: " + loanDate + "\n"
				+ "Return date: " + returnDate;
	}

	

	
	
	
}
