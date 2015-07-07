<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<link href="/css/ui-dialog.css" rel="stylesheet" type="text/css" />
	<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="/css/main/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/js/jquery/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="/js/jquery/dialog.js"></script>
	<script type="text/javascript" src="/js/main/main.js"></script> 
	<script type="text/javascript" src="/js/common/common.js"></script>
	
	<!-- 
	<script type="text/javascript" language="javascript" src=""></script>
	 -->
	<script type="text/javascript">
	</script>
</head>
<body>
	<%@ include file="../common/head.jsp" %>
	
	<div class="body_content">
		<div class="zone">
			<div class="block" >
				<img alt="search" src="/img/search.jpg"/>
				<div class="black_layer"></div>
			</div>
			<div class="block" >
				<img alt="none" src="http://pic.58pic.com/58pic/15/17/35/71458PICwxh_1024.jpg" />	
				<div class="black_layer"></div>
			</div>
			<div class="block">
				<img alt="search" src=""/>
				<div class="black_layer"></div>
			</div>
		</div>
		<div class="zone">
			<div class="block">
				<img alt="search" src=""/>
				<div class="black_layer"></div>
			</div>
			<div class="block">
				<img alt="search" src=""/>
				<div class="black_layer"></div>
			</div>
			<div class="block">
				<img alt="search" src=""/>
				<div class="black_layer"></div>
			</div>
		</div>
		<div class="zone">
			<div class="block">
				<img alt="search" src=""/>
				<div class="black_layer"></div>
			</div>
			<div class="block">
				<img alt="search" src=""/>
				<div class="black_layer"></div>
			</div>
			<div class="block">
				<img alt="search" src=""/>
				<div class="black_layer"></div>
			</div>
		</div>
	</div>
	
	<%@ include file="../common/foot.jsp" %>
	<div class="frame_layer"></div>
	<div class="login_frame">
		<form method="post" action="/user/login.do" >
			<div class="login_title">
				Boxing User Login
			</div>
			<div class="form-group">
	          <input type="text" class="form-control" name="userName" placeholder="username">
	        </div>
	        <div class="form-group">
	          <input type="password" class="form-control" name="password" placeholder="password">
	        </div>
	        <div class="login_bottom">
	        	<button type="submit" class="btn btn-default">Login</button>
	        	no user? <a style="text-decoration:underline;color: blue;" href="javascript:void(0)">regist now</a>
	       	</div>
	    </form>
	</div>
	<div class="regist_frame">
		<div class="regist_title">
			Boxing User Regist
		</div>
		<div class="form-group">
			<label for="exampleInputName2">UserName</label>
        	<input type="text" class="form-control" placeholder="username">
        </div>
        <div class="form-group">
        	<label for="exampleInputName2">Set Password</label>
        	<input type="password" class="form-control" placeholder="password">
        </div>
        <div class="form-group">
        	<label for="exampleInputName2">Confirm Password</label>
        	<input type="password" class="form-control" placeholder="password-confirm">
        </div>
        <div class="regist_bottom">
        	<button type="submit" class="btn btn-default">Regist</button>
        	<button type="button" class="btn btn-default">GoBack</button>
       	</div>
	</div>
</body>
</html>