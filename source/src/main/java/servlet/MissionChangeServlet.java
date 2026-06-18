package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DailyDAO;

@WebServlet("/MissionChangeServlet")
public class MissionChangeServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String missionData =
                request.getParameter("missionData");

        if (missionData == null ||
                missionData.isEmpty()) {

            System.out.println("missionDataが空です");

            response.sendRedirect(
                    request.getContextPath()
                    + "/WEB-INF/jsp/home.jsp");

            return;
        }

        String[] missions =
                missionData.split(",");

        HttpSession session =
                request.getSession();

        Integer userId =
                (Integer) session.getAttribute(
                        "userId");

        if (userId == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/LoginServlet");

            return;
        }

        System.out.println(
                "保存userId=" + userId);

        DailyDAO dao =
                new DailyDAO();

        dao.replaceTodayMissions(
                userId,
                missions);

        response.sendRedirect(
                request.getContextPath()
                + "/HomeServlet");
    }
}