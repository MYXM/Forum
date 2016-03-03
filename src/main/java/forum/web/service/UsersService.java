package forum.web.service;

import java.util.List;

import forum.web.dao.pojo.User;

public interface UsersService {
	public void addUsers(User user);
	
	public List<User> listAll();
}
