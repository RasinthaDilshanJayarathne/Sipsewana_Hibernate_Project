package view.tm;

public class ProgramTM implements Comparable<ProgramTM>{
    private String programId;
    private String programName;
    private String duration;
    private double fee;

    public ProgramTM() {
    }

    public ProgramTM(String programId, String programName, String duration, double fee) {
        this.setProgramIg(programId);
        this.setProgramName(programName);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramIg(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
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
        return "ProgramTM{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
    @Override
    public int compareTo(ProgramTM o) {
        return programId.compareTo(o.getProgramId());
    }
}
