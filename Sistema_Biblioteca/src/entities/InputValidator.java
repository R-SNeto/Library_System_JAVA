package entities;

import java.util.Scanner;

public final class InputValidator {
	
	//Methods that ask if the user want continue or stop but in different situations
	
	public boolean askYesOrNot(Scanner sc) {
		while(true) {
			System.out.print("Do you want repeat the process [Y/N]? ");
			String YN = sc.nextLine().toUpperCase().trim();
			
			if(YN.equals("N")) {
				System.out.println("\nReturning to menu...\n");
				return false;
			}else if(YN.equals("Y")) {
				return true;
			}else {
				System.out.println("\nWrong option, try again\n");
			}
		}
	}
	
	public boolean confirmYesOrNot(Scanner sc) {
		while(true) {
			System.out.print("Do you really want do this [Y/N]? ");
			String YN = sc.nextLine().toUpperCase().trim();
			
			if(YN.equals("N")) {
				System.out.println("\nReturning to menu...\n");
				return false;
			}else if(YN.equals("Y")) {
				return true;
			}else {
				System.out.println("\nWrong option, try again\n");
			}
		}
		
	}
	
}
