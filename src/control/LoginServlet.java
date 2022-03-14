package control;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.MemberDAO;
import db.PlanBean;
import db.PlanDAO;

@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		MemberDAO mdao = new MemberDAO();
		int result = mdao.checkMember(userName);
		if(result==0){
			request.setAttribute("result", result);
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			
			RequestDispatcher dis = request.getRequestDispatcher("PlanListServlet.do");
			dis.forward(request, response);
		}
	}
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
