package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Register {
    @Id
    private String RegId;
    private LocalDate RegDate;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Program program;

    public Register() {
    }

    public Register(String regId, LocalDate regDate, Student student, Program program) {
        this.setRegId(regId);
        this.setRegDate(regDate);
        this.setStudent(student);
        this.setProgram(program);
    }

    public String getRegId() {
        return RegId;
    }

    public void setRegId(String regId) {
        RegId = regId;
    }

    public LocalDate getRegDate() {
        return RegDate;
    }

    public void setRegDate(LocalDate regDate) {
        RegDate = regDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Register{" +
                "RegId='" + RegId + '\'' +
                ", RegDate=" + RegDate +
                ", student=" + student +
                ", program=" + program +
                '}';
    }
}