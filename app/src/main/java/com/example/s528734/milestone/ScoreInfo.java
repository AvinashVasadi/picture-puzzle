package com.example.s528734.milestone;

import java.util.Date;

/**
 * Created by S528770 on 11/17/2017.
 */

public class ScoreInfo {



    public int easy;
    public int hard;
    public long easytime;
    public long hardtime;
    public Date created;
    public Date updated;

    public void setEasy(int easy) {
        this.easy = easy;
    }

    public void setHard(int hard) {
        this.hard = hard;
    }

    public void setEasytime(long easytime) {
        this.easytime = easytime;
    }

    public void setHardtime(long hardtime) {
        this.hardtime = hardtime;
    }

    @Override
    public String toString() {
        return "ScoreInfo{" +
                "easy='" + easy + '\'' +
                ", hard='" + hard + '\'' +
                ", easytime" + easytime +
                ", hardtime" + hardtime +

                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}

