package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NameDAO;
import dto.UserDto;


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
		if (session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		}
		
		//ログイン中のユーザIDを取得
		Integer userId = (Integer) session.getAttribute("userId");	

		//DBから現在の呼び名を取得する
		NameDAO nDao=new NameDAO();
		UserDto user=nDao.selectByUserId(userId);
		
		request.setAttribute("user", user);
		
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
		if (session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
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
		boolean isSuccess = nDao.update(new UserDto(userId, userNickname, charaNickname));
		
		 
		String message;
		
		if(isSuccess) {
			message="呼び名を更新しました。";
		}else {
			message="呼び名を更新できませんでした。";
		}
		
		request.setAttribute("message",message);
		
		 // 更新後のデータを再取得する
	    UserDto user = nDao.selectByUserId(userId);
	    request.setAttribute("user", user);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/name.jsp");
		dispatcher.forward(request, response);
	}
}


