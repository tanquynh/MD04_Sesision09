package com.example.controller;

import com.example.model.dao.StudentDAOImpl;
import com.example.model.entity.Student;
import com.example.model.service.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private StudentServiceImpl studentService;

    @Override
    public void init()  {
            studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            int studentId = 0;
            if(action == null) {
                showStudentList(request, response);
            }else {
                switch (action) {
                    case "add":
                        request.getRequestDispatcher("views/student-add.jsp").forward(request,response);
                        break;
                    case "edit":
                        studentId = Integer.parseInt(request.getParameter("id"));
                        Student student = studentService.findById(studentId);
                        request.setAttribute("student", student);
                        request.getRequestDispatcher("views/student-edit.jsp").forward(request, response);
                        break;
                    case "delete":
                        studentId = Integer.parseInt(request.getParameter("id"));
                        studentService.delete(studentId);
                        showStudentList(request,response);
                        break;
                }
            }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String studentName = request.getParameter("studentName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int studentId;
        Date birthday;
        Student student;
        if (action == null) {
            showStudentList(request, response);
        } else {
            switch (action) {
                case "add":
//                    studentId = Integer.parseInt(request.getParameter("id"));
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date = simpleDateFormat.parse(request.getParameter("birthday"));
                        birthday = new Date(date.getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    student = new Student();
                    student.setStudentName(studentName);
                    student.setEmail(email);
                    student.setAddress(address);
                    student.setBirthday(birthday);
                    studentService.save(student);
                    showStudentList(request,response);
                    break;
                case "update":
                    studentId = Integer.parseInt(request.getParameter("id"));
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date = simpleDateFormat.parse(request.getParameter("birthday"));
                        birthday = new java.sql.Date(date.getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    student = new Student();
                    student.setStudentName(studentName);
                    student.setBirthday(birthday);
                    student.setEmail(email);
                    student.setAddress(address);
                    studentService.update(student, studentId);
                    showStudentList(request, response);
                    break;

                case "sort":
                    request.setAttribute("studentList", studentService.getSortedStudentList());
                    request.getRequestDispatcher("views/student.jsp").forward(request, response);
                    break;
            }
        }
    }



    @Override
    public void destroy() {
        super.destroy();
    }

    private void showStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentList = studentService.findAll();
        request.setAttribute("studentList",studentList);
        request.getRequestDispatcher("views/student.jsp").forward(request,response);
    }

}