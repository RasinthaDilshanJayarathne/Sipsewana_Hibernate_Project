package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student {
    @Id
    private String sId;
    private String sName;
    private String nic;
    private String address;
    private String contact;
    private String program;

    @OneToMany(mappedBy = "student")
    private List<Register> studentList;

    public Student() {
    }

    public Student(String sId, String sName, String nic, String address, String contact, String program) {
        this.setsId(sId);
        this.setsName(sName);
        this.setNic(nic);
        this.setAddress(address);
        this.setContact(contact);
        this.setProgram(program);
    }

    public Student(String sId, String sName, String nic, String address, String contact, String program, List<Register> studentList) {
        this.setsId(sId);
        this.setsName(sName);
        this.setNic(nic);
        this.setAddress(address);
        this.setContact(contact);
        this.setProgram(program);
        this.setStudentList(studentList);
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public List<Register> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Register> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", program='" + program + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
