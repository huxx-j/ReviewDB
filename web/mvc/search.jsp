<%@ page import="kr.co.bit.memberdao.MemberDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.co.bit.membervo.MemberVO" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: bit-user
  Date: 2018-04-06
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String id = request.getParameter("id");

    MemberDAO dao = new MemberDAO();

    out.print(dao.select1(id).get(0).getId());
    out.print(dao.select1(id).get(0).getPw());
    out.print(dao.select1(id).get(0).getName());
    out.print(dao.select1(id).get(0).getEmail());
    out.print(dao.select1(id).get(0).getZipcode());
    out.print(dao.select1(id).get(0).getAddr1());
    out.print(dao.select1(id).get(0).getAddr2());
    out.print(dao.select1(id).get(0).getTool());
    String te = Arrays.toString(dao.select1(id).get(0).getLangs());
    out.print(te);
    out.print(dao.select1(id).get(0).getProject());


%>

</body>
</html>
