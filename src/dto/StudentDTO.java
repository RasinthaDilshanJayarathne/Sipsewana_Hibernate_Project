package dto;

import java.io.Serializable;

public class StudentDTO implements Serializable {
    private String sId;
    private String sName;
    private String nic;
    private String address;
    private String contact;

    public StudentDTO() {
    }

    public StudentDTO(String sId, String sName, String nic, String address, String contact) {
        this.setsId(sId);
        this.setsName(sName);
        this.setNic(nic);
        this.setAddress(address);
        this.setContact(contact);
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


    @Override
    public String toString() {
        return "Student{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
