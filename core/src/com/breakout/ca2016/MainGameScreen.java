package com.breakout.ca2016;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.breakout.ca2016.Entities.Ball;
import com.breakout.ca2016.Entities.Board;
import com.breakout.ca2016.Entities.Brick;
import com.breakout.ca2016.Entities.Paddle;

import java.util.ArrayList;

/**
 * Created by t00191944 on 15/11/2016.
 */
public class MainGameScreen implements Screen {

    private Breakout game;
    private OrthographicCamera cam;
    private SpriteBatch batch;

    public Board board;

    public MainGameScreen(Breakout game) {
        this.game = game;
        init();
    }

    private void init() {
        this.board = new Board(game);
        this.batch = new SpriteBatch();
        this.cam = new OrthographicCamera(Board.BOARD_WIDTH, Board.BOARD_HEIGHT);
        this.cam.setToOrtho(false, Board.BOARD_WIDTH, Board.BOARD_HEIGHT);
        Gdx.app.debug("Test", "HAI");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        // clear the screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // start the updating...
        updateGame(delta);
        this.board.render(this.batch, this.cam);
        cam.update();

    }

    public void updateGame(float delta)
    {
        this.board.update(delta);
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

    }

    @Override
    public void dispose() {

    }
}
