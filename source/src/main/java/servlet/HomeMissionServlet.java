package servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

@WebServlet("/HomeMissionServlet")
public class HomeMissionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("userId");

		UserDAO userDao = new UserDAO();

		int charaId = userDao.getCharaId(userId);

		String charaImage;

		if (charaId == 1 || charaId == 3) {
			charaImage = "dog.png";
		} else {
			charaImage = "cat.png";
		}

		// ランダムコメント
		String[] comments = { "今日はどんなミッションにする", "一緒に頑張ろう", "気になるテーマを選んで", "無理せずできそうなものを選ぼう", "今日の目標を決めよう",
				"小さな一歩が大切だ", "どれにするかワクワクする", "君ならきっとできる" };

		Random random = new Random();

		String comment = comments[random.nextInt(comments.length)];

		if (charaId >= 1 && charaId <= 4) {
			String[] tails = { "ワン！", "ワン♪", "ワンワン！" };
			comment += tails[random.nextInt(tails.length)];
		} else if (charaId >= 5 && charaId <= 6) {
			String[] tails = { "ニャー！", "ニャー♪", "ニャーニャー！" };
			comment += tails[random.nextInt(tails.length)];
		}

		request.setAttribute("comment", comment);
		request.setAttribute("charaId", charaId);
		request.setAttribute("charaImage", charaImage);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mission.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
}