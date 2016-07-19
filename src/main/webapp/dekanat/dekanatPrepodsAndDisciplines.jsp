<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>SM: Деканат: Преподователи / Дисцыплины</title>
    <link href="../css/main.css" rel="stylesheet" type="text/css">
    <link href="../css/dekanat/dekanatPrepodsAndDisciplines.css" rel="stylesheet" type="text/css">
    <script src="../js/dekanat.js"></script>
</head>
<body>
    <div id="welcome">
        Здравствуйте, ${surname} ${name} ${secondname}!
    </div>
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

                    <form method="post" action="/dekanat/dekanatPrepodsAndDisciplines">
                        <table>
                            <caption>Таблица активации аккаунтов преподователей</caption>
                            <thead>
                            <tr>
                                <td class="td_head">№</td>
                                <td class="td_head">Фамалия</td>
                                <td class="td_head">Имя</td>
                                <td class="td_head">Отчество</td>
                                <td class="td_head">Активация аккаунта</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="prepod" items="${prepodsListTest}" varStatus="inc">
                                <tr>
                                    <td>${inc.index + 1}</td>
                                    <td>${prepod.surname}</td>
                                    <td>${prepod.name}</td>
                                    <td>${prepod.secondname}</td>
                                    <td>
                                        <c:if test="${prepod.authorized == 'Аккаунт активирован!'}">
                                            <input type="checkbox" name="option${prepod.id}" checked>
                                        </c:if>

                                        <c:if test="${prepod.authorized == 'Аккаунт не активирован!'}">
                                            <input type="checkbox" name="option${prepod.id}">
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <table>
                            <caption>Таблица распределения преподователей по дисцыплинам</caption>
                            <thead>
                                <tr>
                                    <td>ФИО преподователя</td>
                                    <td>Дисцыплина</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="prepod" items="${prepodsListTest}">
                                    <tr>
                                        <td>${prepod.surname} ${prepod.name} ${prepod.secondname}</td>
                                        <td>
                                            <select name="select${prepod.id}">
                                                <c:forEach var="discipline" items="${disciplinesListTest}">
                                                    <c:set var="selected" value="false"/>

                                                    <c:forEach var="link" items="${disciplinesAndPrepodsListTest}">
                                                        <c:if test="${(discipline.id.equals(link.idDiscipline)) && (prepod.id.equals(link.idPrepod))}">
                                                            <c:set var="selected" value="true"/>
                                                        </c:if>
                                                    </c:forEach>

                                                    <c:if test="${selected == true}">
                                                        <option selected>${discipline.name}</option>
                                                    </c:if>

                                                    <c:if test="${selected == false}">
                                                        <option>${discipline.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="submit" name="input_prepods_authorization"  value="Сохранить внесенные изменения!">
                    </form>
                </div>
            </td>
            <td class="main"></td>
        </tr>
    </table>
</body>
</html>









