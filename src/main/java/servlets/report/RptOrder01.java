package servlets.report;

import dao.RptOrder01DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RptOrder01 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<String> aHead = Arrays.asList("Код ТА", "ФИО ТА", "Сумма, грн", "К-во заказов");
        RptOrder01DAO dao = new RptOrder01DAO();
        List<List<String>> aRecList = dao.viewAllRptOrder01();

        req.setAttribute("aHead", aHead);
        req.setAttribute("aRecList", aRecList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/report/rptorder01.jsp");
        requestDispatcher.forward(req, resp);
    }
}
