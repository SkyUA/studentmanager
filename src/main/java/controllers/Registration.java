package controllers;

import database.DBConnection;
import entities.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.sql.*;

@WebServlet(name = "controllers.Registration", urlPatterns = {"/registration" })

public class Registration extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {

            String surname = request.getParameter("surname");
            String name = request.getParameter("name");
            String secondname = request.getParameter("secondname");

            DBConnection dbConnection = new DBConnection();
            List<Person> personList = dbConnection.getPersonList();
            boolean wrongInputData = false;
            for (Person person : personList) {
                if ((person.getSurname().equals(surname)) && (person.getName().equals(name)) && (person.getSecondname().equals(secondname))) {
                    wrongInputData = true;
                }
            }
            request.setAttribute("wrongInputData", wrongInputData);

            if (wrongInputData == true) {
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
            }

            if (wrongInputData == false) {
                Person person = new Person();
                person.setSurname(request.getParameter("surname"));
                person.setName(request.getParameter("name"));
                person.setSecondname(request.getParameter("secondname"));
                person.setRole(request.getParameter("role"));
                person.setLogin(request.getParameter("login"));
                person.setPassword(request.getParameter("password"));
                dbConnection.registerPerson(person);

                response.sendRedirect("/authorization");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

