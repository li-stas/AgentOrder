package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RptOrder01DAO {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;


    public RptOrder01DAO() {
    }

    private void connection() {
        this.connection = getConnection();
    }

    private static Connection getConnection() {
        DAOConnection daoConnection = OracleDAOConnection.getInstance();
        daoConnection.connect();
        Connection con = daoConnection.getConnection();
       /* Connection con = ConnectionFactory.
                getInstance().getConnection();*/
        return con;
    }

    private void disconnect() {
        try {

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            connection.close();
            System.out.println("Connection was closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<List<String>> viewAllRptOrder01() {
        connection();
        List<List<String>> aRecList = new ArrayList<List<String>>();
        try {
            String cSql = "SELECT s_tag.kod AS kta, s_tag.FIO AS FIO, "
                    + "(SELECT SUM(rs2.KVP * rs2.ZEN) AS SumOrd FROM  AO_RS1 rs1, AO_RS2 rs2 WHERE s_tag.kod = rs1.KTA and rs1.TTN = rs2.TTN) AS SumOrd,"
                    + "(SELECT COUNT(TTN) FROM AO_RS1 rs1 WHERE s_tag.kod = rs1.KTA) AS CntOrd "
                    + " FROM AO_S_TAG s_tag "
                    + " WHERE KTAS = 31 and KTAS <> Kod";

            stmt = connection.createStatement();

            rs = stmt.executeQuery(cSql);
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
        disconnect();
        return aRecList;
    }
}
