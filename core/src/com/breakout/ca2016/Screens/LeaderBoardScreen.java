package com.breakout.ca2016.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.breakout.ca2016.Breakout;
import com.breakout.ca2016.Entities.Player;
import com.breakout.ca2016.LeaderBoard.LeaderBoard;
import com.breakout.ca2016.ScreenType;
import com.breakout.ca2016.Singleton.LeaderBoardLegacy;
import com.breakout.ca2016.Utils.SkinUtils;

import java.util.ArrayList;

/**
 * Created by t00191944 on 23/11/2016.
 */
public class LeaderBoardScreen implements Screen {

    private Breakout game;
    private Skin skin;
    private Stage stage;
    private Table table;

    public LeaderBoardScreen(Breakout game){
        this.game = game;

        table = new Table();
        table.setFillParent(true);

        stage = new Stage();

        skin = SkinUtils.CreateSkin();
    }

    @Override
    public void show() {
        GenerateUI(stage, table);
        Gdx.input.setInputProcessor(stage);
    }

    private void GenerateUI(Stage stage, Table table) {

        // Create UI elements
        Label lblTitle = new Label("Leaderboard", skin);
        TextButton btnContinueButton = new TextButton("Continue", skin);
        TextButton btnTest = new TextButton("TestCall API", skin);

        // Add UI elements to table
        table.add(lblTitle).spaceBottom(25).row();

        //Get List of Players
        ArrayList<Player> players = LeaderBoardLegacy.getInstance().getLeaderBoard();

        // Create UI elements and add them to the table for correct displaying
        for (Player player: players) {

            Label name = new Label(player.getUserName(), skin);
            Label score = new Label(String.valueOf(player.getScore()), skin);

            table.add(name).spaceRight(30);
            table.add(score).spaceBottom(10).row();
        }

        // Add Continue Button at them
        table.add(btnContinueButton).spaceTop(30);
        table.add(btnTest).spaceTop(30);

        // Add UI elements directly to Stage
        stage.addActor(table);

        //Listeners for Buttons
        btnTest.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                new LeaderBoard().test();
            }
        });

        btnContinueButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getScreenType(ScreenType.MainMenu));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        table.clear();
        stage.clear();
    }
}
