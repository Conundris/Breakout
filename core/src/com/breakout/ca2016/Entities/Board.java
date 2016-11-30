package com.breakout.ca2016.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breakout.ca2016.Breakout;
import com.breakout.ca2016.Creators.BrickCreator;
import com.breakout.ca2016.Creators.WallCreator;
import com.breakout.ca2016.ScreenType;

public class Board
{
    public final static float BOARD_WIDTH = 10f;
    public final static float BOARD_HEIGHT = 12f;

    // Entities
    public Breakout game;
    public Paddle paddle;
    public Ball ball;
    public BrickCreator bricks;
    public WallCreator walls;

    // other
    public int destroyedBricks = 0;
    public boolean blnFinished = false;

    //local properties
    private SpriteBatch batch;
    private BitmapFont font;

    // Player Board properties
    public int ballLives = 3;
    public int player_score = 0;

    public Board(Breakout game)
    {
        this.game = game;
        this.paddle = new Paddle(this);
        this.ball = new Ball(this);
        this.bricks = new BrickCreator(this, 20);
        this.walls = new WallCreator(this);

        // local properties
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        this.paddle.render(batch, cam);
        this.ball.render(batch, cam);
        this.bricks.render(batch, cam);
        this.walls.render(batch, cam);

        this.batch.begin();
        this.renderPlayerScore();
        this.batch.end();
    }

    public void update(float delta)
    {
        this.paddle.update(delta);
        this.ball.update(delta);
    }

    private void renderPlayerScore()
    {
        this.font.setColor(Color.GRAY);
        this.font.draw(this.batch, "Score: " + this.player_score, 350f, 20f);
    }
}