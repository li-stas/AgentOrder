package servlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dao.DAOConnection;
import dao.OracleDAOConnection;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InfoConnect extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(InfoConnect.class);



    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        DAOConnection daoConnection = OracleDAOConnection.getInstance();
        List<String> aboutConnect = daoConnection.getAboutConnect();
        log.info("aboutConnect=" + aboutConnect);
        //System.out.println("aboutConnect=" + aboutConnect);
        req.setAttribute("aboutConnect", aboutConnect);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/infoconnect.jsp");
        requestDispatcher.forward(req, resp);
    }

}
