package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CharaDAO;
import dao.UserDAO;
import model.Chara;
import model.User;

@WebServlet("/CharaServlet")
public class CharaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		Integer userId = (Integer)session.getAttribute("user_id");
		
		UserDAO uDAO = new UserDAO();
		User user = uDAO.findByUserId(userId);
		
		//CharaDAOを利用してユーザーの現在の設定情報を取得する
		CharaDAO cDao = new CharaDAO();
	    Chara chara = cDao.findByCharaId(user.getCharaId());
	    
	    //取得した情報をリクエストスコープに格納する
	    request.setAttribute("userNickname", user.getUserNickname());
	    request.setAttribute("charaNickname",user.getCharaNickname());
	    request.setAttribute("image",chara.getImage());
	    request.setAttribute("speak",chara.getSpeak());
	    
	  //キャラクターページへフォワードする
	    RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/WEB-INF/jsp/character.jsp");
	    dispatcher.forward(request, response);
	}
	  protected void doPost(HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {

	        doGet(request, response);
	    }
}