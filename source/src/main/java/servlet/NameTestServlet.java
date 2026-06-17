package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDto;
/**
 * Servlet implementation class NameTestServlet
 */
@WebServlet("/NameTestServlet")
public class NameTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	
			throws ServletException, IOException {
			
		UserDto user = new UserDto(1,"サザエさん","タマ");
		
		request.setAttribute("user",user);
		request.getRequestDispatcher("/name.jsp").forward(request, response);
	}
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");

	    String newChara = request.getParameter("charaNickname");
	    String newUser = request.getParameter("userNickname");

	    // 受け取った値で DTO を作り直す。
	    UserDto updated = new UserDto(1, newUser, newChara);

	    request.setAttribute("user", updated);
	    request.setAttribute("message", "更新成功");

	    request.getRequestDispatcher("/name.jsp").forward(request, response);
	}
}
