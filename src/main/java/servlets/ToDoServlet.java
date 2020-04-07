package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ToDoServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String url = req.getRequestURL().toString();
        System.out.println(url);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/todo.jsp");
        requestDispatcher.forward(req, resp);
    }

}