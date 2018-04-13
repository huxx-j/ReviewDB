<%@ page import="kr.co.bit.vo.MemberVO" %>
<%@ page import="kr.co.bit.dao.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
    String cmd = request.getParameter("cmd");
//
//    MemberDAO dao = new MemberDAO();
//
//    boolean flag = dao.search_ID(cmd);
////    String url;
//    out.print(flag);
////    System.out.println(flag);
////    if (flag==true) {
////        url = "./id_check.jsp?cmd=true";
////    } else {
////        url = "./id_check.jsp?cmd=false";
////    }
////    request.setAttribute("cmd",flag);
////    response.sendRedirect(url);



    if(cmd.equals("id")){
        cmd = "true";
    } else {
        cmd = "123456";
    }

    String json = "{\"user\":\"admin\",\"message\":\"success\"}";
    out.print(json);
%>
