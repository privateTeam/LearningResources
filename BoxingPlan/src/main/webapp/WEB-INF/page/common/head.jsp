<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="body_head">
	<div class="user_content">
	<c:choose>
		<c:when test="${user != null}">Hellow,${user.userName } | 
		<a href="/user/logout.do">Logout</a></c:when>
   		<c:otherwise>Pls login first!</c:otherwise>
	</c:choose>
	</div>
</div>
