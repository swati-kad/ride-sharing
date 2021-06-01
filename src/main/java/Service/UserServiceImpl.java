package Service;

import java.util.ArrayList;
import java.util.List;

import Model.User;

public class UserServiceImpl implements UserService {
	
	private List<User> allUsers = new ArrayList<>();
	 private int i=0;
	@Override
	public void addUser(User user) {
		try { i=i+1;
		System.out.println("list of user Name "+ i +" i  "+ user);
			allUsers.add(user);
			System.out.println("list of user  "+ allUsers + " size will be  :: " + allUsers.size() );
		}catch(Exception e) {
			System.out.println("Exception " + e.getMessage() + " occured while adding User " + user);
		}
	}
}
