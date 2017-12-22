package com.breakout.ca2016.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.breakout.ca2016.Breakout;
import com.breakout.ca2016.LeaderBoard.LeaderBoard;
import com.breakout.ca2016.ScreenType;
import com.breakout.ca2016.Singleton.LeaderBoardLegacy;
import com.breakout.ca2016.Utils.SkinUtils;

/**
 * Created by t00191944 on 29/11/2016.
 */
public class PostGameScreen implements Screen {

    private Breakout game;
    private Skin skin;
    private Stage stage;
    private Table table;

    public PostGameScreen(Breakout game) {
        this.game = game;

        table = new Table();
        table.setFillParent(true);

        stage = new Stage();

        skin = SkinUtils.CreateSkin();
    }

    // Create Formitems and add them to the stage
    private void GenerateUI(Stage stage, Table table) {

        // Create UI elements
        Label lblPlayerName = new Label("Playername: ", skin);
        TextField txtPlayerName = new TextField("", skin);
        TextButton btnContinueButton = new TextButton("Continue", skin);

        // Add UI elements to table
        table.add(lblPlayerName);
        table.add(txtPlayerName).width(100).height(30).row();
        table.add(btnContinueButton).spaceTop(30).padLeft(50).row();

        // Add UI elements directly to Stage
        stage.addActor(table);

        //Add Listener to Continue button
        btnContinueButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(txtPlayerName.getText() != "" && !txtPlayerName.getText().trim().isEmpty()) {
                    game.player.setUserName(txtPlayerName.getText());
                    LeaderBoard.getInstance().CreateEntry(game.player);
                    LeaderBoardLegacy.getInstance().addPlayer(game.player);
                    game.player = null;

                    game.setScreen(game.getScreenType(ScreenType.MainMenu));
                } else {
                    game.player = null;

                    game.setScreen(game.getScreenType(ScreenType.MainMenu));
                }
            }
        });
    }

    @Override
    public void show() {
        GenerateUI(stage, table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Render content in stage on to the screen.
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
