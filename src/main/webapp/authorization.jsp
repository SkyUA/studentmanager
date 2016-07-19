<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset = "utf-8">
    <title>SM: Авторизация</title>
    <link href="../css/main.css" rel="stylesheet" type="text/css">
    <script src="../js/authorization.js"></script>
</head>
<body>
    <table class="main">
        <tr>
            <td class="main">

              qweqw  ${test} qw


                list:
                <c:forEach var="person" items="${personListTest}">
                    ${person.surname}
                </c:forEach>

            </td>
            <td class="main"></td>
            <td class="main"></td>
            <td class="main"></td>
            <td  class="main">
                <div id="main_panel">
                    <br>
                    <b>Авторизация</b>
                    <br>
                    <form method="post" action="/authorization">
                        <br>
                        <input type="text" onfocus="authorization_login_onfocus()" onblur="authorization_login_onblur()" value="Логин" name="login" class="input_text" id="login">
                        <br><br>
                        <input type="text" onfocus="authorization_password_onfocus()" onblur="authorization_password_onblur()" value="Пароль" name="password" class="input_text" id="password">
                        <br><br>
                        <input type="submit" name="Submit"  value="Вход" id="enter">
                        <br>
                    </form>
                    <A id="reg" href="/registration">Регистрация</A>
                    <br><br>
                </div>

                <div id="warning">
                    <c:if test="${wrongInputData == false}">
                        Введены ошибочные данные! Пожалуйста, введите новые...
                    </c:if>
                </div>
            </td>
            <td class="main"></td>
            <td class="main"></td>
            <td class="main"></td>
            <td class="main"></td>
        </tr>
    </table>
</body>
</html>

