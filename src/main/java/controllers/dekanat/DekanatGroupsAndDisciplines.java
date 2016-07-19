package controllers.dekanat;

import database.DBConnection;
import entities.Discipline;
import entities.Group;
import entities.links.GroupsAndDisciplines;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.dekanat.DekanatGroupsAndDisciplines", urlPatterns = {"/dekanat/dekanatGroupsAndDisciplines" })
public class DekanatGroupsAndDisciplines extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String surname = String.valueOf(session.getAttribute("surname"));
        String name = String.valueOf(session.getAttribute("name"));
        String secondname = String.valueOf(session.getAttribute("secondname"));
        String authorized = String.valueOf(session.getAttribute("authorized"));

        DBConnection dbConnection = new DBConnection();

        List<Group> groupsListTest = dbConnection.getGroups();
        request.setAttribute("groupsListTest", groupsListTest);

        List<Discipline> disciplinesListTest = dbConnection.getDisciplines();
        request.setAttribute("disciplinesListTest", disciplinesListTest);

        List<GroupsAndDisciplines> groupsAndDisciplinesListTest = dbConnection.selectAllFromGroupsAndDisciplines();
        request.setAttribute("groupsAndDisciplinesListTest", groupsAndDisciplinesListTest);

        request.getRequestDispatcher("/dekanat/dekanatGroupsAndDisciplines.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBConnection dbConnection = new DBConnection();

            String newGroup = request.getParameter("input_new_groupe");
            String isPressedNewGroup = request.getParameter("input_save_new_groupe");
            if ((newGroup != "") && (newGroup != null) && (isPressedNewGroup != null)) {
                dbConnection.addGroup(newGroup);
            }

            String deleteGroup = request.getParameter("select_groupe_to_delete");
            String isPressedDeleteGroup = request.getParameter("input_delete_group");
            if (isPressedDeleteGroup != null) {
                dbConnection.deleteGroupByName(deleteGroup);
            }

            String newDiscipline = request.getParameter("input_new_discipline");
            String isPressedNewDiscipline = request.getParameter("input_save_new_discipline");
            if ((newDiscipline != "") && (newDiscipline != null) && (isPressedNewDiscipline != null)) {
                dbConnection.addDiscipline(newDiscipline);
            }

            String deleteDiscipline = request.getParameter("select_discipline_to_delete");
            String isPressedDeleteDisciplime = request.getParameter("input_delete_discipline");
            if (isPressedDeleteDisciplime != null) {
                dbConnection.deleteDisciplineByName(deleteDiscipline);
            }

            List<Group> groupListTest = dbConnection.getGroups();
            List<Discipline> disciplinesListTest = dbConnection.getDisciplines();
            List<GroupsAndDisciplines> groupsAndDisciplinesListtTest = dbConnection.selectAllFromGroupsAndDisciplines();

            for (Discipline discipline : disciplinesListTest) {
                for (Group group : groupListTest) {
                    String linkGroupToDiscipline = request.getParameter("option" + discipline.getId() + "_" + group.getId());
                    if (linkGroupToDiscipline != null) {
                        boolean match = false;
                        for (GroupsAndDisciplines groupsAndDisciplines : groupsAndDisciplinesListtTest) {
                            int arg1 = discipline.getId();
                            int arg2 = groupsAndDisciplines.getIdDiscipline();
                            int arg3 = group.getId();
                            int arg4 = groupsAndDisciplines.getIdGroup();
                            if ((arg1 == arg2) && (arg3 == arg4)) {
                                match = true;
                                break;
                            }
                        }
                        if (match == false) {
                            dbConnection.addLinkFromGroupToDiscipline(group.getId(), discipline.getId());
                        }
                    }
                    if (linkGroupToDiscipline == null) {
                        dbConnection.deleteLinkFromGroupToDiscipline(group.getId(), discipline.getId());
                    }
                }
            }
            response.sendRedirect("/dekanat/dekanatGroupsAndDisciplines");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
