package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

	@WebServlet("/RegistServlet")
	public class RegistServlet extends HttpServlet {
		
	@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
			System.out.println("RegistServlet開始");

			RequestDispatcher dispatcher =
				    request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
				dispatcher.forward(request, response);
		}
	
	
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        System.out.println("ユーザID：" + id);
        System.out.println("パスワード：" + pw);
        
     // ログインidのダブりをなくす
        try {
        	UserDAO uDao = new UserDAO();
        
	        uDao.insert(id, pw);
	        
	        response.sendRedirect(
	                request.getContextPath() +"/LoginServlet");
	        } catch (Exception e) {
	
	            if (e.getMessage().contains("Duplicate") ) {
	
	                request.setAttribute("error", "そのログインIDは既に使用されています、ログインIDを変更して新規登録をしてください。");
	
	                RequestDispatcher dispatcher =
	                        request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
	                dispatcher.forward(request, response);
	                return;
	            }
	
	            throw new ServletException(e);
	        }
    }
}