package controllers;

import database.DBConnection;
import entities.Discipline;
import entities.Group;
import entities.Mark;
import entities.Person;
import entities.links.DisciplinesAndPrepods;
import entities.links.GroupsAndDisciplines;
import entities.links.GroupsAndStudents;
import entities.links.StudentsAndDisciplinesAndMarks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.Prepod", urlPatterns = {"/prepod" })
public class Prepod extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String id = String.valueOf(session.getAttribute("id"));
        String surname = String.valueOf(session.getAttribute("surname"));
        String name = String.valueOf(session.getAttribute("name"));
        String secondname = String.valueOf(session.getAttribute("secondname"));
        String authorized = String.valueOf(session.getAttribute("authorized"));

        DBConnection dbConnection = new DBConnection();

        List<Person> studentsListTest = dbConnection.getAllStudents();
        request.setAttribute("studentsListTest", studentsListTest);

        List<Group> groupsListTest = dbConnection.getGroups();
        request.setAttribute("groupsListTest", groupsListTest);

        List<Discipline> disciplinesListTest = dbConnection.getDisciplines();
        request.setAttribute("disciplinesListTest", disciplinesListTest);

        List<Mark> marksListTest = dbConnection.getMarks();
        request.setAttribute("marksListTest", marksListTest);

        List<GroupsAndDisciplines> groupsAndDisciplinesListTest = dbConnection.selectAllFromGroupsAndDisciplines();
        request.setAttribute("groupsAndDisciplinesListTest", groupsAndDisciplinesListTest);

        List<GroupsAndStudents> groupsAndStudentsListTest = dbConnection.selectLinkFromGroupToStudent();
        request.setAttribute("groupsAndStudentsListTest", groupsAndStudentsListTest);

        List<DisciplinesAndPrepods> disciplinesAndPrepodsListTest = dbConnection.selectLinkFromDisciplineToPrepod();
        request.setAttribute("disciplinesAndPrepodsListTest", disciplinesAndPrepodsListTest);

        List<StudentsAndDisciplinesAndMarks> studentsAndDisciplinesAndMarksListTest = dbConnection.selectLinkFromStudentToDisciplineToMark();
        request.setAttribute("studentsAndDisciplinesAndMarksListTest", studentsAndDisciplinesAndMarksListTest);

        for (DisciplinesAndPrepods disciplinesAndPrepods : disciplinesAndPrepodsListTest) {
            for (Discipline discipline : disciplinesListTest) {
                String arg1 = id;
                String arg2 = Integer.toString(disciplinesAndPrepods.getIdPrepod());
                String arg3 = Integer.toString(discipline.getId());
                String arg4 = Integer.toString(disciplinesAndPrepods.getIdDiscipline());

                if (arg1.equals(arg2) && arg3.equals(arg4)) {
                    String targetDisciplineName = discipline.getName();
                    request.setAttribute("targetDisciplineName", targetDisciplineName);

                    String targetDisciplineId = Integer.toString(discipline.getId());
                    request.setAttribute("targetDisciplineId", targetDisciplineId);
                }
            }
        }

        String targetGroup = request.getParameter("select_target_group");
        String targetGroupName;
        if (targetGroup == null) {
            targetGroupName = groupsListTest.get(0).getName();
            request.setAttribute("targetGroupName", targetGroupName);
        }
        if (targetGroup != null) {
            targetGroupName = targetGroup;
            request.setAttribute("targetGroupName", targetGroupName);
        }
        request.getRequestDispatcher("/prepod.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            HttpSession session = request.getSession(true);
            String id = String.valueOf(session.getAttribute("id"));

            DBConnection dbConnection = new DBConnection();
            List<Person> studentsListTest = dbConnection.getAllStudents();
            List<Discipline> disciplinesListTest = dbConnection.getDisciplines();
            List<Mark> marksListTest = dbConnection.getMarks();
            List<DisciplinesAndPrepods> disciplinesAndPrepodsListTest = dbConnection.selectLinkFromDisciplineToPrepod();
            List<StudentsAndDisciplinesAndMarks> studentsAndDisciplinesAndMarksListTest = dbConnection.selectLinkFromStudentToDisciplineToMark();

            int targetDisciplineId = 0;
            for (DisciplinesAndPrepods disciplinesAndPrepods : disciplinesAndPrepodsListTest) {
                for (Discipline discipline : disciplinesListTest) {
                    String arg1 = id;
                    String arg2 = Integer.toString(disciplinesAndPrepods.getIdPrepod());
                    String arg3 = Integer.toString(discipline.getId());
                    String arg4 = Integer.toString(disciplinesAndPrepods.getIdDiscipline());

                    if (arg1.equals(arg2) && arg3.equals(arg4)) {
                        targetDisciplineId = discipline.getId();
                    }
                }
            }

            for (Mark mark : marksListTest) {
                for (Person student : studentsListTest) {
                    String targetMark = request.getParameter("select" + student.getId());
                    if (targetMark != null) {
                        if (Integer.toString(mark.getId()).equals(targetMark)) {
                            boolean match = false;
                            for (StudentsAndDisciplinesAndMarks studentsAndDisciplinesAndMarks : studentsAndDisciplinesAndMarksListTest) {
                                int arg1 = student.getId();
                                int arg2 = studentsAndDisciplinesAndMarks.getIdStudent();
                                int arg3 = targetDisciplineId;
                                int arg4 = studentsAndDisciplinesAndMarks.getIdDiscipline();

                                if ((arg1 == arg2) && (arg3 == arg4)) {
                                    match = true;
                                    dbConnection.updateLinkFromStudentToDisciplineToMark(student.getId(), targetDisciplineId, mark.getId(), studentsAndDisciplinesAndMarks.getId());
                                }
                            }
                            if (match == false) {
                                dbConnection.addLinkFromStudentToDisciplineToMark(student.getId(), targetDisciplineId, mark.getId());
                            }
                        }
                    }
                }
            }
            response.sendRedirect("/prepod");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
