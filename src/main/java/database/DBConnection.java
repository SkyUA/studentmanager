package database;

import entities.*;
import entities.links.DisciplinesAndPrepods;
import entities.links.GroupsAndDisciplines;
import entities.links.GroupsAndStudents;
import entities.links.StudentsAndDisciplinesAndMarks;
import sql.StudentDisciplineMark;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DBConnection {

    public Connection connection = null;
    private ResultSet resultSet = null;

    private PreparedStatement registerPerson;
    private PreparedStatement auhorizePerson;

    private PreparedStatement getPersons;
    private PreparedStatement getAllDekanat;
    private PreparedStatement getGroups;
    private PreparedStatement getDisciplines;

    private PreparedStatement addDiscipline;
    private PreparedStatement addGroup;

    private PreparedStatement getAllStudents;
    private PreparedStatement getAllPrepods;

    private PreparedStatement deleteGroup;
    private PreparedStatement deleteDiscipline;
    private PreparedStatement deleteGroupByName;
    private PreparedStatement deleteDisciplineByName;

    private PreparedStatement addLinkFromGroupToDiscipline;
    private PreparedStatement deleteLinkFromGroupToDiscipline;
    private PreparedStatement selectAllFromGroupsAndDisciplines;

    private PreparedStatement addLinkFromDisciplineToPrepod;
    private PreparedStatement updateLinkFromDisciplineToPrepod;
    private PreparedStatement selectLinkFromDisciplineToPrepod;

    private PreparedStatement addLinkFromGroupToStudent;
    private PreparedStatement updateLinkFromGroupToStudent;
    private PreparedStatement selectLinkFromGroupToStudent;

    private PreparedStatement getMarks;

    private PreparedStatement addLinkFromStudentToDisciplineToMark;
    private PreparedStatement updateLinkFromStudentToDisciplineToMark;
    private PreparedStatement selectLinkFromStudentToDisciplineToMark;

    private PreparedStatement selectStudentDisciplineMarkByStudentId;


    public DBConnection() {
        try {

            String  s = "jdbc:mysql://127.2.141.2:3306/studentmanager";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(s, "adminYaTLBXT", "7pH17eaCElmc");
            }
            catch (SQLException e) {

                e.printStackTrace();
            }





            /*String USERNAME = System.getEnv("OPENSHIFT_MYSQL_DB_USERNAME");
            String PASSWORD = System.getEnv("OPENSHIFT_MYSQL_DB_PASSWORD");
            String DB_NAME = System.getEnv("OPENSHIFT_APP_NAME");
            String FORNAME_URL = "com.mysql.jdbc.Driver";
            String URL = "jdbc:"+System.getEnv("OPENSHIFT_MYSQL_DB_URL")+ DB_NAME;
            Connection m_connection = DriverManager.getConnection(URL , USERNAME , PASSWORD);*/



            /*Class.forName("com.mysql.jdbc.Driver");

            String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
            String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
            String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");

            String url = String.format(":mysql://%s:%s/studentmanager", host, port);
            Connection conn = DriverManager.getConnection(url, username, password);*/






            /*String dbURL = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&characterEncoding=UTF-8",
                        System.getenv("OPENSHIFT_MYSQL_DB_HOST"),
                        System.getenv("OPENSHIFT_MYSQL_DB_PORT"),
                        System.getenv("OPENSHIFT_GEAR_NAME"),
                        System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"),
                        System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));*/

            /*Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/sms?user=root&password=root&characterEncoding=UTF-8";
            //String dbURL = "jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/sms?user=root&password=root&characterEncoding=UTF-8";
            //String dbURL = "jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/sms?user=adminYaTLBXT&password=7pH17eaCElmc&characterEncoding=UTF-8";
            //String dbURL = "jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/studentmanager?user=adminYaTLBXT&password=7pH17eaCElmc&characterEncoding=UTF-8";

            connection = DriverManager.getConnection(dbURL);*/

            registerPerson = connection.prepareStatement("INSERT INTO `person` (`surname`, `name`, `secondname`, `role`, `login`, `password`) VALUES (?, ?, ?, ?, ?, ?);");
            auhorizePerson = connection.prepareStatement("UPDATE person SET authorized = ? WHERE id = ? ");

            getPersons = connection.prepareStatement("SELECT * FROM person");
            getAllDekanat = connection.prepareStatement("SELECT * FROM person WHERE role = 'Работник деканата' ");
            getGroups = connection.prepareStatement("SELECT * FROM groups");
            getDisciplines = connection.prepareStatement("SELECT * FROM disciplines");

            addDiscipline = connection.prepareStatement("INSERT INTO `disciplines` (`name`) VALUES (?);");
            addGroup = connection.prepareStatement("INSERT INTO `groups` (`name`) VALUES (?);");

            getAllStudents = connection.prepareStatement("SELECT * FROM person WHERE role = 'Студент' ");
            getAllPrepods = connection.prepareStatement("SELECT * FROM person WHERE role = 'Преподователь' ");

            deleteGroup = connection.prepareStatement("DELETE FROM `groups` WHERE `id`= ?");
            deleteDiscipline = connection.prepareStatement("DELETE FROM `disciplines` WHERE `id`= ?");
            deleteGroupByName = connection.prepareStatement("DELETE FROM `groups` WHERE `name`= ?");
            deleteDisciplineByName = connection.prepareStatement("DELETE FROM `disciplines` WHERE `name`= ?");

            addLinkFromGroupToDiscipline = connection.prepareStatement("INSERT INTO `groups_disciplines` (`id_group`, `id_discipline`) VALUES (?, ?);");
            deleteLinkFromGroupToDiscipline = connection.prepareStatement("DELETE FROM `groups_disciplines` WHERE `id_group` = ? AND `id_discipline` = ?");
            selectAllFromGroupsAndDisciplines = connection.prepareStatement("SELECT * FROM groups_disciplines");

            addLinkFromDisciplineToPrepod = connection.prepareStatement("INSERT INTO `disciplines_prepods` (`id_discipline`, `id_prepod`) VALUES (?, ?);");
            updateLinkFromDisciplineToPrepod = connection.prepareStatement("UPDATE disciplines_prepods SET id_discipline = ?, id_prepod = ? WHERE id = ? ");
            selectLinkFromDisciplineToPrepod = connection.prepareStatement("SELECT * FROM disciplines_prepods");

            addLinkFromGroupToStudent = connection.prepareStatement("INSERT INTO `groups_students` (`id_group`, `id_student`) VALUES (?, ?);");
            updateLinkFromGroupToStudent = connection.prepareStatement("UPDATE groups_students SET id_group = ?, id_student = ? WHERE id = ? ");
            selectLinkFromGroupToStudent = connection.prepareStatement("SELECT * FROM groups_students");

            getMarks = connection.prepareStatement("SELECT * FROM marks");

            addLinkFromStudentToDisciplineToMark = connection.prepareStatement("INSERT INTO `students_disciplines_marks` (`id_student`, `id_discipline`, `id_mark`) VALUES (?, ?, ?);");
            updateLinkFromStudentToDisciplineToMark = connection.prepareStatement("UPDATE students_disciplines_marks SET id_student = ?, id_discipline = ?, id_mark = ? WHERE id = ? ");
            selectLinkFromStudentToDisciplineToMark = connection.prepareStatement("SELECT * FROM students_disciplines_marks");

            selectStudentDisciplineMarkByStudentId = connection.prepareStatement("SELECT `person`.`surname`,`disciplines`.`name`,`marks`.`value` " +
                                                                                 "FROM `students_disciplines_marks` " +
                                                                                 "JOIN `person` ON (`person`.`id` = `id_student`) " +
                                                                                 "JOIN `disciplines` ON (`disciplines`.`id` = `id_discipline`) " +
                                                                                 "JOIN `marks` ON (`marks`.`id` = `id_mark`) " +
                                                                                 "WHERE `person`.`id` = ? ");
        //} catch (ClassNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
        /*} catch (SQLException e) {
            e.printStackTrace();*/
        }
    }

    public void registerPerson(Person person) {
        try {
            registerPerson.setString(1, person.getSurname());
            registerPerson.setString(2, person.getName());
            registerPerson.setString(3, person.getSecondname());
            registerPerson.setString(4, person.getRole());
            registerPerson.setString(5, person.getLogin());
            registerPerson.setString(6, person.getPassword());
            registerPerson.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void auhorizePerson(int id, boolean authorize) {
        try {
            auhorizePerson.setString(1, authorize?"Аккаунт активирован!":"Аккаунт не активирован!");
            auhorizePerson.setInt(2, id);
            auhorizePerson.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPersonList() {
        resultSet = null;
        List<Person> personList = new ArrayList<Person>();
        try {
            resultSet = getPersons.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setSurname(resultSet.getString("surname"));
                person.setName(resultSet.getString("name"));
                person.setSecondname(resultSet.getString("secondname"));
                person.setRole(resultSet.getString("role"));
                person.setLogin(resultSet.getString("login"));
                person.setPassword(resultSet.getString("password"));
                person.setAuthorized(resultSet.getString("authorized"));
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public List<Person> getAllDekanat() {
        resultSet = null;
        List<Person> dekanatList = new ArrayList<Person>();
        try {
            resultSet = getAllDekanat.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(Integer.parseInt(resultSet.getString("id")));
                person.setSurname(resultSet.getString("surname"));
                person.setName(resultSet.getString("name"));
                person.setSecondname(resultSet.getString("secondname"));
                person.setAuthorized(resultSet.getString("authorized"));
                dekanatList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dekanatList;
    }

    public List<Group> getGroups() {
        resultSet = null;
        List<Group> groupsList = new ArrayList<Group>();
        try {
            resultSet = getGroups.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(Integer.parseInt(resultSet.getString("id")));
                group.setName(resultSet.getString("name"));
                groupsList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupsList;
    }

    public List<Discipline> getDisciplines() {
        resultSet = null;
        List<Discipline> disciplinesList = new ArrayList<Discipline>();
        try {
            resultSet = getDisciplines.executeQuery();
            while (resultSet.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(Integer.parseInt(resultSet.getString("id")));
                discipline.setName(resultSet.getString("name"));
                disciplinesList.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinesList;
    }

    public void addDiscipline(String discipline) {
        try {
            addDiscipline.setString(1, discipline);
            addDiscipline.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGroup(String group) {
        try {
            addGroup.setString(1, group);
            addGroup.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllStudents() {
        resultSet = null;
        List<Person> studentsList = new ArrayList<Person>();
        try {
            resultSet = getAllStudents.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(Integer.parseInt(resultSet.getString("id")));
                person.setSurname(resultSet.getString("surname"));
                person.setName(resultSet.getString("name"));
                person.setSecondname(resultSet.getString("secondname"));
                person.setAuthorized(resultSet.getString("authorized"));
                studentsList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    public List<Person> getAllPrepods() {
        resultSet = null;
        List<Person> prepodsList = new ArrayList<Person>();
        try {
            resultSet = getAllPrepods.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(Integer.parseInt(resultSet.getString("id")));
                person.setSurname(resultSet.getString("surname"));
                person.setName(resultSet.getString("name"));
                person.setSecondname(resultSet.getString("secondname"));
                person.setAuthorized(resultSet.getString("authorized"));
                prepodsList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prepodsList;
    }

    public void deleteGroup(int id) {
        try {
            deleteGroup.setInt(1, id);
            deleteGroup.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroupByName(String name) {
        try {
            deleteGroupByName.setString(1, name);
            deleteGroupByName.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDiscipline(int id) {
        try {
            deleteDiscipline.setInt(1, id);
            deleteDiscipline.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDisciplineByName(String name) {
        try {
            deleteDisciplineByName.setString(1, name);
            deleteDisciplineByName.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLinkFromGroupToDiscipline(int groupId, int disciplineId) {
        try {
            addLinkFromGroupToDiscipline.setInt(1, groupId);
            addLinkFromGroupToDiscipline.setInt(2, disciplineId);
            addLinkFromGroupToDiscipline.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLinkFromGroupToDiscipline(int groupId, int disciplineId) {
        try {
            deleteLinkFromGroupToDiscipline.setInt(1, groupId);
            deleteLinkFromGroupToDiscipline.setInt(2, disciplineId);
            deleteLinkFromGroupToDiscipline.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GroupsAndDisciplines> selectAllFromGroupsAndDisciplines() {
        resultSet = null;
        List<GroupsAndDisciplines> groupsAndDisciplinesList = new ArrayList<GroupsAndDisciplines>();
        try {
            resultSet = selectAllFromGroupsAndDisciplines.executeQuery();
            while (resultSet.next()) {
                GroupsAndDisciplines groupsAndDisciplines = new GroupsAndDisciplines();
                groupsAndDisciplines.setId(Integer.parseInt(resultSet.getString("id")));
                groupsAndDisciplines.setIdGroup(Integer.parseInt(resultSet.getString("id_group")));
                groupsAndDisciplines.setIdDiscipline(Integer.parseInt(resultSet.getString("id_discipline")));
                groupsAndDisciplinesList.add(groupsAndDisciplines);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupsAndDisciplinesList;
    }

    public void addLinkFromDisciplineToPrepod(int disciplineId, int prepodId) {
        try {
            addLinkFromDisciplineToPrepod.setInt(1, disciplineId);
            addLinkFromDisciplineToPrepod.setInt(2, prepodId);
            addLinkFromDisciplineToPrepod.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLinkFromDisciplineToPrepod(int disciplineId, int prepodId, int id) {
        try {
            updateLinkFromDisciplineToPrepod.setInt(1, disciplineId);
            updateLinkFromDisciplineToPrepod.setInt(2, prepodId);
            updateLinkFromDisciplineToPrepod.setInt(3, id);
            updateLinkFromDisciplineToPrepod.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DisciplinesAndPrepods> selectLinkFromDisciplineToPrepod() {
        resultSet = null;
        List<DisciplinesAndPrepods> disciplinesAndPrepodsList = new ArrayList<DisciplinesAndPrepods>();
        try {
            resultSet = selectLinkFromDisciplineToPrepod.executeQuery();
            while (resultSet.next()) {
                DisciplinesAndPrepods disciplinesAndPrepods = new DisciplinesAndPrepods();
                disciplinesAndPrepods.setId(Integer.parseInt(resultSet.getString("id")));
                disciplinesAndPrepods.setIdDiscipline(Integer.parseInt(resultSet.getString("id_discipline")));
                disciplinesAndPrepods.setIdPrepod(Integer.parseInt(resultSet.getString("id_prepod")));
                disciplinesAndPrepodsList.add(disciplinesAndPrepods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinesAndPrepodsList;
    }

    public void addLinkFromGroupToStudent(int groupId, int studentId) {
        try {
            addLinkFromGroupToStudent.setInt(1, groupId);
            addLinkFromGroupToStudent.setInt(2, studentId);
            addLinkFromGroupToStudent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLinkFromGroupToStudent(int groupId, int studentId, int id) {
        try {
            updateLinkFromGroupToStudent.setInt(1, groupId);
            updateLinkFromGroupToStudent.setInt(2, studentId);
            updateLinkFromGroupToStudent.setInt(3, id);
            updateLinkFromGroupToStudent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GroupsAndStudents> selectLinkFromGroupToStudent() {
        resultSet = null;
        List<GroupsAndStudents> groupsAndStudentsList = new ArrayList<GroupsAndStudents>();
        try {
            resultSet = selectLinkFromGroupToStudent.executeQuery();
            while (resultSet.next()) {
                GroupsAndStudents groupsAndStudents = new GroupsAndStudents();
                groupsAndStudents.setId(Integer.parseInt(resultSet.getString("id")));
                groupsAndStudents.setIdGroup(Integer.parseInt(resultSet.getString("id_group")));
                groupsAndStudents.setIdStudent(Integer.parseInt(resultSet.getString("id_student")));
                groupsAndStudentsList.add(groupsAndStudents);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupsAndStudentsList;
    }

    public List<Mark> getMarks() {
        resultSet = null;
        List<Mark> marksList = new ArrayList<Mark>();
        try {
            resultSet = getMarks.executeQuery();
            while (resultSet.next()) {
                Mark mark = new Mark();
                mark.setId(resultSet.getInt("id"));
                mark.setValue(resultSet.getInt("value"));
                marksList.add(mark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marksList;
    }

    public void addLinkFromStudentToDisciplineToMark(int studentId, int disciplineId, int markId) {
        try {
            addLinkFromStudentToDisciplineToMark.setInt(1, studentId);
            addLinkFromStudentToDisciplineToMark.setInt(2, disciplineId);
            addLinkFromStudentToDisciplineToMark.setInt(3, markId);
            addLinkFromStudentToDisciplineToMark.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLinkFromStudentToDisciplineToMark(int studentId, int disciplineId, int markId, int id) {
        try {
            updateLinkFromStudentToDisciplineToMark.setInt(1, studentId);
            updateLinkFromStudentToDisciplineToMark.setInt(2, disciplineId);
            updateLinkFromStudentToDisciplineToMark.setInt(3, markId);
            updateLinkFromStudentToDisciplineToMark.setInt(4, id);
            updateLinkFromStudentToDisciplineToMark.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StudentsAndDisciplinesAndMarks> selectLinkFromStudentToDisciplineToMark() {
        resultSet = null;
        List<StudentsAndDisciplinesAndMarks> studentsAndDisciplinesAndMarksList = new ArrayList<StudentsAndDisciplinesAndMarks>();
        try {
            resultSet = selectLinkFromStudentToDisciplineToMark.executeQuery();
            while (resultSet.next()) {
                StudentsAndDisciplinesAndMarks studentsAndDisciplinesAndMarks = new StudentsAndDisciplinesAndMarks();
                studentsAndDisciplinesAndMarks.setId(Integer.parseInt(resultSet.getString("id")));
                studentsAndDisciplinesAndMarks.setIdStudent(Integer.parseInt(resultSet.getString("id_student")));
                studentsAndDisciplinesAndMarks.setIdDiscipline(Integer.parseInt(resultSet.getString("id_discipline")));
                studentsAndDisciplinesAndMarks.setIdValue(Integer.parseInt(resultSet.getString("id_mark")));
                studentsAndDisciplinesAndMarksList.add(studentsAndDisciplinesAndMarks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsAndDisciplinesAndMarksList;
    }

    public List<StudentDisciplineMark> selectStudentDisciplineMarkByStudentId(int studentId) {
        resultSet = null;
        List<StudentDisciplineMark> StudentDisciplineMarkList = new ArrayList<StudentDisciplineMark>();
        try {
            selectStudentDisciplineMarkByStudentId.setInt(1, studentId);
            resultSet = selectStudentDisciplineMarkByStudentId.executeQuery();
            while (resultSet.next()) {
                StudentDisciplineMark studentDisciplineMark = new StudentDisciplineMark();
                studentDisciplineMark.setStudentSurname(resultSet.getString("surname"));
                studentDisciplineMark.setDisciplineName(resultSet.getString("name"));
                studentDisciplineMark.setMarkValue(Integer.parseInt(resultSet.getString("value")));
                StudentDisciplineMarkList.add(studentDisciplineMark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentDisciplineMarkList;
    }

}