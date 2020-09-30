package fm;

import java.util.Scanner;

public class UI {

	public static void main(String[] args) {
		while (true) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("\t\t\t Welcome to AskMe ^_^");
		System.out.println("1.Sign up");
		System.out.println("2.Log in");
		System.out.println("3.Close AskMe");
		int choice1 = input.nextInt();
		User loggedIn = null;
		switch(choice1) {
			case 1 :
				loggedIn = Signing.signUp();
				break;
			case 2 :
				// you may recieve the user
				loggedIn = Signing.logIn();
				break;
			case 3 :
				System.out.println("thanks for your time ^_^");
				System.exit(0);
				break;
					
		}
		int choice2 = 0;
		while (choice2 != -1) {
			System.out.println("1.Print Questions To Me");
			System.out.println("2.Print Questions From Me");
			System.out.println("3.Answer Question");
			System.out.println("4.Delete Question");
			System.out.println("5.Ask Question");
			System.out.println("6.List System Users");
			System.out.println("7.Feed");
			System.out.println("8.Logout");
			choice2 = input.nextInt();
	
			switch(choice2) {
				case 1 :
					Operations.printAll(loggedIn.fromOthers);
					break;
				case 2 :
					// you may recieve the user
					Operations.printAll(loggedIn.toOthers);
					break;
				case 3 :
					Operations.answerQuestion(loggedIn);
					break;
				case 4 :
					Operations.deleteQuestion(loggedIn);
					break;
				case 5 :
					Operations.askQuestion(loggedIn);
					
					break;
				case 6 :
					Operations.printSystemUsers(-1);
					
					break;
				case 7 :
					//Operations.printQuestionsList(loggedIn.feed);
					
					break;
				case 8 :
					System.out.println("thanks for your time ^_^");
					System.exit(0);
					break;
					
			}
	}
		
		
		
		
		
		
		
		}
		
		
	}
}
