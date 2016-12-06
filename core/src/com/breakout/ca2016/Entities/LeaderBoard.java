package com.breakout.ca2016.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class LeaderBoard {

    private Json json;
    // Handles the local File for Leaderboard, description of Local:
    // https://github.com/libgdx/libgdx/wiki/File-handling
    private FileHandle leaderBoardFile = Gdx.files.local("leaderboard.json");

    public ArrayList<Player> players;

    private static LeaderBoard ourInstance = new LeaderBoard();

    public static LeaderBoard getInstance() {
        return ourInstance;
    }

    private LeaderBoard() {
        players = new ArrayList<>();
        json = new Json();
    }

    public void loadLeaderBoard() {
        // Check if File exists locally
        if(leaderBoardFile.exists()) {
            ArrayList<Player> jsonValueList = json.fromJson(ArrayList.class, leaderBoardFile);

            if(jsonValueList != null) {
                for (Player value : jsonValueList) {
                    players.add(value);
                }
            }
        } else {
            leaderBoardFile.writeString("", false);
        }
    }

    private void saveLeaderBoard() {
        String jsonString = json.prettyPrint(players);

        leaderBoardFile.writeString(jsonString, false);

        Gdx.app.debug("JsonTest", json.toJson(players));
    }

    public void addPlayer(Player player) {
        players.add(player);
        saveLeaderBoard();
    }

    public ArrayList<Player> getLeaderBoard() {
        // Sort List of Players descending on Score
        players.sort((Player v1, Player v2) -> v2.getScore()-v1.getScore());
        return players;
    }
}
