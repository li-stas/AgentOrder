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

public class RptOrder01 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<String> aHead = Arrays.asList("Код ТА", "ФИО ТА", "Сумма, грн", "К-во заказов");
        List<List<String>> aRecList = new ArrayList<List<String>>();

        DAOConnection daoConnection = OracleDAOConnection.getInstance();
        daoConnection.connect();
        Connection conn = daoConnection.getConnection();
        // "Код ТА", "ФИО ТА", "Сумма", "К-во заказов"
        try {
            String cSql = "SELECT s_tag.kod AS kta, s_tag.FIO AS FIO, "
                    + "(SELECT SUM(rs2.KVP * rs2.ZEN) AS SumOrd FROM  AO_RS1 rs1, AO_RS2 rs2 WHERE s_tag.kod = rs1.KTA and rs1.TTN = rs2.TTN) AS SumOrd,"
                    + "(SELECT COUNT(TTN) FROM AO_RS1 rs1 WHERE s_tag.kod = rs1.KTA) AS CntOrd "
                    + " FROM AO_S_TAG s_tag "
                    + " WHERE KTAS = 31 and KTAS <> Kod";

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




        req.setAttribute("aHead", aHead);
        req.setAttribute("aRecList", aRecList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/report/rptorder01.jsp");
        requestDispatcher.forward(req, resp);
    }
}
