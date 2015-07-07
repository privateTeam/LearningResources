package org.beadle.service.user;

import org.beadle.dao.user.UserDao;
import org.beadle.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	
	@Autowired UserDao userDao;

	public User loginValidate(User user){
		return userDao.validateUser(user);
	}
	
	public boolean registUser(User user){
		userDao.insertOne(user);
		return true;
	}
	
	public void insertUserWithBatch(){
		
	}
}
