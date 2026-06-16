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

import dao.UserDAO;


/**
 * Servlet implementation class CharacterChangeServlet
 */
@WebServlet("/CharacterChangeServlet")
public class CharacterChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
		response.sendRedirect("/c2/LoginServlet");
		return;
		}
		
		//キャラ変更画面へフォワードする
		RequestDispatcher dispatcher =
		request.getRequestDispatcher("/charachange.jsp");
		dispatcher.forward(request,response);
		}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1
		//リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");
		
		// リクエストパラメータを取得する
		String dog = request.getParameter("dog");
		String cat = request.getParameter("cat");
		
		//2 ログイン中のユーザIDを取得する。
		HttpSession session = request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		
		//3　もしボタンに書かれてる文字が犬なら、テーブルの項目charaidを1に更新
	    //  もしボタンに書かれている文字が猫なら、テーブルの項目charaidを2に更新
		
		 UserDAO uDao = new UserDAO();
		 
		 if (dog.equals("犬")) {
				uDao.update(1,user_id);
		}else if(cat.equals("猫")){
			 	uDao.update(2,user_id);
		}
				
		//3 タイプ選択画面（typechange.jsp）へフォワードする
			RequestDispatcher dispatcher =
			request.getRequestDispatcher("/typechange.jsp");
			dispatcher.forward(request,response);
			}
		
	}


