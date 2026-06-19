package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("LoginServlet開始");

		RequestDispatcher dispatcher =
			    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

			dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("★★★★ doPost開始 ★★★★");

		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("user_id");

		String pw = request.getParameter("pw");

		System.out.println("loginId=" + loginId);
		System.out.println("pw=" + pw);

		UserDAO dao = new UserDAO();

		boolean ok = dao.isLoginOK(loginId, pw);

		System.out.println("isLoginOK=" + ok);

		if (ok) {

			System.out.println("ログイン成功");

			int userId = dao.getUserId(loginId);

			HttpSession session = request.getSession();

			session.setAttribute("userId", userId);
			session.setAttribute("loginId", loginId);

			response.sendRedirect(request.getContextPath() + "/HomeServlet");

		} else {

			System.out.println("ログイン失敗");

			request.setAttribute("loginError", true);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

			dispatcher.forward(request, response);
		}
	}
}
