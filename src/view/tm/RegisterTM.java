package view.tm;

import java.time.LocalDate;

public class RegisterTM {
    private String sId;
    private String pId;
    private LocalDate date;

    public RegisterTM() {
    }

    public RegisterTM(String sId, String pId, LocalDate date) {
        this.setsId(sId);
        this.setpId(pId);
        this.setDate(date);
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RegisterDetailTM{" +
                "sId='" + sId + '\'' +
                ", pId='" + pId + '\'' +
                ", date=" + date +
                '}';
    }
}
