package view.tm;

public class StudentTM implements Comparable<StudentTM>{
    private String studentId;
    private String studentName;
    private String nic;
    private String address;
    private String contact;

    public StudentTM() {
    }

    public StudentTM(String studentId, String studentName, String nic, String address, String contact) {
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setNic(nic);
        this.setAddress(address);
        this.setContact(contact);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    @Override
    public String toString() {
        return "StudentTM{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
    @Override
    public int compareTo(StudentTM o) {
        return studentId.compareTo(o.getStudentId());
    }
}
