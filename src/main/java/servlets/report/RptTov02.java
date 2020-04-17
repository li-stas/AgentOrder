package servlets.report;

import dao.RptTov02DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * https://ibytecode.com/blog/pagination-in-servlet-and-jsp/
 */

public class RptTov02 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int page = 1;
        int recordsPerPage = 10;
        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        RptTov02DAO dao = new RptTov02DAO();
        List<List<String>> aRecList = dao.viewAllRptTov02(page, recordsPerPage);
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        List<String> aHead = Arrays.asList("Код пр-ции", "Наименование продукции","Ед.изм","К-во, шт", "Сумма, грн");
        req.setAttribute("aHead", aHead);
        req.setAttribute("aRecList", aRecList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/report/rpttov02.jsp");
        requestDispatcher.forward(req, resp);
    }
}
