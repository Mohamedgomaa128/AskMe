package fm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Signing {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
	}
	
/*	public static User signIn(String userName, String passWord, boolean anonymous) {
		User u = new User(userName, passWord, anonymous);
		generateRandomID(u);
		
		File f = new File("users\\" + userName);
		
		
		return u;
	}*/
	
	public static User signUp() {
		
		
		File f = new File("users");
		f.mkdir();
		
		
		System.out.print("enter the Username: ");
		String userName = input.next();
		
		System.out.print("enter the password: ");
		String passWord = input.next();
		
		
		
		System.out.print("Do you want to recieve anonymous questions ? ");
		System.out.print("enter 1 for yes ");
		System.out.print("enter 2 for no ");


		
		int anonymousQ = input.nextInt();
		
		// no or other words
		boolean anonymous = (anonymousQ == 1 ? true : false);
		
		
		
		User u = new User(userName, passWord, anonymous);
		generateRandomUserID(u);
		withFiles.writeUser(u);	
		withFiles.addSystemUser(u);
		
		return u;
		
		
	}
	
	public static User logIn() {
		
		System.out.print("enter the Username: ");
		String userName = input.next();
		
		User u = withFiles.loadUser(userName);

		// to be checked
		//System.out.print("enter the password: ");
		//String passWord = input.next();
		
		
		return u;	
	}
	
	public static void logOut(User u) {
		withFiles.writeUser(u);
	}
	
	public static void generateRandomUserID(User u) {
		ArrayList<Integer> list = withFiles.readUsersIds();
		int iD = validID(list);
		u.iD = iD;
		withFiles.addUserId(iD);
	}
	
	public static void generateQuestionID(Question q) {
		ArrayList<Integer> list = withFiles.readQuestionsIds();
		int iD = validID(list);
		q.iD = iD;
		withFiles.addquestionId(iD);
	}
	
	private static boolean isValidUserName(String userName) {
		File f = new File("users\\" + userName);

		if (f.exists())
			return false;
		return true;
	}
	
	private static int validID(ArrayList<Integer> list) {
		if (list == null)
			return (int)(Math.random() * 1000);
		else {
			while(true) {
				Integer rand = (int)(Math.random() * 1000);
				if (!list.contains(rand))
					return rand;
			}
		}
			
		
	}
	
}
