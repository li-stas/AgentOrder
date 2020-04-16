package dao;


import entities.Student;

import java.sql.Connection;
import java.util.List;

public interface DAOConnection {
    void connect();
    void disconnect();
    Connection getConnection();
    List<String> getAboutConnect();
    List<Student> selectAllStudents();
    List<Student> selectStartsWith(String name);
    void createStudent(String name, float scholarShip);
    void updateStudent(int id, float sum);
    void deleteStudent(int id);
}
