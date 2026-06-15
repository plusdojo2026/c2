package servlet;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NameDAO;
import dto.User;


/**
	* Servlet implementation class NameServlet
*/
@WebServlet("/NameServlet")

public class NameServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	
		throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		// キャラとユーザの呼び方を登録できるページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/name.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		//ログイン中のユーザIDを取得する。これでユーザごとにニックネームが決められると思い、記載した。
		int userId=(Integer)session.getAttribute("userId");	
		String userNickname=request.getParameter("userNickname");
		String charaNickname=request.getParameter("charaNickname");
		
		
		
		// 更新処理を行う
		NameDAO nDao = new NameDAO();
		
		 String action = request.getParameter("regist");
		 
	if ("登録".equals(action)) {
		if (nDao.update(new User(userId,userNickname,charaNickname))) { // 登録成功
			request.setAttribute("result", new Result("登録成功！","呼び名を更新しました。", "/webapp/NameServlet"));
		} else { // 登録失敗
		request.setAttribute("result", new Result("登録失敗！", "呼び名を登録できませんでした。", "/webapp/NameServlet"));
		}
	}			

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}
}

