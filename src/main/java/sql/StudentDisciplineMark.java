package sql;

public class StudentDisciplineMark {
    private String studentSurname;
    private String disciplineName;
    private int markValue;

    public String getStudentSurname() {
        return studentSurname;
    }
    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getDisciplineName() {
        return disciplineName;
    }
    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public int getMarkValue() {
        return markValue;
    }
    public void setMarkValue(int markValue) { this.markValue = markValue; }
}