package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HomeDAO;

@WebServlet("/CompleteServlet")
public class CompleteServlet extends HttpServlet {

	@Override
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		try {

			HttpSession session =
					request.getSession();

			Integer userId =
					(Integer)session.getAttribute(
							"userId");

			if(userId == null) {

				response.getWriter()
						.write("error");

				return;
			}

			String missionName =
					request.getParameter(
							"missionName");

			String completeStr =
			        request.getParameter("complete");

			boolean complete =
			        "1".equals(completeStr);

			System.out.println(complete);

			HomeDAO dao =
					new HomeDAO();

			boolean result =
					dao.updateComplete(
							userId,
							missionName,
							complete);

			if(result) {

				response.getWriter()
						.write("success");

			}else {

				response.getWriter()
						.write("error");
			}

		}catch(Exception e) {

			e.printStackTrace();

			response.getWriter()
					.write("error");
		}
	}
}