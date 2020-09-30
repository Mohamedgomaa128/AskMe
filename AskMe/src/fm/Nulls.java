package fm;

import java.util.ArrayList;

public class Nulls {
	
public static void askQuestion(User u1) {
		
		printSystemUsers();
		System.out.println("enter the id of the user you want to ask");
		int iD = input.nextInt();
		
		ArrayList<User> list = withFiles.readSystemUsers();
		
		User u2 = null;
		
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).iD == iD) {
				u2 = list.get(i);
				break;
			}
		
		// you may put here choices if he needs anonymous
		int num = 1;
		if (u2.fromOthers != null || u2.fromOthers.size() != 0) {
			System.out.println("enter 1 for new Question");
			System.out.println("enter 2 for threaded Question");
			num = input.nextInt();
		}
		
		
		
		
		if (num == 1) {
			System.out.println("enter your question");
			String questionText = input.next();
			Question q = new Question(questionText, u1, u2);
			Thread t = new Thread();
			t.parentQuestion = q;
			if (u1.toOthers == null)
				u1.toOthers = new ArrayList<Thread>();
			u1.toOthers.add(t);
			if (u2.fromOthers == null)
				u2.fromOthers = new ArrayList<Thread>();
			u2.fromOthers.add(t);
			
		}
		else if (num == 2) {
			printQuestionsList(u2.fromOthers);
			System.out.println("enter the index of Question you want");
			int index = input.nextInt();
			
			System.out.println("enter the Question you want");
			String questionText = input.next();
			Question q = new Question(questionText, u1, u2);
			Thread t = u2.fromOthers.get(index);
			
			if (t.questions == null || t.questions.isEmpty()) {
				t.questions = new ArrayList<Question>();
				t.questions.add(q);
			}
			else 
				t.questions.add(q);	
		}
		
		withFiles.writeUser(u1);
		withFiles.writeUser(u2);
		
		
		
		
		
	}



private static void printAnsweredQuestions(Thread t) {
	
	if (t.parentQuestion.answer != null) {
		System.out.printf("Question Id (%d) from user id(%d)      ", t.parentQuestion.iD, t.parentQuestion.asker.iD);
	
		System.out.printf("Question : %s\n", t.parentQuestion.question);
		
		System.out.printf("\t\t        Answer : %s\n", t.parentQuestion.answer);
	}
		
	
	
	if (t.questions != null) {
		
	}
	
	
	
	System.out.println();
	
	
			
	
}






/*private static void printQuestions(Thread t) {
	 
	System.out.printf("Question Id (%d) from user id(%d)      ", t.parentQuestion.iD, t.parentQuestion.asker.iD);
		
	System.out.printf("Question : %s\n", t.parentQuestion.question);
	if (t.parentQuestion.answer != null)
		System.out.printf("\t\t        Answer : %s\n", t.parentQuestion.answer);
	
	
	
	if (t.questions != null) {
		for (int  i = 0; i < t.questions.size(); i++) {
			System.out.printf("thread :  Question Id (%d) from user(%d)      ", t.questions.get(i).iD, t.questions.get(i).asker.iD);
			System.out.printf("Question : %s\n", t.questions.get(i).question);
			if (t.questions.get(i).answer != null)
				System.out.printf("\t\t        Answer : %s\n", t.questions.get(i).answer);
		}	
		
	}
	System.out.println();
			
}*/

/*public static void printQuestionsList(ArrayList<Thread> l) {
	// for "from Others" and "to Others" and "feed"
	ArrayList<Thread> list = l;
	
	for (int i = 0; i < list.size(); i++)
		printQuestions(list.get(i));
}*/

/*public static void updateFeed(User loggedIn, Thread t) {
	// iterate over my followers and add my question or my answer to their feed
	
	ArrayList<User> list = loggedIn.followedBy;

	for (int i = 0; i < list.size(); i++) {
		 User u = list.get(i);
		 u.feed.add(t); 
	}
}*/




}

