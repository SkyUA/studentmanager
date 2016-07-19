<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>SM: Студент</title>
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
                <td  class="main">
                    <div id="main_panel">
                        <table>
                            <caption>Таблица оценок</caption>
                            <thead>
                            <tr>
                                <td class="td_head">Дисцыплина</td>
                                <td class="td_head">Оценка</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${selectStudentDisciplineMarkByStudentIdTest}" var="disciplineAndMark">
                                <tr>
                                    <td>${disciplineAndMark.disciplineName}</td>
                                    <td>${disciplineAndMark.markValue}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br>
                    </div>
                </td>
                <td class="main"></td>
                <td class="main"></td>
            </tr>
        </table>
    </c:if>
</body>
</html>
