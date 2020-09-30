package fm;

import java.io.Serializable;

public class Question implements Serializable {
	 int iD;
	 String question;
	 String answer;
	 User asker;
	 User askedTo;
	 
	 public Question(String q, User a, User at) {
		 question = q;
		 asker = a;
		 askedTo = at;
		 Signing.generateQuestionID(this);
	}
	
}