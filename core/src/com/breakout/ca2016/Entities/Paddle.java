package com.breakout.ca2016.Entities;

/**
 * Created by t00191944 on 16/11/2016.
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle
{
    private final Board board;

    private final float WIDTH = 1.5f;
    private final float HEIGHT = .25f;
    public final float SPEED = .01f;

    private final ShapeRenderer renderer;

    private Vector2 position;
    public Vector2 getPosition() {return this.position;}

    private Vector2 velocity = new Vector2();
    public Vector2 getVelocity() { return this.velocity;}

    private Vector2 acceleration = new Vector2();
    public Vector2 getAcceleration() { return this.acceleration; }

    private Rectangle bounds;
    public Rectangle getBounds() { return this.bounds; }

    public Color color = Color.BLUE;

    public Paddle(Board board)
    {
        this.board = board;
        this.position = new Vector2(Board.BOARD_WIDTH / 2 - WIDTH / 2, 0.5f);
        this.bounds = new Rectangle(this.position.x, this.position.y, WIDTH, HEIGHT);
        this.renderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        this.renderer.setProjectionMatrix(cam.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(this.color);
        this.renderer.rect(this.getPosition().x,
                this.getPosition().y,
                this.getBounds().width,
                this.getBounds().height);
        this.renderer.end();
    }

    public void update(float delta)
    {
    }
}
