package dao;


import entities.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class OracleDAOConnection implements DAOConnection {

    private static OracleDAOConnection oracleDAOConnection;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private List<String> aboutConnect;


    private OracleDAOConnection() {
        super();
    }

    public static OracleDAOConnection getInstance() {
        if (oracleDAOConnection != null) {
            return oracleDAOConnection;
        } else {
            oracleDAOConnection = new OracleDAOConnection();
            return oracleDAOConnection;
        }
    }

    @Override
    public void connect() {
        //connectWebLogic();
        connectOracleDriver();
    }

    /*
     * https://docs.oracle.com/cd/A97329_03/web.902/a95879/ds.htm#1005742
     * mvn com.oracle.maven:oracle-maven-sync:push -DoracleHome=c:\oracle\middleware\oracle_home\
     * mvn install:install-file -DpomFile=oracle-maven-sync-12.2.1.pom -Dfile=oracle-maven-sync-12.2.1.jar
     */
    private void connectWebLogic() {

        Context ic = null;
        try {
            String sp = "weblogic.jndi.WLInitialContextFactory";
            String file = "t3://localhost:7001";
            String dataSourceName = "JNDINamedbStudentWL";

            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, sp);
            env.put(Context.PROVIDER_URL, file);
            ic = new InitialContext(env);

            DataSource ds = (DataSource) ic.lookup(dataSourceName);

            connection = ds.getConnection();

            DatabaseMetaData dma = connection.getMetaData();
            // Печать сообщения об успешном соединении
            System.out.println("\nWebLogic");
            System.out.println("Connected to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            System.out.println("Version " + dma.getDriverVersion());

        } catch (SQLException | NamingException e) {
            System.out.println("\n" + e.getMessage());
            //e.printStackTrace();
        }
    }

    public void connectOracleDriver() {
        String connectionUrl = "";
        String driverClass = "";
        String userName = "";
        String passWord = "";
        if (connectionUrl.isEmpty()) {
            connectionUrl = "jdbc:oracle:thin:@localhost:1521:xe";
            driverClass = "oracle.jdbc.OracleDriver";
            userName = "STUDENT";
            passWord = "admin";
        }
        try {
            Class.forName(driverClass);
            DriverManager.setLogStream(System.out);
            // Попытка соединения с драйвером. Каждый из
            // зарегистрированных драйверов будет загружаться, пока
            // не будет найден тот, который сможет обработать этот URL
            connection = DriverManager.getConnection(connectionUrl, userName, passWord);
            // Если соединиться не удалось, то произойдет exception (исключительная ситуация).
            // Получить DatabaseMetaData объект и показать информацию о соединении
            DatabaseMetaData dma = connection.getMetaData();
            // Печать сообщения об успешном соединении

            System.out.println("\njdbc");
            System.out.println("Connected to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            System.out.println("Version " + dma.getDriverVersion());

            aboutConnect = new ArrayList<>();
            aboutConnect.add("Type connect: jdbc");
            aboutConnect.add("Connected to: " + dma.getURL());
            aboutConnect.add("Driver      : " + dma.getDriverName());
            aboutConnect.add("Version     : " + dma.getDriverVersion());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void disconnect() {
        try {

            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            connection.close();
            System.out.println("Connection was closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---------READ-------------
    @Override
    public List<Student> selectAllStudents() {
        connect();
        List<Student> studentList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM STUDENTS ORDER BY NAME ASC");
            while (resultSet.next()) {
                studentList.add(parseStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        System.out.println("studentList->" + studentList);
        return studentList;
    }

    public List<Student> selectStartsWith(String name) {
        connect();
        List<Student> studentList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT * FROM STUDENTS WHERE Name LIKE '"
                            + name + "%"
                            + "' ORDER BY NAME ASC");

            if (resultSet.next()) {
                do {
                    studentList.add(parseStudent(resultSet));
                } while (resultSet.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        System.out.println("studentList->" + studentList);
        return studentList;
    }


    //---------Create-------------
    @Override
    public void createStudent(String name, float scholarShip) {
        connect();
        try {
            statement = connection.prepareStatement("INSERT INTO STUDENTS (NAME, SCHOLARSHIP)" +
                    "VALUES (?, ?)");
            ((PreparedStatement) statement).setString(1, name);
            ((PreparedStatement) statement).setFloat(2, scholarShip);
            ((PreparedStatement) statement).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    //---------Update-------------
    @Override
    public void updateStudent(int id, float sum) {
        connect();
        try {
            statement = connection.prepareStatement("UPDATE STUDENTS SET STUDENTS.SCHOLARSHIP = ? WHERE STUDENTS.ID = ?");
            ((PreparedStatement) statement).setFloat(1, sum);
            ((PreparedStatement) statement).setInt(2, id);
            ((PreparedStatement) statement).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    //---------Delete-------------
    @Override
    public void deleteStudent(int id) {
        connect();
        try {
            statement = connection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENTS.ID = ?");
            ((PreparedStatement) statement).setInt(1, id);
            ((PreparedStatement) statement).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }


    private Student parseStudent(ResultSet resultSet) {
        Student student = null;
        try {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            String email = resultSet.getString("EMAIL");
            String group = resultSet.getString("GROUPNO");
            float scholarship = resultSet.getFloat("SCHOLARSHIP");
            student = new Student(id, name, email, group, scholarship);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<String> getAboutConnect() {
        connect();
        disconnect();
        return aboutConnect;
    }
}
