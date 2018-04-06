package kr.co.bit.controller;

import kr.co.bit.memberdao.MemberDAO;
import kr.co.bit.membervo.MemberVO;
import org.apache.axis.session.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CommandController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        cmd = cmd==null?"":cmd;
        request.setCharacterEncoding("UTF-8");
        String url = "./mvc/home.jsp";

       if (cmd.equals("regist")) {
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String zip1 = request.getParameter("zip1");
            String zip2 = request.getParameter("zip2");
            String addr1 = request.getParameter("addr1");
            String addr2 = request.getParameter("addr2");
            String tool = request.getParameter("tool");
            String project = request.getParameter("project");
            String[] langs = request.getParameterValues("lang");

            //MemberVO 데이터 클래스를 만들어서 인스턴스를 하나 생성
            MemberVO vo = new MemberVO();
            vo.setId(id);
            vo.setPw(pw);
            vo.setName(name);
            vo.setEmail(email);
            vo.setZipcode(zip1 + "-" + zip2);
            vo.setAddr1(addr1);
            vo.setAddr2(addr2);
            vo.setTool(tool);
            vo.setProject(project);
            vo.setLangs(langs);

           MemberDAO dao = new MemberDAO();
           boolean flag = dao.insert(vo);
            request.setAttribute("message", "success");

            if (flag) {
                url = "./mvc/list.jsp";
            } else {
                url = "./mvc/error.jsp";
            }

        } else if (cmd.equals("viewRegist")) {
           url = "./mvc/regist_member.jsp";
       } else  if (cmd.equals("search")) {
//           ArrayList<MemberVO> list;
//           MemberDAO dao = new MemberDAO();
//           String id = request.getParameter("id");
//           list = dao.select1(id);
//
           url = "./mvc/search.jsp";
       }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, resp);
    }

}

