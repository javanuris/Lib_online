package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 18.02.2017.
 */
@WebServlet( urlPatterns = {"/admin", "/customer", "/cabinet"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name" ,"Nurislam");
        req.getRequestDispatcher("mypage.jsp").forward(req ,resp);
    }
}
