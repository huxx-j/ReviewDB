<%@ page import="kr.co.bit.dao.MemberDAO" %>
<%@ page import="kr.co.bit.vo.MemberVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ID 검사</title>
    <script type="text/javascript">
        function work_close() {
            //id의 값을 가져오기
            var userid = document.getElementById("id").value;
            opener.document.getElementById("userid").value = userid;
            opener.document.getElementById("isIdCheck").value = true;
            self.close();
        }

        // function () {
        //
        // }
        function search_ID_ajax() {
            var cmd = document.getElementById("id").value;
            var server_page = "../mvc/id_service.jsp";
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var result = this.responseText;
                    processResultId(result);
                } else if (this.readyState4 && this.status != 200) {
                    alert("error")
                }

                // xhr.open("GET",server_page,true);
                // xhr.send();
                var data = "cmd=" + cmd;
                alert(data);
                xhr.open("POST", server_page, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
                xhr.send(data);

                return false;
            }


        }

        function processResultId(result) {
            if (result == "true") {
                document.getElementById("idc").value = "아이디 사용가능";
            } else {
                document.getElementById("idc").value = "아이디 사용 불가능";
            }
        }
    </script>
</head>
<body>
<%
    MemberVO vo = (MemberVO)request.getAttribute("result");
    String message = "사용불가능";
    if(vo==null){
        message = "";
    }else if (vo.getPw() == null) {
        message = "사용가능";
    }
//    String id = request.getParameter("id");
//    String flag = request.getParameter("cmd");
//    System.out.println(flag);
//    if (flag.equals("true")) {
//
//    }

%>
ID check <br>

<form action="../command?cmd=idcheck" method="post">
    아이디<input type="text" name="id" value="<%=vo==null?"":vo.getId()%>" id="id">
    <input type="submit" value="Search_ID">
    <button onclick="work_close()">Use_ID</button>
    <br>
    <input type="text" name="check" id="idc" disabled="disabled" value=<%=message%>>
</form>
</body>
</html>