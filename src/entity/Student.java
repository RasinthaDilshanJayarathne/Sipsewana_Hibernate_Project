package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    private String sId;
    private String sName;
    private String nic;
    private String address;
    private String contact;

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Register> studentList;

    public Student() {
    }

    public Student(String sId, String sName, String nic, String address, String contact) {
        this.setsId(sId);
        this.setsName(sName);
        this.setNic(nic);
        this.setAddress(address);
        this.setContact(contact);
    }

    public Student(String sId, String sName, String nic, String address, String contact, List<Register> studentList) {
        this.setsId(sId);
        this.setsName(sName);
        this.setNic(nic);
        this.setAddress(address);
        this.setContact(contact);
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
                ", studentList=" + studentList +
                '}';
    }
}
