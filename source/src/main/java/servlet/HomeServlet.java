package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DailyDAO;
import dao.HomeDAO;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("HomeServlet実行");

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

        DailyDAO dao =
                new DailyDAO();

        String[] missions =
                dao.getTodayMissions(userId);

        System.out.println("===== missions =====");

        if(missions != null){
            for(String m : missions){
                System.out.println(m);
            }
        }else{
            System.out.println("missions = null");
        }

        request.setAttribute(
                "missions",
                missions);
        
        

        request.setAttribute(
                "missions",
                missions);

        HomeDAO homeDao =
                new HomeDAO();

        int[] radarData =
                homeDao.getRadarData(userId);

        request.setAttribute(
                "radarData",
                radarData);

        request.getRequestDispatcher(
                "/home.jsp")
                .forward(
                        request,
                        response);
    }
}