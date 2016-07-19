function authorization_login_onfocus() {
    if (document.getElementById("login").value == 'Логин') {
        document.getElementById("login").value = '';
        document.getElementById("login").style.color = "black";
    }
}
function authorization_login_onblur() {
    if (document.getElementById("login").value == '') {
        document.getElementById("login").value = 'Логин';
        document.getElementById("login").style.color = "silver";
    }
}
function authorization_password_onfocus() {
    if (document.getElementById("password").value == 'Пароль') {
        document.getElementById("password").value = '';
        document.getElementById("password").style.color = "black";
    }
}
function authorization_password_onblur() {
    if (document.getElementById("password").value == '') {
        document.getElementById("password").value = 'Пароль';
        document.getElementById("password").style.color = "silver";
    }
}