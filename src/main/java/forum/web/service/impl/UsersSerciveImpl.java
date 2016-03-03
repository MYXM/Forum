package forum.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import forum.web.dao.interfaces.UsersDao;
import forum.web.dao.pojo.User;
import forum.web.service.UsersService;

@Service("usersService")
public class UsersSerciveImpl implements UsersService {

    @Autowired
	private UsersDao usersDao;
	
	@Override
	public void addUsers(User user) {
		usersDao.addUser(user);
	}

	@Override
	public List<User> listAll() {
		return usersDao.listAll();
	}

}
