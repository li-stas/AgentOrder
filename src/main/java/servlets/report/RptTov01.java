package servlets.report;

import dao.DAOConnection;
import dao.OracleDAOConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RptTov01 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> aHead = Arrays.asList("Код пр-ции", "Наименование продукции","Ед.изм","К-во, шт", "Сумма, грн");
        List<List<String>> aRecList = new ArrayList<List<String>>();

        DAOConnection daoConnection = OracleDAOConnection.getInstance();
        daoConnection.connect();
        Connection conn = daoConnection.getConnection();

        try {
            String cSql = "SELECT tov.MNTOV AS MnTov, NAT, NEI, " +
                    "       (SELECT SUM(KVP) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS Qt, " +
                    "       (SELECT SUM(KVP * ZEN) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS SumTov " +
                    "FROM AO_TOV tov " +
                    "ORDER BY Nat";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(cSql);
            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            if (rs.next()) {
                do {
                    List<String> columnList = new ArrayList<String>();
                    for (int i = 0; i < cols; i++) {
                        columnList.add(rs.getString(i + 1));
                    }
                    aRecList.add(columnList);
                } while (rs.next());
            }

            System.out.println("int cols = " + md.getColumnCount());
            System.out.println("aRecList = " + aRecList.toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        daoConnection.disconnect();


        req.setCharacterEncoding("UTF-8");

        req.setAttribute("aHead", aHead);
        req.setAttribute("aRecList", aRecList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/report/rpttov01.jsp");
        requestDispatcher.forward(req, resp);
    }
}
