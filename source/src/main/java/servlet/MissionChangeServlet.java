package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DailyDAO;

@WebServlet("/MissionChangeServlet")
public class MissionChangeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String missionData = request.getParameter("missionData");

		if (missionData == null || missionData.isEmpty()) {
			System.out.println("missionDataが空です");
			response.sendRedirect(request.getContextPath() + "/home.jsp");
			return;
		}

		String[] missions = missionData.split(",");

		int userId = 1; // 仮ログインユーザー

		DailyDAO dao = new DailyDAO();

		// ★ここが本体（今日分を完全更新）
		dao.replaceTodayMissions(userId, missions);

		response.sendRedirect(request.getContextPath() + "/HomeServlet");
	}
}