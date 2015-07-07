package org.beadle.controller.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beadle.pojo.Message;
import org.beadle.pojo.user.User;
import org.beadle.service.user.UserService;
import org.beadle.util.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserService userService;
	
	@RequestMapping("/login.do")
	public String login(User user,
						HttpSession session,
						boolean autoLogin,
						HttpServletResponse response,
						HttpServletRequest request){
		System.out.println(autoLogin);
		User u = userService.loginValidate(user);
		if(u != null){
			session.setAttribute(Dict.USER_SESSION_KEY,u);
			Cookie cookie = new Cookie("userName",u.getUserName());
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		return "/main/main";
	}
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "/main/main";
	}
	
	@RequestMapping("/regist.do")
	public Object regist(User user){
		//userService.registUser(user)
		return new Message(true);
	}
	
	@RequestMapping("/check.do")
	public Object check(HttpSession session,HttpServletRequest request){
		Object user = session.getAttribute(Dict.USER_SESSION_KEY);
		if(user != null){
			return new Message(true);
		}
		return new Message(false);
	}
	public UserService getUserService(){
		return this.userService;
	}
}
