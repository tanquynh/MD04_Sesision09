package com.example.model.dao;

import com.example.model.entity.Student;
import com.example.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> findAll() {
        Connection connection = null;
        List<Student> studentList = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call FIND_ALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("studentId"));
                student.setStudentName(resultSet.getString("studentName"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setEmail(resultSet.getString("email"));
                student.setAddress(resultSet.getString("address"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return studentList;
    }


    @Override
    public boolean save(Student student) {
        Connection connection = ConnectionDB.openConnection();
        Student student1 = new Student();
        student1.setStudentName(student.getStudentName());
        student1.setAddress(student.getAddress());
        student1.setEmail(student.getEmail());
        student1.setBirthday(student.getBirthday());

        try {
            CallableStatement callableStatement = connection.prepareCall("{call ADD_STUDENT(?,?,?,?)}");
            callableStatement.setString(1, student1.getStudentName());
            callableStatement.setString(2, student1.getEmail());
            callableStatement.setString(3, student1.getAddress());
            java.util.Date utilDate = student.getBirthday();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            callableStatement.setDate(4, sqlDate);
            int check = callableStatement.executeUpdate();
            if (check > 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Student findById(Integer id) {
        Student student = new Student();
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call FIND_BY_ID(?)}");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                student.setStudentId(resultSet.getInt("studentId"));
                student.setStudentName(resultSet.getString("studentName"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddress(resultSet.getString("address"));
                student.setEmail(resultSet.getString("email"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return student;
    }

    @Override
    public boolean update(Student student, Integer id) {
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call EDIT_STUDENT(?, ? , ?, ?, ?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, student.getStudentName());
            callableStatement.setString(3, student.getEmail());
            callableStatement.setString(4, student.getAddress());
            java.util.Date utilDate = student.getBirthday();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            callableStatement.setDate(5, sqlDate);

            int check = callableStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{call DELETE_BY_ID(?)}");
            callableStatement.setInt(1, id);
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    public List<Student> getSortedStudentList() {
        List<Student> sortedStudentList = findAll();
        Collections.sort(sortedStudentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentName().compareTo(o2.getStudentName());
            }
        });
        System.out.println(sortedStudentList);
        return sortedStudentList;
    }


}
