package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {

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

        UserDAO uDao = new UserDAO();
        uDao.insert(id, pw);
        
        
        // DAOで登録処理
        response.sendRedirect(
                request.getContextPath() + "/login.jsp");
    }
}