<%--
  Created by IntelliJ IDEA.
  User: Mak
  Date: 11/13/2023
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body {
            padding: 5%;
        }
    </style>
</head>
<body>
<h1>Add new Student</h1>
<form action="<%=request.getContextPath()%>/student" method="post">
    <div class="mb-3">
        <label for="studentName" class="form-label fs-4">Student Name</label>
        <input type="text" class="form-control" id="studentName" name="studentName" placeholder="Nguyễn Văn A">
    </div>
    <div class="mb-3">
        <label for="Email" class="form-label fs-4">Student Name</label>
        <input type="text" class="form-control" id="Email" name="email" placeholder="email">
    </div>
    <div class="mb-3">
        <label for="Address" class="form-label fs-4">Student Name</label>
        <input type="text" class="form-control" id="Address" name="address" placeholder="address">
    </div>

    <div class="mb-3">
        <label for="birthday" class="form-label fs-4">Birthday</label>
        <input type="date" class="form-control" id="birthday" name="birthday">
    </div>
    <br>
    <input type="hidden" name="action" value="add">
    <button class="btn btn-primary" type="submit">Add</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
