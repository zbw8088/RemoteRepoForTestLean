<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title id="title">Login</title>
</head>

<form method="GET" action="LoginServlet" >
<table>
<tr>
<td>ログイン名</td>
<td><input type="text" name="LoginName" /></td>
</tr>
<tr>
<td>パスワード</td>
<td><input type="pass" name="PassWord" /></td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value="ログイン！" />
</td>
</tr>
</table>
</form>

<body>
</body>
</html>