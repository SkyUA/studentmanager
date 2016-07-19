package controllers.dekanat;

import database.DBConnection;
import entities.Discipline;
import entities.Group;
import entities.Person;
import entities.links.GroupsAndDisciplines;
import entities.links.GroupsAndStudents;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.dekanat.DekanatStudentsAndGroups", urlPatterns = {"/dekanat/dekanatStudentsAndGroups" })
public class DekanatStudentsAndGroups extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String surname = String.valueOf(session.getAttribute("surname"));
        String name = String.valueOf(session.getAttribute("name"));
        String secondname = String.valueOf(session.getAttribute("secondname"));
        String authorized = String.valueOf(session.getAttribute("authorized"));

        DBConnection dbConnection = new DBConnection();

        List<Person> studentsListTest = dbConnection.getAllStudents();
        request.setAttribute("studentsListTest", studentsListTest);

        List<Group> groupsListTest = dbConnection.getGroups();
        request.setAttribute("groupsListTest", groupsListTest);

        List<GroupsAndStudents> groupsAndStudentsListTest = dbConnection.selectLinkFromGroupToStudent();
        request.setAttribute("groupsAndStudentsListTest", groupsAndStudentsListTest);

        request.getRequestDispatcher("/dekanat/dekanatStudentsAndGroups.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBConnection dbConnection = new DBConnection();

            List<Group> groupListTest = dbConnection.getGroups();
            List<Person> studentsListTest = dbConnection.getAllStudents();
            List<GroupsAndStudents> groupsAndStudentsListTest = dbConnection.selectLinkFromGroupToStudent();

            for (Person person : studentsListTest) {
                String authorization = request.getParameter("option" + person.getId());
                dbConnection.auhorizePerson(person.getId(), authorization != null);
            }

            for (Group group : groupListTest) {
                for (Person student : studentsListTest) {
                    String linkGroupToStudent = request.getParameter("select" + student.getId());
                    if (group.getName().equals(linkGroupToStudent)) {

                        boolean match = false;
                        for (GroupsAndStudents groupsAndStudents : groupsAndStudentsListTest) {
                            int arg1 = student.getId();
                            int arg2 = groupsAndStudents.getIdStudent();
                            if (arg1 == arg2) {
                                match = true;
                                dbConnection.updateLinkFromGroupToStudent(group.getId(), student.getId(), groupsAndStudents.getId());
                            }
                        }
                        if (match == false) {
                            dbConnection.addLinkFromGroupToStudent(group.getId(), student.getId());
                        }
                    }
                }
            }
            response.sendRedirect("/dekanat/dekanatStudentsAndGroups");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
