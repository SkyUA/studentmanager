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

@WebServlet(name = "controllers.Admin", urlPatterns = {"/admin" })
public class Admin extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String surname = String.valueOf(session.getAttribute("surname"));
        String name = String.valueOf(session.getAttribute("name"));
        String secondname = String.valueOf(session.getAttribute("secondname"));

        DBConnection dbConnection = new DBConnection();
        List<Person> dekanatListTest = dbConnection.getAllDekanat();
        request.setAttribute("dekanatListTest", dekanatListTest);

        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBConnection dbConnection = new DBConnection();
            List<Person> dekanatListTest = dbConnection.getAllDekanat();
            for (Person person : dekanatListTest) {
                String authorization = request.getParameter("option" + person.getId());
                dbConnection.auhorizePerson(person.getId(), authorization != null);
            }
            response.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
