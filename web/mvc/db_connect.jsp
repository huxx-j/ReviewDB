<%@ page import="kr.co.bit.dao.MemberDAO" %><%--
  Created by IntelliJ IDEA.
  User: bit-user
  Date: 2018-04-06
  Time: 오전 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>db_connect</title>
</head>
<body>
<%
    MemberDAO memberDAO = new MemberDAO();
    memberDAO.select();
%>
</body>
</html>
