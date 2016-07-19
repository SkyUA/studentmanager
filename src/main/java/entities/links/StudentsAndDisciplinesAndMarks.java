package entities.links;

public class StudentsAndDisciplinesAndMarks {
    private int id;
    private int idStudent;
    private int idDiscipline;
    private int idValue;

    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdStudent() {
        return idStudent;
    }
    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdDiscipline() {
        return idDiscipline;
    }
    public void setIdDiscipline(int idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public int getIdValue() {
        return idValue;
    }
    public void setIdValue(int idValue) { this.idValue = idValue; }
}
