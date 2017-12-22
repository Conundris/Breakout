package com.breakout.ca2016.Entities;

/**
 * Created by t00191944 on 29/11/2016.
 */
public class Player {
    private int id;
    private String Name;
    private int Score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return Name;
    }

    public void setUserName(String Name) { this.Name = Name; }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        this.Score = score;
    }
}
