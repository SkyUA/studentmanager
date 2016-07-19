<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>SM: Преподователь</title>
    <link href="../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="welcome">
        Здравствуйте, ${surname} ${name} ${secondname}!
    </div>

    <div id="warning">
        <c:if test="${authorized.equals('Аккаунт не активирован!')}">
            Ваш аакаунт еще не активирован! Пожалуйста, ожидайте...
        </c:if>
    </div>

    <c:if test="${authorized.equals('Аккаунт активирован!')}">
        <table class="main">
            <tr>
                <td class="main"></td>
                <td class="main"></td>
                <td class="main"></td>
                <td  class="main">
                    <div id="main_panel">
                        <form method="get" action="/prepod">
                            <br>
                            <b>Группа</b>
                            <select name="select_target_group" id="select_groupe_to_delete">
                                <c:forEach var="group" items="${groupsListTest}">
                                    <c:forEach var="discipline" items="${disciplinesListTest}">
                                        <c:forEach var="link" items="${groupsAndDisciplinesListTest}">
                                            <c:if test="${(group.id.equals(link.idGroup)) && (discipline.id.equals(link.idDiscipline) && (discipline.name.equals(targetDisciplineName)))}">
                                                <option>${group.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </c:forEach>
                            </select>
                            <input type="submit" name="input_delete_group"  value="Ok">
                        </form>

                        <form method="post" action="/prepod">
                            <table>
                                <caption>Таблица оценок студентов</caption>
                                <thead>
                                <tr>
                                    <td class="td_head">ФИО студента</td>
                                    <td class="td_head">Оценка</td>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="student" items="${studentsListTest}">
                                        <c:forEach var="group" items="${groupsListTest}">
                                            <c:forEach var="link" items="${groupsAndStudentsListTest}">
                                                <c:if test="${(group.id.equals(link.idGroup)) && (student.id.equals(link.idStudent) && (group.name.equals(targetGroupName)))}">
                                                    <tr>
                                                        <td>${student.surname} ${student.name} ${student.secondname}</td>
                                                        <td>
                                                            <select name="select${student.id}">
                                                                <c:forEach var="mark" items="${marksListTest}">

                                                                   <c:set var="selected" value="false"/>

                                                                   <c:forEach var="link2" items="${studentsAndDisciplinesAndMarksListTest}">
                                                                       <c:if test="${(student.id.equals(link2.idStudent)) && (mark.id.equals(link2.idValue) && (link2.idDiscipline == targetDisciplineId))}">
                                                                            <c:set var="selected" value="true"/>
                                                                        </c:if>
                                                                    </c:forEach>

                                                                    <c:if test="${selected == true}">
                                                                        <option selected>${mark.value}</option>
                                                                    </c:if>

                                                                    <c:if test="${selected == false}">
                                                                        <option>${mark.value}</option>
                                                                    </c:if>

                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <br>
                            <input type="submit" name="input_students_authorization"  value="Сохранить внесенные изменения!">
                        </form>
                    </div>
                </td>
                <td class="main"></td>
                <td class="main"></td>
                <td class="main"></td>
            </tr>
        </table>
    </c:if>
</body>
</html>