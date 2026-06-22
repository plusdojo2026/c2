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

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// ログインチェック
		if (session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		Integer userId = (Integer) session.getAttribute("userId");

		UserDAO uDAO = new UserDAO();
		User user = uDAO.findByUserId(userId);

		// 現在のキャラ情報取得
		CharaDAO cDAO = new CharaDAO();
		Chara chara = cDAO.findByCharaId(user.getCharaId());

		request.setAttribute("type", chara.getTypeId());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/type.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// ログインチェック
		if (session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		Integer userId = (Integer) session.getAttribute("userId");

		UserDAO uDAO = new UserDAO();
		User user = uDAO.findByUserId(userId);

		request.setCharacterEncoding("UTF-8");

		// 選択されたタイプ(1～4)
		int typeId = Integer.parseInt(request.getParameter("typeId"));

		// 現在のキャラID取得

		int newCharaId;

		// 犬(1～4)
		String animal = (String) session.getAttribute("animal");

		if ("cat".equals(animal)) {
			newCharaId = typeId + 4;
		} else {
			newCharaId = typeId;
		}

		// userテーブルのchara_id更新
		CharaDAO cDAO = new CharaDAO();
		cDAO.update(newCharaId, userId);

		// ホーム画面へ
		response.sendRedirect(request.getContextPath() + "/HomeServlet");
	}
}