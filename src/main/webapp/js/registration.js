function registration_surname_onfocus() {
    if (document.getElementById("surname").value == 'Фамилия') {
        document.getElementById("surname").value = '';
        document.getElementById("surname").style.color = "black";
    }
}
function registration_surname_onblur() {
    if (document.getElementById("surname").value == '') {
        document.getElementById("surname").value = 'Фамилия';
        document.getElementById("surname").style.color = "silver";
    }
}
function registration_name_onfocus() {
    if (document.getElementById("name").value == 'Имя') {
        document.getElementById("name").value = '';
        document.getElementById("name").style.color = "black";
    }
}
function registration_name_onblur() {
    if (document.getElementById("name").value == '') {
        document.getElementById("name").value = 'Имя';
        document.getElementById("name").style.color = "silver";
    }
}
function registration_secondname_onfocus() {
    if (document.getElementById("secondname").value == 'Отчество') {
        document.getElementById("secondname").value = '';
        document.getElementById("secondname").style.color = "black";
    }
}
function registration_secondname_onblur() {
    if (document.getElementById("secondname").value == '') {
        document.getElementById("secondname").value = 'Отчество';
        document.getElementById("secondname").style.color = "silver";
    }
}
function registration_login_onfocus() {
    if (document.getElementById("login").value == 'Логин') {
        document.getElementById("login").value = '';
        document.getElementById("login").style.color = "black";
    }
}
function registration_login_onblur() {
    if (document.getElementById("login").value == '') {
        document.getElementById("login").value = 'Логин';
        document.getElementById("login").style.color = "silver";
    }
}
function registration_password_onfocus() {
    if (document.getElementById("password").value == 'Пароль') {
        document.getElementById("password").value = '';
        document.getElementById("password").style.color = "black";
    }
}
function registration_password_onblur() {
    if (document.getElementById("password").value == '') {
        document.getElementById("password").value = 'Пароль';
        document.getElementById("password").style.color = "silver";
    }
}
