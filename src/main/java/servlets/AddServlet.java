package servlets;

import entities.User;
import model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * будет обрабатывать запросы, поступившие по адресу /add;
 */
public class AddServlet extends HttpServlet {
    /*
    AddServlet, чтобы выводилось уведомление об успешном добавлении пользователя. Для этого в методе doPost()
    после того, как добавили нового пользователя в модель, можем добавить имя этого пользователя в
    атрибуты объекта req и передать управление обратно во вьюху add.jsp. А в ней уже сделать участок с
    Java-кодом, в котором происходит проверка, есть ли такой атрибут в запросе, и если да — то вывод сообщения
    о том, что пользователь успешно добавлен.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("pass");

        User user = new User(name, password);
        //System.out.println(user.toString());

        Model model = Model.getInstance();
        model.add(user);

        //req.setAttribute("userName", name);
        req.setAttribute("userName", user.getName());
        doGet(req, resp);
    }


}
