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

import db.PlanBean;
import db.PlanDAO;

@WebServlet("/PlanListServlet.do")
public class PlanListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");

		// 화면에 보여질 게시글의 개수
		int pageSize = 5;
		// 현재 보여지고 있는 페이지의 넘버값을 읽어드림
		String pageNum = request.getParameter("pageNum");
		// null처리
		if (pageNum == null) {
			pageNum = "1";
		}
		// 전체 글의 개수
		int count = 0;
		// jsp에서 보여질 넘버링 숫자 값을 저장
		int number = 1;
		// 현재 보여지고 있는 페이지 문자를 숫자로 형변환
		int currentPage = Integer.parseInt(pageNum);
		// 전체 게시글의 개수 가져오기
		PlanDAO pdao = new PlanDAO();
		count = pdao.getAllCount(userName);

		// 현재 보여질 페이지 시작 번호를 설정
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage*pageSize;

		//8개를 기준으로 게시글을 리턴 받아주는 메소드 호출
		Vector<PlanBean> v = pdao.getPlan(userName,startRow,endRow);
		number = pageSize * (currentPage-1)+1;
		
		request.setAttribute("v", v);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);

		RequestDispatcher dis = request.getRequestDispatcher("plan.jsp");
		dis.forward(request, response);
	}
}
