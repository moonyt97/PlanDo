package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.MemberDAO;

@WebServlet("/JoinServlet.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		MemberDAO mdao = new MemberDAO();
		int result = mdao.checkMember(userName);
		if(result==1){
			request.setAttribute("result", result);
			RequestDispatcher dis = request.getRequestDispatcher("join.jsp");
			dis.forward(request, response);
			
		}else{
			mdao.insertMember(userName);
			response.sendRedirect("index.jsp");
		}
		
		
	}
	

}
