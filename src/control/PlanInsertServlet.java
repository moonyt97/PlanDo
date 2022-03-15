package control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.PlanDAO;

@WebServlet("/PlanInsertServlet.do")
public class PlanInsertServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("userName");
		String content = request.getParameter("content");
		String dday = request.getParameter("dday");
		PlanDAO pdao = new PlanDAO();
		pdao.insertPlan(userName,content,dday);
		RequestDispatcher dis = request.getRequestDispatcher("PlanListServlet.do");
		dis.forward(request, response);
		
	}
	

}
