package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DailyDAO;
import model.User;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        User user =
                (User) session.getAttribute("loginUser");

        int userId =
                user.getUserId();

        LocalDate today =
                LocalDate.now();

        int year =
                today.getYear();

        int month =
                today.getMonthValue();

        DailyDAO dao =
                new DailyDAO();

        Map<Integer,Integer> monthlyResult =
                dao.getMonthlyAchievement(
                        userId,
                        year,
                        month);

        request.setAttribute(
                "monthlyResult",
                monthlyResult);

        request.setAttribute(
                "year",
                year);

        request.setAttribute(
                "month",
                month);

        request.setAttribute(
                "yearMonth",
                YearMonth.of(year, month));

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "/WEB-INF/jsp/calendar.jsp");

        dispatcher.forward(
                request,
                response);
    }
}