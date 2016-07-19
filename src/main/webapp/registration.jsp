<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset = "utf-8">
    <title>SM: Регистрация</title>
    <link href="../css/main.css" rel="stylesheet" type="text/css">
    <script src="../js/registration.js"></script>
</head>
<body>
    <table class="main">
        <tr class="main">
            <td class="main"></td>
            <td class="main"></td>
            <td class="main"></td>
            <td class="main"></td>
            <td  class="main">
                <div id="main_panel">
                    <br>
                    <b>Регистрация</b>
                    <form method="post" action="/registration">
                        <br>
                        <input type="text" onfocus="registration_surname_onfocus()" onblur="registration_surname_onblur()" value="Фамилия" name="surname" class="input_text" id="surname">
                        <br><br>
                        <input type="text" onfocus="registration_name_onfocus()" onblur="registration_name_onblur()" value="Имя" name="name" class="input_text"id="name">
                        <br><br>
                        <input type="text" onfocus="registration_secondname_onfocus()" onblur="registration_secondname_onblur()" value="Отчество" name="secondname" class="input_text" id="secondname">
                        <br><br>
                        <div id="ragistration_radio">
                            <input name="role" type="radio" value="Студент" checked>Студент
                            <br><br>
                            <input name="role" type="radio" value="Преподователь">Преподаватель
                            <br><br>
                            <input name="role" type="radio" value="Работник деканта">Работник деканта
                        </div>
                        <br>
                        <input type="text" onfocus="registration_login_onfocus()" onblur="registration_login_onblur()" value="Логин" name="login" class="input_text" id="login">
                        <br><br>
                        <input type="text" onfocus="registration_password_onfocus()" onblur="registration_password_onblur()" value="Пароль" type="password" name="password" class="input_text" id="password">
                        <br><br>
                        <input type="submit" name="Submit"  value="Зарегистрироваться!">
                    </form>
                </div>

                <div id="warning">
                    <c:if test="${wrongInputData == true}">
                        Аккаунт с такими фамилией, именем и отчеством уже существует! Пожалуйста, введите новые данные...
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
