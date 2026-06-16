package servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DailyDAO;

@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Calendar cal = Calendar.getInstance();

        int year;
        int month;

        try {
            year = Integer.parseInt(
                    request.getParameter("year"));

            month = Integer.parseInt(
                    request.getParameter("month")) - 1;

        } catch (Exception e) {

            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
        }

        cal.set(year, month, 1);

        int startDayOfWeek =
                cal.get(Calendar.DAY_OF_WEEK);

        int lastDay =
                cal.getActualMaximum(
                        Calendar.DAY_OF_MONTH);

        Calendar prev =
                (Calendar) cal.clone();

        prev.add(Calendar.MONTH, -1);

        Calendar next =
                (Calendar) cal.clone();

        next.add(Calendar.MONTH, 1);

        //--------------------------------
        // デイリー取得
        //--------------------------------

        HttpSession session =
                request.getSession();

        User user =
                (User) session.getAttribute("user");

        DailyDAO dao =
                new DailyDAO();

        Map<Integer,String> achievementMap =
                dao.getAchievementMap(
                        user.getUserId(),
                        year,
                        month + 1);

        //--------------------------------
        // JSPへ渡す
        //--------------------------------

        request.setAttribute("year", year);
        request.setAttribute("month", month);

        request.setAttribute(
                "startDayOfWeek",
                startDayOfWeek);

        request.setAttribute(
                "lastDay",
                lastDay);

        request.setAttribute(
                "prev",
                prev);

        request.setAttribute(
                "next",
                next);

        request.setAttribute(
                "achievementMap",
                achievementMap);

        RequestDispatcher rd =
                request.getRequestDispatcher(
                        "/WEB-INF/jsp/calendar.jsp");

        rd.forward(request, response);
    }
}