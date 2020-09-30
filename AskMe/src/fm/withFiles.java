package fm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class withFiles {
	
	public static void main (String[] args) {
		//addUserId(101);
		//addUserId(500);
		Signing.signUp();
		
		ArrayList<Integer> arr = readUsersIds();
		
		for (Integer a : arr)
			System.out.println(a);
		
		
		
		
	}
	
 	public static ArrayList<Integer> readUsersIds(){
 		// reading users Ids
 		File f = new File("users\\usersIds.txt");
 		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
 		ArrayList<Integer> ids = null;
 		
 		try {
 		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
 		
 		
 		ids = (ArrayList<Integer>) ois.readObject();
 		
 		
 		}
 		catch (Exception e) {
 			//System.out.println(e.getMessage());
 			
 		}
		return ids;
 	}
 	
 	public static ArrayList<Integer> readQuestionsIds(){
 		// reading Questions IDs
 		File f = new File("users\\questionsIds.txt");
 		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
 		
 		ArrayList<Integer> ids = null;
 		
 		try {
 		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
 		
 		
 		ids = (ArrayList<Integer>) ois.readObject();
 		
 		
 		}
 		catch (Exception e) {
 			//System.out.println(e.getMessage());
 			
 		}
		return ids;
 	}

 	// you can merge between the two methods and make it save in the file with the passed list name 
 	public static void addquestionId(int userId) {
 		// adding a Question id to the file of questions
 	 	
 		try {
 			ArrayList<Integer> list = readQuestionsIds();
 			if (list == null)
 				list = new ArrayList<Integer>();
 			list.add(userId);
 	 		File f = new File("users\\questionsIds.txt");
 	 		
 	 		
 	 		
 	 		try {
 				f.createNewFile();
 			} catch (IOException e1) {
 				e1.printStackTrace();
 			}
 	 		
 	 		FileOutputStream fos = new FileOutputStream(f);
 			ObjectOutputStream oos = new ObjectOutputStream(fos);
 			
 			oos.writeObject(list);
 			
 			oos.flush();
 			oos.close();
 			fos.close();
 	 		}
 	 		catch (Exception e) {
 	 			//System.out.println(e.getMessage());
 	 			
 	 		} 	
 		
 	}
	
 	public static void addUserId(int userId) {
 	
 		try {
 			ArrayList<Integer> list = readUsersIds();
 			if (list == null)
 				list = new ArrayList<Integer>();
 			list.add(userId);
 	 		File f = new File("users\\usersIds.txt");
 	 		
 	 		try {
 				f.createNewFile();
 			} catch (IOException e1) {
 				e1.printStackTrace();
 			}
 	 		
 	 		FileOutputStream fos = new FileOutputStream(f);
 			ObjectOutputStream oos = new ObjectOutputStream(fos);
 			
 			oos.writeObject(list);
 			
 			oos.flush();
 			oos.close();
 			fos.close();
 	 		}
 	 		catch (Exception e) {
 	 		//	System.out.println(e.getMessage());
 	 			
 	 		} 	
 		
 	}
	
 	public static void writeUser(User u) {
 		// writing a user in file
 		try {
 		File f = new File("users\\" + u.userName + ".txt");
 		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
 		
 		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(u);
		
		oos.flush();
		oos.close();
		fos.close();
 		}
 		catch (Exception e) {
 			//System.out.println(e.getMessage());
 			
 		} 	
 		
 	}
 		
 	public static User loadUser(String userName) {
 		// loding user from a file
 		File f = new File("users\\" + userName + ".txt");

 		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
 		
 		User u = null;
 		
 		try {
 	 		FileInputStream fis = new FileInputStream(f);
 			ObjectInputStream ois = new ObjectInputStream(fis);
 	 		
 	 		
 	 		u = (User) ois.readObject();
 	 		
 	 		
 	 		}
 	 		catch (Exception e) {
 	 			//System.out.println(e.getMessage());
 	 			
 	 		}
 		return u;
 	}
 	
 	public static void addSystemUser(User u) {
 

 		try {
 			ArrayList<User> list = readSystemUsers();
 			if (list == null)
 				list = new ArrayList<User>();
 			list.add(u);
 	 		File f = new File("users\\SysetemUsers.txt");
 	 		
 	 		try {
 				f.createNewFile();
 			} catch (IOException e1) {
 				e1.printStackTrace();
 			}
 	 		
 	 		FileOutputStream fos = new FileOutputStream(f);
 			ObjectOutputStream oos = new ObjectOutputStream(fos);
 			
 			oos.writeObject(list);
 			
 			oos.flush();
 			oos.close();
 			fos.close();
 	 		}
 	 		catch (Exception e) {
 	 		//	System.out.println(e.getMessage());
 	 			
 	 		} 	
 		
 	}
 	
 	public static ArrayList<User> readSystemUsers() {
 		// list of System users
 		File f = new File("users\\SysetemUsers.txt");

 		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
 		
 		ArrayList<User> list = null;

 		try {
 	 		FileInputStream fis = new FileInputStream(f);
 			ObjectInputStream ois = new ObjectInputStream(fis);
 	 		
 	 		
 	 		list = (ArrayList<User>) ois.readObject();
 	 		
 	 		
 	 		}
 	 		catch (Exception e) {
 	 			//System.out.println(e.getMessage());	
 	 		}
 		return list;
 	}
 	
 	public static ArrayList<Object> readFeed() {
 		File f = new File("users\\feed.txt");

 		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
 		
 		ArrayList<Object> list = null;

 		try {
 	 		FileInputStream fis = new FileInputStream(f);
 			ObjectInputStream ois = new ObjectInputStream(fis);
 	 		
 	 		
 	 		list = (ArrayList<Object>) ois.readObject();
 	 		
 	 		
 	 		}
 	 		catch (Exception e) {
 	 			//System.out.println(e.getMessage());	
 	 		}
 		return list;
 	}
 	
 	public static void addFeed(Object o) {
 		
 		try {
 			ArrayList<Object> list = readFeed();
 			if (list == null)
 				list = new ArrayList<Object>();
 			list.add(o);
 	 		File f = new File("users\\feed.txt");
 	 		
 	 		try {
 				f.createNewFile();
 			} catch (IOException e1) {
 				e1.printStackTrace();
 			}
 	 		
 	 		FileOutputStream fos = new FileOutputStream(f);
 			ObjectOutputStream oos = new ObjectOutputStream(fos);
 			
 			oos.writeObject(list);
 			
 			oos.flush();
 			oos.close();
 			fos.close();
 	 		}
 	 		catch (Exception e) {
 	 		//	System.out.println(e.getMessage());
 	 			
 	 		} 	
 		
 	}

}
