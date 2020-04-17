package servlets.report;

import dao.RptTov01DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RptTov01 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<String> aHead = Arrays.asList("Код пр-ции", "Наименование продукции","Ед.изм","К-во, шт", "Сумма, грн");

        RptTov01DAO dao = new RptTov01DAO();
        List<List<String>> aRecList = dao.viewAllRptTov02();

        req.setAttribute("aHead", aHead);
        req.setAttribute("aRecList", aRecList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/report/rpttov01.jsp");
        requestDispatcher.forward(req, resp);
    }
}
