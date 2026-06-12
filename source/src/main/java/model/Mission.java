package model;

public class Mission {

    private int missionId;
    private String missionName;

    public Mission(
            int missionId,
            String missionName) {

        this.missionId = missionId;
        this.missionName = missionName;
    }

    public int getMissionId() {
        return missionId;
    }

    public String getMissionName() {
        return missionName;
    }
}