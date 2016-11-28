<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>
<%@ page import="model.User"%>
<%
	if (request.getAttribute("User") == null) {
		response.sendRedirect("Login.jsp");
		return;
	}

	User user = (User) request.getAttribute("User");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title id="title">Top</title>
</head>
<body>
ようこそ<%=user.getName() %>さん！

</body>
</html>