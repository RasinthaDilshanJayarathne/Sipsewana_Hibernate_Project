package entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Program {
    @Id
    private String pId;
    private String pName;
    private String duration;
    private double fee;

    @OneToMany(mappedBy = "program",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Register> courseDetails=new ArrayList<>();

    public Program() {
    }

    public Program(String pId, String pName, String duration, double fee) {
        this.setpId(pId);
        this.setpName(pName);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public Program(String pId, String pName, String duration, double fee, List<Register> courseDetails) {
        this.setpId(pId);
        this.setpName(pName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setCourseDetails(courseDetails);
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<Register> getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(List<Register> courseDetails) {
        this.courseDetails = courseDetails;
    }

    @Override
    public String toString() {
        return "Program{" +
                "pId='" + pId + '\'' +
                ", pName='" + pName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", courseDetails=" + courseDetails +
                '}';
    }
}
