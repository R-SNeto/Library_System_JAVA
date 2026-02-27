package entities;

public class Books {

	private String bookTitle;
	private String bookWriter;
	private String bookId;
	private Boolean bookAvailable;
	
	public Books(String bookTitle, String bookWriter, String bookId) {
		this.bookTitle = bookTitle;
		this.bookWriter = bookWriter;
		this.bookId = bookId;
		this.bookAvailable = true;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookWriter() {
		return bookWriter;
	}
	
	public String getBookId() {
		return bookId;
	}

	public Boolean getBookAvailable() {
		return bookAvailable;
	}

	public void setBookAvailable(Boolean bookAvailable) {
		this.bookAvailable = bookAvailable;
	}

	@Override
	public String toString() {
		return "--------------------------\n" +
				"Book title: " + bookTitle + "\n" + 
				"Book writer: " + bookWriter + "\n" +
				"Book ID: " + bookId + "\n" +
				"Book Status: " + bookAvailable +"\n";
				
	}
	
	
	
	
	
	
	
}
