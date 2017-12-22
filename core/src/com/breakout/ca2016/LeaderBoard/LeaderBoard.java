package com.breakout.ca2016.LeaderBoard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;

public class LeaderBoard implements Net.HttpResponseListener {

    public void test() {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("http://localhost:32768/api/Leaderboard");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setContent("{\"name\":\"Test\",\"score\":200}");
        Gdx.net.sendHttpRequest(httpPost, this);
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        final int statusCode = httpResponse.getStatus().getStatusCode();

        if (statusCode != 200) {
            Gdx.app.log("NetAPI", "An error ocurred since statusCode is not OK");
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
