package controllers.dekanat;

import database.DBConnection;
import entities.Discipline;
import entities.Group;
import entities.Person;
import entities.links.DisciplinesAndPrepods;
import entities.links.GroupsAndDisciplines;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.dekanat.DekanatPrepodsAndDisciplines", urlPatterns = {"/dekanat/dekanatPrepodsAndDisciplines" })
public class DekanatPrepodsAndDisciplines extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String surname = String.valueOf(session.getAttribute("surname"));
        String name = String.valueOf(session.getAttribute("name"));
        String secondname = String.valueOf(session.getAttribute("secondname"));
        String authorized = String.valueOf(session.getAttribute("authorized"));

        DBConnection dbConnection = new DBConnection();

        List<Discipline> disciplinesListTest = dbConnection.getDisciplines();
        request.setAttribute("disciplinesListTest", disciplinesListTest);

        List<Person> prepodsListTest = dbConnection.getAllPrepods();
        request.setAttribute("prepodsListTest", prepodsListTest);

        List<DisciplinesAndPrepods> disciplinesAndPrepodsListTest = dbConnection.selectLinkFromDisciplineToPrepod();
        request.setAttribute("disciplinesAndPrepodsListTest", disciplinesAndPrepodsListTest);

        request.getRequestDispatcher("/dekanat/dekanatPrepodsAndDisciplines.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBConnection dbConnection = new DBConnection();
            List<Discipline> disciplinesListTest = dbConnection.getDisciplines();
            List<Person> prepodsListTest = dbConnection.getAllPrepods();
            List<DisciplinesAndPrepods> disciplinesAndPrepodsListTest = dbConnection.selectLinkFromDisciplineToPrepod();

            for (Person person : prepodsListTest) {
                String authorization = request.getParameter("option" + person.getId());
                dbConnection.auhorizePerson(person.getId(), authorization != null);
            }

            for (Discipline discipline : disciplinesListTest) {
                for (Person prepod : prepodsListTest) {
                    String linkDisciplineToPrepod = request.getParameter("select" + prepod.getId());
                    if (discipline.getName().equals(linkDisciplineToPrepod)) {

                        boolean match = false;
                        for (DisciplinesAndPrepods disciplinesAndPrepods : disciplinesAndPrepodsListTest) {
                            int arg1 = prepod.getId();
                            int arg2 = disciplinesAndPrepods.getIdPrepod();
                            if (arg1 == arg2) {
                                match = true;
                                dbConnection.updateLinkFromDisciplineToPrepod(discipline.getId(), prepod.getId(), disciplinesAndPrepods.getId());
                            }
                        }
                        if (match == false) {
                            dbConnection.addLinkFromDisciplineToPrepod(discipline.getId(), prepod.getId());
                        }
                    }
                }
            }
            response.sendRedirect("/dekanat/dekanatPrepodsAndDisciplines");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
