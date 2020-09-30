package fm;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{

	String userName;
	int iD;
	String passWord;
	boolean allowAnonymous;
	
	ArrayList<Object> toOthers;
	ArrayList<Object> fromOthers;
	
	

	
	User(String userName, String passWord, boolean allowAnonymous) {
		this.userName = userName;
		this.passWord = passWord;	
		this.allowAnonymous = allowAnonymous;
		Signing.generateRandomUserID(this);
	}

	

	
	
}
