package com.example.model.service;

import com.example.model.dao.StudentDAO;
import com.example.model.dao.StudentDAOImpl;
import com.example.model.entity.Student;

import java.util.List;

public class StudentServiceImpl implements IStudentService{
private final StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public boolean save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public Student findById(Integer integer) {
        return studentDAO.findById(integer);
    }

    @Override
    public boolean update(Student student, Integer integer) {
        return studentDAO.update(student,integer);
    }

    @Override
    public boolean delete(Integer integer) {
        return  studentDAO.delete(integer);
    }

}
