package kr.co.bit.controller;

import kr.co.bit.dao.MemberDAO;
import kr.co.bit.dao.ZipcodeDAO;
import kr.co.bit.vo.MemberVO;
import kr.co.bit.vo.ZipcodeVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cmd = request.getParameter("cmd");
        cmd = cmd == null ? "" : cmd;
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
        } else if (cmd.equals("search")) {
            String id = request.getParameter("id");
            url = "./mvc/regist_member.jsp";
            MemberDAO dao = new MemberDAO();
            MemberVO vo = dao.select(id);
            request.setAttribute("vo", vo);

        } else if (cmd.equals("make")) {
            url = "./mvc/result.jsp";
            ZipcodeDAO dao = new ZipcodeDAO();
            String path = this.getServletContext().getRealPath("WEB-INF/file/zipcode.csv");
            boolean flag = dao.insert(path);

            request.setAttribute("result", flag ? "success" : "fail");
        } else if (cmd.equals("search_d")) {
            ZipcodeDAO dao = new ZipcodeDAO();
            String dong = request.getParameter("dong");
            List<ZipcodeVO> list = dao.search(dong);
            request.setAttribute("list", list);

            url = "./mvc/postal.jsp";

        } else if (cmd.equals("idcheck")) {
            MemberVO vo = null;
            MemberDAO dao = new MemberDAO();
            vo = dao.select(request.getParameter("id"));
            url = "./mvc/id_check.jsp";
            request.setAttribute("result", vo);
        } else if (cmd.equals("viewIdService")) {
            url = "./mvc/id_service.jsp";
        } else if (cmd.equals("postal")) {
            url = "./mvc/postal.jsp";
        } else if (cmd.equals("viewIdCheck")) {
            url = "./mvc/id_check.jsp";
        } else if (cmd.equals("searchAll")) {
            url = "./mvc/search.jsp";
            MemberDAO dao = new MemberDAO();
            ArrayList<MemberVO> list = dao.select();
            request.setAttribute("list", list);
        } else if (cmd.equals("update")) {
            url = "./mvc/update_member.jsp";
            String name = request.getParameter("name");

            MemberDAO dao = new MemberDAO();
            MemberVO vo = dao.selectName(name);
            request.setAttribute("vo", vo);

        } else if (cmd.equals("update_member")) {
            System.out.println("커맨드 - 업데이트");
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
            boolean flag = dao.update(vo);

            request.setAttribute("message", "success");

            if (flag) {
                url = "command?cmd=searchAll";
            } else {
                url = "./mvc/error.jsp";
            }

        } else if (cmd.equals("remove")) {
            String id = request.getParameter("id");
            MemberDAO dao = new MemberDAO();
            boolean flag = dao.delete(id);
            if (flag==true) {
                url = "command?cmd=searchAll.jsp";
            } else {
                url = "./mvc/error.jsp";
            }
        }

        resp.setContentType("text/html; charset=UTF-8");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, resp);
    }

}

