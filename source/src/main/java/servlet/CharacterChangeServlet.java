//キャラクター変更サーブレット
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CharacterChangeServlet
 */
@WebServlet("/CharacterChangeServlet")
public class CharacterChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("loginId") == null) {
			response.sendRedirect("/c2/LoginServlet");
			return;
		}

		// キャラ変更画面へフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/charachange.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String dog = request.getParameter("dog");
		String cat = request.getParameter("cat");

		HttpSession session = request.getSession();

		if ("犬".equals(dog)) {
			session.setAttribute("animal", "dog");
		} else if ("猫".equals(cat)) {
			session.setAttribute("animal", "cat");
		}

		response.sendRedirect(request.getContextPath() + "/TypeServlet");
	}

}
