<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>SM: Администратор</title>
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
                <td class="main">
                    <div id="main_panel">
                        <form method="post" action="/admin">
                            <table>
                                <caption>Таблица активации аккаунтов работников деканата</caption>
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
                                    <c:forEach var="dekanat" items="${dekanatListTest}" varStatus="inc">
                                    <tr>
                                        <td>${inc.index + 1}</td>
                                        <td>${dekanat.surname}</td>
                                        <td>${dekanat.name}</td>
                                        <td>${dekanat.secondname}</td>
                                        <td>
                                            <c:if test="${dekanat.authorized == 'Аккаунт активирован!'}">
                                                <input type="checkbox" name="option${dekanat.id}" checked>
                                            </c:if>

                                            <c:if test="${dekanat.authorized == 'Аккаунт не активирован!'}">
                                                <input type="checkbox" name="option${dekanat.id}">
                                            </c:if>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <br>
                            <input type="submit" name="submit"  value="Сохранить внесенные изменения!">
                        </form>
                    </div>
                </td>
                <td class="main"></td>
                <td class="main"></td>
            </tr>
        </table>
    </c:if>
</body>
</html>