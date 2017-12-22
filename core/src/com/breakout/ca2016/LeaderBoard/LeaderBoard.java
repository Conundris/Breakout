package com.breakout.ca2016.LeaderBoard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonWriter;
import com.breakout.ca2016.Entities.Player;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class LeaderBoard implements Net.HttpResponseListener {

    private Json json;
    public ArrayList<Player> players;

    private static LeaderBoard Instance = new LeaderBoard();

    public static LeaderBoard getInstance() {
        return Instance;
    }

    public LeaderBoard(){
        players = new ArrayList<>();
        json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
    }

    public void getLeaderBoard() {
        GETEntries();
    }

    public void CreateEntry(Player player) {
        POSTEntry(json.prettyPrint(player));
    }

    private void POSTEntry(String jsonText) {
        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("http://localhost:32768/api/Leaderboard");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setContent(jsonText);
        Gdx.net.sendHttpRequest(httpPost, this);
    }

    private void GETEntries() {
        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl("http://localhost:32768/api/Leaderboard");
        Gdx.net.sendHttpRequest(httpGet, this);
        
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        final int statusCode = httpResponse.getStatus().getStatusCode();

        if (statusCode == 201) {
            Gdx.app.log("NetAPI", "Entry created.");
            return;
        }

        if(statusCode == 200) { //For now we're assuming 200 is from the get request for getting the leaderboard.
            players = json.fromJson(ArrayList.class, httpResponse.getResultAsString());
            players.sort((Player v1, Player v2) -> v2.getScore()-v1.getScore());
            return;
        }
    }

    @Override
    public void failed(Throwable t) {
        Gdx.app.log("NetAPI" ,"Failed to perform the HTTP Request: " + t.getMessage());
        t.printStackTrace();
    }

    @Override
    public void cancelled() {
        Gdx.app.log("NetAPI", "HTTP request cancelled");
    }
}
