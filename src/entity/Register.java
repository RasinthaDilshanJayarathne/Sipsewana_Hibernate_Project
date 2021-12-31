package entity;

import java.time.LocalDate;

public class Register {
    private String sId;
    private String pId;
    private LocalDate date;

    public Register() {
    }

    public Register(String sId, String pId, LocalDate date) {
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
}
