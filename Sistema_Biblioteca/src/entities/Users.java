package entities;

public class Users {

	private String userName;
	private String userId;
	private String userCellphone;
	private Boolean userAvailable;
	
	public Users(String userName, String userId, String userCellphone) {
		
		this.userName = userName;
		this.userId = userId;
		this.userCellphone = userCellphone;
		this.userAvailable = true;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserCellphone() {
		return userCellphone;
	}
	
	public Boolean getUserAvailable() {
		return userAvailable;
	}

	public void setUserAvailable(Boolean userAvailable) {
		this.userAvailable = userAvailable;
	}

	public String toString() {
		return "--------------------------\n" +
				"User name: " + userName + "\n" + 
				"User ID: " + userId + "\n" +
				"User cellphone: " + userCellphone + "\n" +
				"User Status: " + userAvailable +"\n";
				
	}
	
}
