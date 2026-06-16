package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CharaDAO;
import dao.UserDAO;
import model.Chara;
import model.User;

public class TypeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		UserDAO uDAO = new UserDAO();
		User user = uDAO.findByUserId(userId);

        // 現在のタイプ設定取得
        CharaDAO cDao = new CharaDAO();
        Chara chara = cDAO.findByTypeId(user.getTypeId());

        // 取得した情報をリクエストスコープに格納する
        request.setAttribute("type", chara.getTypeId);

        // タイプページへフォワードする
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/type.jsp");

        dispatcher.forward(request, response);
		}
	

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
	// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();
			if (session.getAttribute("login_id") == null) {
				response.sendRedirect("/webapp/LoginServlet");
				return;
			}

    request.setCharacterEncoding("UTF-8");

    // フォームから取得
    String weatherType = request.getParameter("weatherType");

    // 更新処理
    CharaDAO dao = new CharaDAO();
    dao.updateType(user.getUserId(), weatherType);

    // 設定画面へ戻る
    response.sendRedirect(request.getContextPath()+ "/CharacterServlet");
	}
}

