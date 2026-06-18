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

@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		Integer userId = (Integer)session.getAttribute("user_id");

		UserDAO uDAO = new UserDAO();
		User user = uDAO.findByUserId(userId);

        // 現在のタイプ設定取得
        CharaDAO cDAO = new CharaDAO();
        Chara chara = cDAO.findByCharaId(user.getCharaId());

        // 取得した情報をリクエストスコープに格納する
        request.setAttribute("type", chara.getTypeId());

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
	Integer userId = (Integer)session.getAttribute("user_id");

	UserDAO uDAO = new UserDAO();
	User user = uDAO.findByUserId(userId);

    request.setCharacterEncoding("UTF-8");

    // フォームから取得
    int typeId = Integer.parseInt (request.getParameter("typeId"));

    // 更新処理
    CharaDAO cDAO = new CharaDAO();
    cDAO.updateType(user.getCharaId(),typeId);

    // 設定画面へ戻る
    response.sendRedirect(request.getContextPath()+ "/CharaServlet");
	}
}

