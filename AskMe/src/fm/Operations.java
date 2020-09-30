package fm;

import java.util.ArrayList;
import java.util.Scanner;

public class Operations {
	
	public static void main (String[] args) {
		User u1 = new User("kkk12", "kw15", true);
		User u2 = new User("tmora4", "tm15", true);
		/*
		Question q1 = new Question("how are you", u1, u2);
		q1.answer = "alhamdolillah";
		
		Question q2 = new Question("how is your brother", u1, u2);
		q2.answer = "alhamdolillah bkhir";

		
		Question q3 = new Question("how do you start", u1, u2);

		Question q4 = new Question("what is here", u1, u2);

		Question q5 = new Question("can i ask more", u1, u2);
		q5.answer = "yes";
		
		Question q6 = new Question("can i go there ", u1, u2);

		Question q7 = new Question("can i ask you about your self", u1, u2);

		Question q8 = new Question("do you have books", u1, u2);

		Question q9 = new Question("have you seen me before", u1, u2);

		
		Thread t1 = new Thread();
		
		t1.parentQuestion = q1;
		ArrayList<Question> list1 = new ArrayList<Question>();
		
		list1.add(q2);
		list1.add(q3);
		list1.add(q4);
		t1.questions = list1;
		
		Thread t2 = new Thread();
		
		t2.parentQuestion = q5;
		ArrayList<Question> list = new ArrayList<Question>();
		
		list.add(q6);
		list.add(q7);
		t2.questions = list;
		

		ArrayList<Object> mainList = new ArrayList<Object>();
		mainList.add(t2);
		mainList.add(q9);
		mainList.add(t1);
		mainList.add(q8);


		printAll(mainList);
		
		printAnsweredOnly(mainList);
		//printThread(t1);
		
//		printAnsweredThread(t1);
		
		//printQuestion(q1);
		
		//printQuestion(q2);
*/
		askQuestion(u1);
		
		

		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static Scanner input = new Scanner(System.in);
	
	public static void printFeed(ArrayList<Object> qList) {
		for (int i = 0; i < qList.size(); i++) {
			//System.out.print((i + 1) + " - ");
			if (qList.get(i) instanceof Question)
				printFQ((Question) qList.get(i));
			else if (qList.get(i) instanceof Thread)
				printFT((Thread) qList.get(i));
		}
	}
	
	private static void printFQ(Question q) {
		
		System.out.printf("Question Id (%d) from user id(%d) to user id(%d)    ", q.iD, q.asker.iD, q.askedTo.iD);
		
		System.out.printf("Question : %s\n", q.question);
		if (q.answer != null)
			System.out.printf("\t\t        Answer : %s\n", q.answer);
	}

	private static void printFT(Thread t) {
		

		System.out.printf("Question Id (%d) from user id(%d) to user id(%d)      ", t.parentQuestion.iD, t.parentQuestion.asker.iD,  t.parentQuestion.askedTo.iD);
			
		System.out.printf("Question : %s\n", t.parentQuestion.question);
		if (t.parentQuestion.answer != null)
			System.out.printf("\t\t        Answer : %s\n", t.parentQuestion.answer);
		
		
		
		if (t.questions != null) {
			for (int  i = 0; i < t.questions.size(); i++) {
				System.out.printf("\tthread :  Question Id (%d) from user(%d)      ", t.questions.get(i).iD, t.questions.get(i).asker.iD,  t.questions.get(i).askedTo.iD);
				System.out.printf("Question : %s\n", t.questions.get(i).question);
				if (t.questions.get(i).answer != null)
					System.out.printf("\t\t\t        Answer : %s\n", t.questions.get(i).answer);
			}	
			
		}
	}
	
	private static void printQuestion(Question q) {
		
		System.out.printf("Question Id (%d) from user id(%d)      ", ((Question) q).iD, ((Question) q).asker.iD);
		
		System.out.printf("Question : %s\n", ((Question) q).question);
		if (((Question) q).answer != null)
			System.out.printf("\t\t        Answer : %s\n", ((Question) q).answer);
	}
	
	private static void printThread(Thread t) {
		
		System.out.printf("Question Id (%d) from user id(%d)      ", t.parentQuestion.iD, t.parentQuestion.asker.iD);
			
		System.out.printf("Question : %s\n", t.parentQuestion.question);
		if (t.parentQuestion.answer != null)
			System.out.printf("\t\t        Answer : %s\n", t.parentQuestion.answer);
		
		
		
		if (t.questions != null) {
			for (int  i = 0; i < t.questions.size(); i++) {
				System.out.printf("\tthread :  Question Id (%d) from user(%d)      ", t.questions.get(i).iD, t.questions.get(i).asker.iD);
				System.out.printf("Question : %s\n", t.questions.get(i).question);
				if (t.questions.get(i).answer != null)
					System.out.printf("\t\t\t        Answer : %s\n", t.questions.get(i).answer);
			}	
			
		}
	}
		
	private static void printAnsweredQuestion(Question q) {
		if (q.answer != null) {
			System.out.printf("Question Id (%d) from user id(%d)      ", ((Question) q).iD, ((Question) q).asker.iD);
			
			System.out.printf("Question : %s\n", ((Question) q).question);
			if (((Question) q).answer != null)
				System.out.printf("\t\t        Answer : %s\n", ((Question) q).answer);
		}
	}
	
	private static void printAnsweredThread(Thread t) {
		System.out.printf("Question Id (%d) from user id(%d)      ", t.parentQuestion.iD, t.parentQuestion.asker.iD);
		
		System.out.printf("Question : %s\n", t.parentQuestion.question);
		if (t.parentQuestion.answer != null)
			System.out.printf("\t\t        Answer : %s\n", t.parentQuestion.answer);
		
		
		boolean hasTail = false;
		
		for (int i = 0; i < t.questions.size(); i++)
			if (t.questions.get(i).answer != null) {
				hasTail = true;
				break;
			}
		
		
		if(hasTail) {
			for (int  i = 0; i < t.questions.size(); i++) {
				if (t.questions.get(i).answer != null) {
					System.out.printf("\tthread :  Question Id (%d) from user(%d)      ", t.questions.get(i).iD, t.questions.get(i).asker.iD);
					System.out.printf("Question : %s\n", t.questions.get(i).question);
					System.out.printf("\t\t\t        Answer : %s\n", t.questions.get(i).answer);
					}	
				}
				
			
		}
		
		
	}
	
	private static ArrayList<Object> answeredQuestions(ArrayList<Object> fromOther) {
		// returns arrayList of Questions can ask after 
		// to be runned with the method that shows the answered question
		ArrayList<Object> toBeReturned = new ArrayList<Object>();
		
		for (int i = 0; i < fromOther.size(); i++) {
			if (fromOther.get(i) instanceof Question && ((Question) fromOther.get(i)).answer != null)
				continue;
			else
				toBeReturned.add(fromOther.get(i));
		}	
		return toBeReturned;
	}
		
	public static void printAll(ArrayList<Object> qList) {
		// answered and not answered
		for (int i = 0; i < qList.size(); i++) {
			System.out.print((i + 1) + " - ");
			if (qList.get(i) instanceof Question)
				printQuestion((Question) qList.get(i));
			else if (qList.get(i) instanceof Thread)
				printThread((Thread) qList.get(i));
		}
		
	}
	
	private static void printAnsweredOnly(ArrayList<Object> qList) {
		for (int i = 0; i < qList.size(); i++) {
		//	System.out.print((i + 1) + " - ");
			if (qList.get(i) instanceof Question)
				printAnsweredQuestion((Question) qList.get(i));
			else if (qList.get(i) instanceof Thread)
				printAnsweredThread((Thread) qList.get(i));
		}
	}
	
	private static boolean qOrThread(ArrayList<Object> list) {
		for (int i = 0; i < list.size(); i++)
			if (!(list.get(i) instanceof Question) || (list.get(i) instanceof Question) && ((Question) list.get(i)).answer != null)
				return true;
		return false;
	}
	
	private static ArrayList<Object> qOrThreadList(ArrayList<Object> list) {
		ArrayList<Object> qOrThreadList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) instanceof Thread || (list.get(i) instanceof Question) && ((Question) list.get(i)).answer != null)
				qOrThreadList.add(list.get(i));
		return qOrThreadList;
	}
	
	public static void askQuestion(User u1) {
		
		printSystemUsers(u1.iD);
		System.out.println("enter the id of the user you want to ask");
		int iD = input.nextInt();
		
		ArrayList<User> list = withFiles.readSystemUsers();
		
		String u2Name = null;
		
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).iD == iD) {
				u2Name = list.get(i).userName;
				break;
			}
		User u2 = withFiles.loadUser(u2Name);
		
		int num = 1;
		if (u2.fromOthers != null && qOrThread(u2.fromOthers)) {
			System.out.println("enter 1 for new Question");
			System.out.println("enter 2 for threaded Question");
			num = input.nextInt();
		}
		
		if (num == 1) {
			System.out.println("enter your question");
			String questionText = input.next();
			Question q = new Question(questionText, u1, u2);
			
			if (u1.toOthers == null)
				u1.toOthers = new ArrayList<Object>();
			u1.toOthers.add(q);
			if (u2.fromOthers == null)
				u2.fromOthers = new ArrayList<Object>();
			u2.fromOthers.add(q);
		}
		else if (num == 2) {
			ArrayList<Object> qOrTList = qOrThreadList(u2.fromOthers);
			printAnsweredOnly(qOrTList);
			//ArrayList<Object> qList = answeredQuestions(u2.fromOthers);
			System.out.println("enter number of Question you want");

			int index = input.nextInt() - 1;
			
			System.out.println("enter the Question you want");
			String questionText = input.next();
			Question q = new Question(questionText, u1, u2);
			
			Object question = qOrTList.get(index);
			
			if (question instanceof Question) {
				Thread t = new Thread();
				t.parentQuestion = (Question) question;
				ArrayList<Question> tail = new ArrayList<Question>();
				tail.add(q);
				t.questions = tail;
				int in = u2.fromOthers.indexOf(question);
				u2.fromOthers.remove(question);
				u2.fromOthers.add(in, t);
				u1.toOthers.add(q);
			}
			else if (question instanceof Thread) {
				((Thread) question).questions.add(q);
			}
			
		}
		
		withFiles.writeUser(u1);
		withFiles.writeUser(u2);	
	}
	
	public static void answerQuestion(User u) {
		// answer Question from which asked to me
		
		printAll(u.fromOthers);
		
		System.out.println("enter the number of Question you want to answer");
		int n = input.nextInt() - 1;
		
		Object o = u.fromOthers.get(n);
		Question toBeAnswered = null;
		
		if (o instanceof Question) {
			toBeAnswered = (Question) o;
			
			System.out.println("enter your answer");
			String ans = input.next();
			toBeAnswered.answer = ans;
			withFiles.addFeed(toBeAnswered);
		}
		else {
			printThread((Thread) o);
			System.out.println("enter the number of Question you want to answer");
			int num = input.nextInt() - 2;
			ArrayList<Question> qList = ((Thread) o).questions;
			
			if (num == -1)
				toBeAnswered = ((Thread) o).parentQuestion;
			else 
				toBeAnswered = qList.get(num);
			
			
			if (toBeAnswered.answer != null) {
				System.out.println("enter your answer update");
				String ans = input.next();
				toBeAnswered.answer = ans;	
			}
			else {
				toBeAnswered = qList.remove(num);
				System.out.println("enter your answer");
				String ans = input.next();
				toBeAnswered.answer = ans;
				qList.add(toBeAnswered);
				//((Thread) o).questions = qList;		
			}
			withFiles.addFeed(o);

		}
		withFiles.writeUser(u);
		withFiles.writeUser(toBeAnswered.asker);

	}
	
	public static void printSystemUsers(int iD) {
		ArrayList<User> list = withFiles.readSystemUsers();
		
		if (iD == -1) {
			for (int i = 0; i < list.size(); i++) {
				User u = list.get(i);
				System.out.println((i + 1) + "-" + u.userName + " and ID (" + u.iD + ")");
				
			}
		}
		else {
			int j = 0;
			for (int i = 0; i < list.size(); i++) {
				User u = list.get(i);
				if (u.iD == iD)
					continue;
				System.out.println((++j) + "-" + u.userName + " and ID (" + u.iD + ")");
				
			}
		}
		
	}
	
	public static void deleteQuestion(User u) {
		
		ArrayList<Object> list = u.fromOthers;
		ArrayList<Object> feed = withFiles.readFeed();

		printAll(list);
		
		System.out.println("enter number from 1 to " + list.size());
		int qChoice = input.nextInt() - 1;
		Object o = list.get(qChoice);
		int in = feed.indexOf(o);
		feed.remove(o);
		
		
		if (o instanceof Question) 
			list.remove(qChoice);
		else {
			printThread((Thread) o);
			System.out.println("enter number -1 to delete parent Question ");
			System.out.println("enter number from 1 to " + ((Thread) o).questions.size() + " to delete a threaded question");
			
			int tChoice = input.nextInt() - 1;
			
			if (tChoice == -2) 
				list.remove(qChoice);			
			else {
				((Thread) o).questions.remove(tChoice);
				feed.add(in, o);
			}
			
		}
		
		withFiles.writeUser(u);

		
		
		
		
		

		
	}
	
	
	
	

}
