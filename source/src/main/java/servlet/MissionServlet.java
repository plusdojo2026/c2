package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MissionThemeDAO;

@WebServlet("/MissionServlet")
public class MissionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String theme = request.getParameter("theme");

        MissionThemeDAO dao =
                new MissionThemeDAO();

        List<String> missions =
                dao.findMissionsByThemeName(theme);

        Collections.shuffle(missions);

        int count = Math.min(3, missions.size());

        List<String> selectedMissions =
                new ArrayList<>();

        for(int i = 0; i < count; i++) {
            selectedMissions.add(missions.get(i));
        }

        response.setContentType(
                "application/json;charset=UTF-8");

        PrintWriter out =
                response.getWriter();

        out.print("[");

        for(int i = 0; i < selectedMissions.size(); i++) {

            out.print("\""
                    + selectedMissions.get(i)
                    + "\"");

            if(i < selectedMissions.size() - 1) {
                out.print(",");
            }
        }

        out.print("]");
    }
}