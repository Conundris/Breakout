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
import com.breakout.ca2016.Entities.LeaderBoard;
import com.breakout.ca2016.ScreenType;

/**
 * Created by t00191944 on 29/11/2016.
 */
public class PostGameScreen implements Screen {

    private Breakout game;
    private Skin skin;
    private BitmapFont font;
    private Stage stage;
    private Table table;

    public PostGameScreen(Breakout game) {
        this.game = game;
        this.font = new BitmapFont();

        table = new Table();
        table.setFillParent(true);

        stage = new Stage();

        CreateSkin();
    }

    private void GenerateUI(Stage stage, Table table) {

        Label lblPlayerName = new Label("Playername: ", skin);
        TextField txtPlayerName = new TextField("", skin);

        TextButton btnContinueButton = new TextButton("Continue", skin);


        table.add(lblPlayerName);
        table.add(txtPlayerName).width(100).height(30).row();

        table.add(btnContinueButton).spaceTop(30).padLeft(50).row();

        stage.addActor(table);

        btnContinueButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(txtPlayerName.getText() != "") {
                    game.player.setUserName(txtPlayerName.getText());
                    LeaderBoard.getInstance().addPlayer(game.player);
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

    public void CreateSkin(){
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() /4, Gdx.graphics.getHeight() /11, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = skin.getFont("default");
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.background = skin.newDrawable("background", Color.GRAY);
        skin.add("default", textFieldStyle);
    }
}
