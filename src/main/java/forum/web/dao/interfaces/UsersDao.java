package forum.web.dao.interfaces;

import java.util.List;

import forum.web.dao.pojo.User;

public interface UsersDao {
	
	public void addUser(User user);
	
	public List<User> listAll();
	
}
