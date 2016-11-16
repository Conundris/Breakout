package com.breakout.ca2016;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.breakout.ca2016.Actor.Ball;
import com.breakout.ca2016.Actor.Brick;
import com.breakout.ca2016.Actor.Paddle;

import java.util.ArrayList;

/**
 * Created by t00191944 on 15/11/2016.
 */
public class MainGameScreen implements Screen {

    private Breakout game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage breakoutStage;
    private Ball ball;
    private Paddle paddle;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private boolean debug = false;
    private boolean[] keys = new boolean[4];
    //private BreakoutContactListener contactListener;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private Vector2 worldToScreen;

    public MainGameScreen(Breakout game) {
        this.game = game;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        breakoutStage = new Stage();

        world = new World(new Vector2(0f, 0f), true);
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1137f, 0.16f, 0.145f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
