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
		// ★ 改善案：本番と同じように session に userId を入れる
        request.getSession().setAttribute("userId", 1);
        		
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
	    
        int userId = (Integer) request.getSession().getAttribute("userId");
	    String newChara = request.getParameter("charaNickname");
	    String newUser = request.getParameter("userNickname");
	    
	    
	    // 受け取った値で DTO を作り直す。
	    UserDto updated = new UserDto(userId, newUser, newChara);

	    request.setAttribute("user", updated);
	    request.setAttribute("message", "更新成功");

	    request.getRequestDispatcher("/name.jsp").forward(request, response);
	}
}
