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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "controllers.Authorization", urlPatterns = {"/authorization" })
public class Authorization extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection dbConnection = new DBConnection();
        List<Person> personList = dbConnection.getPersonList();
        //////////////
        List<Person> personListTest = dbConnection.getPersonList();
        request.setAttribute("personListTest", personListTest);


        request.setAttribute("test", dbConnection.connection != null ? "has con":"no con");
        ////////////////

        request.getRequestDispatcher("/authorization.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            DBConnection dbConnection = new DBConnection();
            List<Person> personList = dbConnection.getPersonList();


            /*//////////////
            List<Person> personListTest = dbConnection.getPersonList();
            request.setAttribute("personListTest", personListTest);


            request.setAttribute("test", dbConnection.connection != null ? "has con":"no con");
            ////////////////*/

            boolean wrongInputData = false;
            request.setAttribute("wrongInputData", wrongInputData);

            for (Person person : personList) {
                if (person.getLogin().equals(login)) {
                    if (person.getPassword().equals(password)) {
                        wrongInputData = true;

                        int id = person.getId();
                        String surname = person.getSurname();
                        String name = person.getName();
                        String secondname = person.getSecondname();
                        String authorized = person.getAuthorized();

                        HttpSession session = request.getSession(true);
                        session.setAttribute("id", id);
                        session.setAttribute("surname", surname);
                        session.setAttribute("name", name);
                        session.setAttribute("secondname", secondname);
                        session.setAttribute("authorized", authorized);

                        if (person.getRole().equals("Студент")) {
                            response.sendRedirect("/student");
                            //response.sendRedirect("studentmanager-skyua.rhcloud.com/student");


                        }
                        if (person.getRole().equals("Преподователь")) {
                            response.sendRedirect("/prepod");
                        }
                        if (person.getRole().equals("Работник деканата")) {
                            response.sendRedirect("/dekanat/dekanatGroupsAndDisciplines");
                        }
                        if (person.getRole().equals("Администратор")) {
                            response.sendRedirect("/admin");
                        }
                    }
                }
            }
            if (wrongInputData == false) {
                request.getRequestDispatcher("/authorization.jsp").forward(request, response);
                //response.sendRedirect("/authorization");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

