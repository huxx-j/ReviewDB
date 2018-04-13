<%@ page import="kr.co.bit.vo.ZipcodeVO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: bit-user
  Date: 2018-04-09
  Time: 오후 5:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function popup() {
            var val = document.getElementById("popup").value;
            // alert(val);
        }

        function use_zipcode() {
            var val = document.getElementById("popup").value;
            var temp = val.split(" ");
            var zipcode = temp[0].split("-");
            var address = val.substring(temp[0].length + 1);
            // alert(zipcode + "/" + address);
            opener.document.getElementById("zip1").value = zipcode[0];
            opener.document.getElementById("zip2").value = zipcode[1];
            opener.document.getElementById("addr1").value = address;
            opener.document.getElementById("isZipCheck").value = true;
            self.close()
        }
    </script>
</head>
<body>

<form action="../command?cmd=search_d" method="post">
    검색(동명) <input type="text" name="dong">
    <input type="submit" value="Search">
</form>
<%
    ArrayList<ZipcodeVO> list = (ArrayList<ZipcodeVO>) request.getAttribute("list");
    if (list == null) {
        list = new ArrayList<>();
    }

    StringBuffer stringBuffer = new StringBuffer("<select onchange='popup()' id='popup'><option>주소선택");
    for (ZipcodeVO vo : list) {
        stringBuffer.append("<option value='" + vo.getAdd() + "'>");
        stringBuffer.append(vo.toString());
    }
    stringBuffer.append("</select>");
//    out.print(stringBuffer.toString());

%>
<%=stringBuffer.toString()%>
<button onclick="use_zipcode()">use</button>

<%--<br><br>--%>
<%--<input type="text" name="zip1" size="5"> ---%>
<%--<input type="text" name="zip2" size="5"> <br><br>--%>
<%--<input type="text" name="addr1" size="50"> <br><br>--%>
<%--<input type="text" name="addr2" size="50">--%>

<%--<%--%>
<%--ArrayList<ZipcodeVO> list = (ArrayList<ZipcodeVO>) request.getAttribute("list");--%>
<%--if (list==null) {--%>
<%--list = new ArrayList<>();--%>
<%--} else {--%>
<%--%>--%>
<%--<select>--%>
<%--<option>주소선택--%>
<%--<%--%>
<%--for(ZipcodeVO vo : list) { %>--%>
<%--<option><%=vo.toString()%>--%>
<%--<%--%>
<%--}--%>
<%--}--%>

<%--%>--%>
<br>

<%--<form action="postal_print.jsp" method="post">--%>
<%--검색(동명) <input type="text" name="dong">--%>
<%--<input type="submit" value="찾기">--%>
<%--</form>--%>

</body>
</html>
