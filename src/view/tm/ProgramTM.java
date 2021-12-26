package view.tm;

public class ProgramTM {
    private String pId;
    private String pName;
    private String duration;
    private double fee;

    public ProgramTM() {
    }

    public ProgramTM(String pId, String pName, String duration, double fee) {
        this.setpId(pId);
        this.setpName(pName);
        this.setDuration(duration);
        this.setFee(fee);
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

    @Override
    public String toString() {
        return "Program{" +
                "pId='" + pId + '\'' +
                ", pName='" + pName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
