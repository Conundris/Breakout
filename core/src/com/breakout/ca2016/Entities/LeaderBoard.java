package com.breakout.ca2016.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

/**
 * Created by t00191944 on 30/11/2016.
 */
public class LeaderBoard {
    private Json json;
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

    public void saveLeaderBoard() {
        String jsonString = json.prettyPrint(players);

        leaderBoardFile.writeString(jsonString, false);

        Gdx.app.debug("JsonTest", json.toJson(players));
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getLeaderBoard() {
        // Sort List of Players ascending on Score
        players.sort((Player v1, Player v2) -> v1.getScore()-v2.getScore());
        return players;
    }
}
