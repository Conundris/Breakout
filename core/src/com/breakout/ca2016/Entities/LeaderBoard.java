package com.breakout.ca2016.Entities;

/**
 * Created by t00191944 on 30/11/2016.
 */
public class LeaderBoard {
    private static LeaderBoard ourInstance = new LeaderBoard();

    public static LeaderBoard getInstance() {
        return ourInstance;
    }

    private LeaderBoard() {
    }

    public void addPlayer(Player player) {

    }
}
