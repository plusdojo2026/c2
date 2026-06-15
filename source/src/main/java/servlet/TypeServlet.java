package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class TypeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}

        // 現在のタイプ設定取得
        TypeDAO tDao = new TypeDAO();
        Type type = dao.findByUserId(user.getUserId());

        // 取得した情報をリクエストスコープに格納する
        request.setAttribute("type", type);

        // タイプページへフォワードする
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/type.jsp");

        dispatcher.forward(request, response);
	}
}

protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
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
    TypeDAO dao = new TypeDAO();
    dao.updateType(user.getUserId(), weatherType);

    // 設定画面へ戻る
    response.sendRedirect(request.getContextPath()　+ "/CharacterServlet");
	}
}

