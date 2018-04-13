<%@ page import="kr.co.bit.dao.MemberDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.co.bit.vo.MemberVO" %>
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
    <script type="text/javascript">
        function send_remove(id) {
            var frm = document.getElementById("frm");
            frm.action = "command?cmd=remove&id="+id;
            frm.onsubmit();
        }
    </script>
</head>
<body>
<%
    ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list");
    StringBuffer stringBuffer = new StringBuffer("<form method='post' action='' id='frm'>");

    for (MemberVO vo :list) {
        stringBuffer.append(vo.getId());
        stringBuffer.append(" <a href = './command?cmd=update&name="+vo.getName()+"'>"+vo.getName()+"</a>");
        stringBuffer.append(" " + vo.getAddr1());
        stringBuffer.append("<button onclick=\"return send_remove('"+vo.getId()+"')\">");
        stringBuffer.append("삭제</button><br>");
    }
    stringBuffer.append("</form>");
    out.print(stringBuffer.toString());

//    String id = request.getParameter("id");
//
//    MemberDAO dao = new MemberDAO();
//
//    out.print(dao.select1(id).get(0).getId());


%>

</body>
</html>
