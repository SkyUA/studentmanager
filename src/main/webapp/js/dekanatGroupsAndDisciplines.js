function input_new_groupe_onfocus() {
    if (document.getElementById("input_new_groupe").value == 'Новая группа') {
        document.getElementById("input_new_groupe").value = '';
        document.getElementById("input_new_groupe").style.color = "black";
    }
}
function input_new_groupe_onblur() {
    if (document.getElementById("input_new_groupe").value == '') {
        document.getElementById("input_new_groupe").value = 'Новая группа';
        document.getElementById("input_new_groupe").style.color = "silver";
    }
}
function input_new_discipline_onfocus() {
    if (document.getElementById("input_new_discipline").value == 'Новая дисцыплина') {
        document.getElementById("input_new_discipline").value = '';
        document.getElementById("input_new_discipline").style.color = "black";
    }
}
function input_new_discipline_onblur() {
    if (document.getElementById("input_new_discipline").value == '') {
        document.getElementById("input_new_discipline").value = 'Новая дисцыплина';
        document.getElementById("input_new_discipline").style.color = "silver";
    }
}
