	//ログインサーブレット
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





	/**
	 	* Servlet implementation class LoginServlet
	 */
	@WebServlet("/LoginServlet")
	public class LoginServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher=
		 request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータを取得
		String user_id =request.getParameter("user_id");
		String pw =request.getParameter("pw");
		
	
	
		// ログイン処理を行う
		UserDAO iDao = new UserDAO();
		if (iDao.isLoginOK(user_id, pw)) { // ログイン成功
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("user_id",user_id);

			// サーブレットにリダイレクトする
			response.sendRedirect("/HomeServlet");
			
		} else { // ログイン失敗
			request.setAttribute("loginError", true);
			
			// 結果ページにフォワードする
			RequestDispatcher dispatcher=
			request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request,response);
		}
	}
	}
		
