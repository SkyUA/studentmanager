<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>SM: Деканат: Группы / Дисцыплины</title>
    <link href="../css/main.css" rel="stylesheet" type="text/css">
    <link href="../css/dekanat/dekanatGroupsAndDisciplines.css" rel="stylesheet" type="text/css">
    <script src="../js/dekanat.js"></script>
    <script src="../js/dekanatGroupsAndDisciplines.js"></script>
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
                <td  class="main">
                    <div id="main_panel">
                        <div id="dekanat_menu">
                            <form method="get" action="/dekanat/dekanatGroupsAndDisciplines">
                                <input type="submit" name="submit1"  value="Группы / Дисцыплины" class="dekanat_menu" id="dekanat_menu1" onmouseover="dekanat_menu1_highlight()" onmouseout="dekanat_menu1_blackout()">
                            </form>

                            <form method="get" action="/dekanat/dekanatPrepodsAndDisciplines">
                                <input type="submit" name="submit2"  value="Преподователи / Дисцыплины" class="dekanat_menu" id="dekanat_menu2" onmouseover="dekanat_menu2_highlight()" onmouseout="dekanat_menu2_blackout()">
                            </form>

                            <form method="get" action="/dekanat/dekanatStudentsAndGroups">
                                <input type="submit" name="submit3"  value="Студенты / Группы" class="dekanat_menu" id="dekanat_menu3" onmouseover="dekanat_menu3_highlight()" onmouseout="dekanat_menu3_blackout()">
                            </form>
                        </div>

                        <form method="post" action="/dekanat/dekanatGroupsAndDisciplines">
                            <div id="dekanat_inputs_div">
                                <br>
                                <input type="text" onfocus="input_new_groupe_onfocus()" onblur="input_new_groupe_onblur()" value="Новая группа" name="input_new_groupe" class="input_text" id="input_new_groupe">
                                <input type="submit" name="input_save_new_groupe"  value="Добавить группу">
                                <br>
                                <br>
                                <select name="select_groupe_to_delete">
                                    <c:forEach var="group" items="${groupsListTest}">
                                        <option>${group.name}</option>
                                    </c:forEach>
                                </select>
                                <input type="submit" name="input_delete_group"  value="Удалить группу">
                                <br>
                                <br>
                                <input type="text" onfocus="input_new_discipline_onfocus()" onblur="input_new_discipline_onblur()" value="Новая дисцыплина" name="input_new_discipline" class="input_text" id="input_new_discipline">
                                <input type="submit" name="input_save_new_discipline"  value="Добавить дисцыплину">
                                <br>
                                <br>
                                <select name="select_discipline_to_delete">
                                    <c:forEach var="discipline" items="${disciplinesListTest}">
                                        <option>${discipline.name}</option>
                                    </c:forEach>
                                </select>
                                <input type="submit" name="input_delete_discipline"  value="Удалить дисцыплину">
                            </div>

                            <table>
                                <caption>Таблица распределения дисцыплин по группам</caption>
                                <thead>
                                <tr>
                                    <td>Дисцыплина</td>
                                    <c:forEach var="group" items="${groupsListTest}">
                                        <td>Группа ${group.name}</td>
                                    </c:forEach>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="discipline" items="${disciplinesListTest}">
                                    <tr>
                                        <c:if test="${!(discipline.name.equals(' '))}">
                                            <td>${discipline.name}</td>

                                            <c:forEach var="group" items="${groupsListTest}">
                                                <td>
                                                    <c:set var="match" value="false"/>

                                                    <c:forEach var="link" items="${groupsAndDisciplinesListTest}">
                                                        <c:if test="${(discipline.id.equals(link.idDiscipline)) && (group.id.equals(link.idGroup))}">
                                                            <c:set var="match" value="true"/>
                                                        </c:if>
                                                    </c:forEach>

                                                    <c:if test="${match == true}">
                                                        <input type="checkbox" name="option${discipline.id}_${group.id}" checked>
                                                    </c:if>

                                                    <c:if test="${match == false}">
                                                        <input type="checkbox" name="option${discipline.id}_${group.id}">
                                                    </c:if>
                                                </td>
                                            </c:forEach>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <input type="submit" name="input_save_table"  value="Сохранить внесенные изменения!">
                        </form>


                    </div>
                </td>
                <td class="main"></td>
            </tr>
        </table>
    </c:if>
</body>
</html>









