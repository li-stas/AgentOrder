package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RptTov01DAO {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;


    public RptTov01DAO() {
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

    public List<List<String>> viewAllRptTov02() {
        connection();
        List<List<String>> aRecList = new ArrayList<List<String>>();
        try {
            String cSql = "SELECT tov.MNTOV AS MnTov, NAT, NEI, " +
                    "       (SELECT SUM(KVP) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS Qt, " +
                    "       (SELECT SUM(KVP * ZEN) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS SumTov " +
                    "FROM AO_TOV tov " +
                    "ORDER BY Nat";

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
