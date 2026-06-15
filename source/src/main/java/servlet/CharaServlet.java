package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CharaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("login_id") == null) {
					response.sendRedirect("/webapp/LoginServlet");
					return;
				}
		
		//CharacterDAOを利用してユーザーの現在の設定情報を取得する
		CharaDAO cDao = new CharaDAO();
	    Character character = cDao.findByUserId(user.getUserId());
	    
	    //取得した情報をリクエストスコープに格納する
	    request.setAttribute("character", character);
	    
	  //キャラクターページへフォワードする
	    RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/WEB-INF/jsp/character.jsp");
	    dispatcher.forward(request, response);
	}

}
