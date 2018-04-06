<%--
  Created by IntelliJ IDEA.
  User: Huxx_j
  Date: 2018. 3. 29.
  Time: PM 3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>

</head>
<body>
Home
<a href="./command?cmd=viewRegist">등록</a>
<a href="./command?cmd=searchAll">전체조회</a>
<form action="./command?cmd=search" method="post">
    <input type="text" name="id">
    <input type="submit" value="부분조회">
</form>
</body>
</html>