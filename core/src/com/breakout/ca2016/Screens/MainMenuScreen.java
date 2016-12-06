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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.breakout.ca2016.Breakout;
import com.breakout.ca2016.ScreenType;

public class MainMenuScreen implements Screen
{
    private Breakout game;
    private BitmapFont font;
    private Skin skin;
    private Stage stage;
    private Table table;

    public MainMenuScreen(Breakout game)
    {
        this.game = game;
        this.font = new BitmapFont();

        table = new Table();
        table.setFillParent(true);

        stage = new Stage();

        CreateSkin();
    }

    // Create Formitems and add them to the stage
    private void GenerateUI(Stage stage, Table table) {

        // Create UI elements
        TextButton startGame = new TextButton("New Game", skin);
        TextButton leaderboardButton = new TextButton("Leaderboards", skin);
        TextButton quitButton = new TextButton("Quit", skin);

        // Add UI elements to table
        table.add(startGame).spaceBottom(10).row();
        table.add(leaderboardButton).spaceBottom(10).row();
        table.add(quitButton).spaceBottom(10).row();

        // Add UI elements directly to Stage
        stage.addActor(table);

        //Add Listeners
        startGame.addListener(new ClickListener() {
              @Override
              public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                  return true;
              }

              @Override
              public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                  game.setScreen(game.getScreenType(ScreenType.MainGame));
              }
        });
        leaderboardButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getScreenType(ScreenType.LeaderBoard));
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void show()
    {
        // Point to the stage for processing Input.
        Gdx.input.setInputProcessor(stage);

        GenerateUI(stage, table);
    }

    @Override
    public void hide()
    {
        dispose();
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
        // Clear Table and Stage when not needed anymore
        table.clear();
        stage.clear();
    }

    /*****************************************************
     *    Title: Java Code Examples for com.badlogic.gdx.scenes.scene2d.ui.Skin.getFont() - Example 3
     *    Author: Programcreek
     *    Site owner/sponsor: http://www.programcreek.com/
     *    Date: 2016
     *    Code version: edited Jan 10 '13 at 17:42
     *    Availability: http://www.programcreek.com/java-api-examples/index.php?class=com.badlogic.gdx.scenes.scene2d.ui.Skin&method=getFont (Accessed 23 November 2016)
     *    Modified: Only taken needed code, changed color.
     *****************************************************/
    private void CreateSkin(){
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
    }

}
