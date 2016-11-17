package com.breakout.ca2016.Entities;

/**
 * Created by womble on 16.11.2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breakout.ca2016.Breakout;
import com.breakout.ca2016.Controller.PaddleController;
import com.breakout.ca2016.Screens.BrickRenderer;

public class Board
{
    public final static float BOARD_WIDTH = 10f;
    public final static float BOARD_HEIGHT = 12f;

    // Entities
    public Breakout game;
    public Paddle paddle;
    public Ball ball;
    public BrickRenderer bricks;

    // other
    public int destroyedBricks = 0;

    //local properties
    private SpriteBatch batch;
    private BitmapFont font;

    // Player Board properties
    public int balls = 3;
    public int player_score = 0;

    public Board(Breakout game)
    {
        this.game = game;
        this.paddle = new Paddle(this);
        this.ball = new Ball(this);
        this.bricks = new BrickRenderer(this, 20);

        // local properties
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        Gdx.input.setInputProcessor(new PaddleController(this));
    }

    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        this.paddle.render(batch, cam);
        this.ball.render(batch, cam);
        this.bricks.render(batch, cam);

        this.batch.begin();
        this.renderPlayerScore();
        this.batch.end();
    }

    public void update(float delta)
    {
        this.paddle.update(delta);
        //this.ball.upd
    }

    private void renderPlayerScore()
    {
        this.font.setColor(Color.GRAY);
        this.font.draw(this.batch, "Score: " + this.player_score, 350f, 20f);
    }
}