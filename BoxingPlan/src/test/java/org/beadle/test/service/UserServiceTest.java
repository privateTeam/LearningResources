package org.beadle.test.service;

import java.sql.Date;

import org.beadle.dao.user.BehaviorDao;
import org.beadle.dao.user.UserDao;
import org.beadle.pojo.user.Behavior;
import org.beadle.pojo.user.User;
import org.beadle.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-*.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests{
	@Autowired UserService userService;
	@Autowired UserDao userDao;
	@Autowired BehaviorDao behaviorDao;
	
	@Test
	public void insertTest(){
//		User user = new User();
//		user.setUserName("beadle2");
//		user.setPassword("123456");
//		userService.registUser(user);
		Behavior be = new Behavior();
		be.setCount(100);
		java.util.Date d= new java.util.Date();
		be.setRecentTime(new Date(d.getTime()));
		behaviorDao.insertOne(be);
	}
	
	@Test
	public void updateOneTest(){
		User user = new User();
		user.setId(1);
		user.setUserName("beadle3");
		user.setPassword("123456");
		userDao.updateOne(user);
	}
	
	@Test
	public void getOneTest(){
		User user= userDao.getOneById(1);
		System.out.println(user);
	}
	
}
