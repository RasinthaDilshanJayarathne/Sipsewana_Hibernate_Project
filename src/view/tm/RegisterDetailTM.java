package view.tm;

import java.time.LocalDate;

public class RegisterDetailTM implements Comparable<RegisterDetailTM>{
    private String RegId;
    private String SId;
    private String SName;
    private String CId;
    private String CName;
    private LocalDate Date;

    public RegisterDetailTM() {
    }

    public RegisterDetailTM(String regId, String SId, String SName, String CId, String CName, LocalDate date) {
        setRegId(regId);
        this.setSId(SId);
        this.setSName(SName);
        this.setCId(CId);
        this.setCName(CName);
        setDate(date);
    }

    public String getRegId() {
        return RegId;
    }

    public void setRegId(String regId) {
        RegId = regId;
    }

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "RegisterDetailTM{" +
                "RegId='" + RegId + '\'' +
                ", SId='" + SId + '\'' +
                ", SName='" + SName + '\'' +
                ", CId='" + CId + '\'' +
                ", CName='" + CName + '\'' +
                ", Date=" + Date +
                '}';
    }

    @Override
    public int compareTo(RegisterDetailTM o) {
        return RegId.compareTo(o.getRegId());
    }
}
