package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RptTov02DAO {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private int noOfRecords;

    public RptTov02DAO() {
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

    public List<List<String>> viewAllRptTov02(int page, int recordsPerPage) {
        int offset = (page - 1) * recordsPerPage;
        int noOfRecords = recordsPerPage;

        int pageNumber = page;
        int pageSize = recordsPerPage;

        connection();
        List<List<String>> aRecList = new ArrayList<List<String>>();
        try {
            /*
            https://stackoverflow.com/questions/241622/paging-with-oracle
             */
            String cSql = String.format("SELECT * FROM " +
                    "    ( " +
                    "        SELECT a.*, rownum r__ " +
                    "        FROM " +
                    "            ( " +
                    "                SELECT tov.MNTOV AS MnTov, NAT, NEI, " +
                    "                       (SELECT SUM(KVP) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS Qt, " +
                    "                       (SELECT SUM(KVP * ZEN) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS SumTov " +
                    "                FROM AO_TOV tov " +
                    "                ORDER BY Nat " +
                    "            ) a " +
                    "        WHERE rownum < ((%d * %d) + 1 ) " +
                    "    ) " +
                    "WHERE r__ >= (((%d - 1) * %d) + 1)", pageNumber, pageSize, pageNumber, pageSize);

            stmt = connection.createStatement();

            rs = stmt.executeQuery(cSql);
            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            //this.noOfRecords = 0;
            if (rs.next()) {
                do {
                    this.noOfRecords++;
                    List<String> columnList = new ArrayList<String>();
                    for (int i = 0; i < cols; i++) {
                        columnList.add(rs.getString(i + 1));
                    }
                    aRecList.add(columnList);
                } while (rs.next());


                cSql = "SELECT COUNT(*) FROM AO_TOV ORDER BY Nat";
                stmt = connection.createStatement();
                rs = stmt.executeQuery(cSql);
                if (rs.next()) {
                    this.noOfRecords = rs.getInt(1);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return aRecList;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
