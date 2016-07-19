package controllers;

import database.DBConnection;
import entities.Discipline;
import entities.Person;
import controllers.Authorization;
import entities.links.StudentsAndDisciplinesAndMarks;
import sql.StudentDisciplineMark;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.Student", urlPatterns = {"/student" })
public class Student extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String id = String.valueOf(session.getAttribute("id"));
        String surname = String.valueOf(session.getAttribute("surname"));
        String name = String.valueOf(session.getAttribute("name"));
        String secondname = String.valueOf(session.getAttribute("secondname"));
        String authorized = String.valueOf(session.getAttribute("authorized"));

        DBConnection dbConnection = new DBConnection();
        List<StudentDisciplineMark> selectStudentDisciplineMarkByStudentIdTest = dbConnection.selectStudentDisciplineMarkByStudentId(Integer.parseInt(id));
        request.setAttribute("selectStudentDisciplineMarkByStudentIdTest", selectStudentDisciplineMarkByStudentIdTest);

        request.getRequestDispatcher("/student.jsp").forward(request, response);
    }
}
