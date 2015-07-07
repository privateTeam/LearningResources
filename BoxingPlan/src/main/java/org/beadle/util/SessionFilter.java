package org.beadle.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.util.FileUtil;

public class SessionFilter implements Filter{

	Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("[sesion过滤器执行init方法]");
		String filePath = "/beadle/abc/text.txt";
		File file = new File(filePath);
		boolean result = FileUtil.createMissingParentDirectories(file);
		System.out.println(result);
		logger.info("[sesion过滤器创建文件成功] filePath="+filePath);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		//获取允许过滤的url
		String[] urlPermmited = PropertyParser.getArgs("urlPermitted");
		String path = req.getServletPath().trim();
		boolean permmited = false;
		for(String str : urlPermmited){
			str = str.trim();
			if(path.equals(str)){
				permmited = true;
				break;
			}
		}
		Object user = session.getAttribute(Dict.USER_SESSION_KEY);
		if(!permmited && user == null){
//			res.sendError(401,"error");
//			request.getRequestDispatcher("/").forward(request, response);
			res.sendRedirect("/");
		}else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
	}
	
}
