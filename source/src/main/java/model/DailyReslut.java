package model;

import java.time.LocalDate;

public class DailyReslut {

    private LocalDate date;
    private int achievement;

    public DailyReslut(LocalDate date, int achievement) {
        this.date = date;
        this.achievement = achievement;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAchievement() {
        return achievement;
    }

    public String getMark() {

        switch(achievement) {
        case 0:
            return "×";
        case 1:
            return "△";
        case 2:
            return "〇";
        case 3:
            return "◎";
        default:
            return "";
        }
    }
}