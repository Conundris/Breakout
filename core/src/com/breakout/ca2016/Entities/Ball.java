package com.breakout.ca2016.Entities;

/**
 * Created by t00191944 on 16/11/2016.
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Ball {

    private Board board;

    public static float SIZE = .25f;
    private static float SPEED = 2f;
    private float max_vel = 5f;
    private float ACCELERATION = 40f;
    private float radius = .2f;

    private Vector2 velocity = new Vector2();
    public Vector2 getVelocity() { return this.velocity; }

    private Vector2 acceleration = new Vector2();
    public Vector2 getAcceleration() { return this.acceleration; }

    private Vector2 position;
    public Vector2 getPosition() { return this.position; }

    private Circle bounds;
    public Circle getBounds() { return this.bounds; }

    private float ballSpeed;
    public float getBallSpeed() { return this.ballSpeed; }

    private int direction_y = 1;
    private int direction_x = 1;

    private ShapeRenderer renderer;

    private SpriteBatch batch;
    private BitmapFont font;

    private boolean debug = false;

    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject()
        {
            return new Rectangle();
        }
    };

    public Ball(Board board)
    {
        this.board = board;
        this.ballSpeed = SPEED;

        this.position = getInitalPosition();
        this.bounds = new Circle(this.position.x, this.position.y, this.radius);
        //this.bounds = new Rectangle(this.position.x, this.position.y, SIZE, SIZE);
        this.renderer = new ShapeRenderer();


        // local debugging properties

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    // Get inital Position where Ball will be rendered
    private Vector2 getInitalPosition() {
        float x = 6;
        float y = 6;
        return new Vector2(x,y);
    }

    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        this.renderer.setProjectionMatrix(cam.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(Color.GREEN);
        /*this.renderer.circle(this.getPosition().x,
                this.getPosition().y,
                this.radius);*/
        this.renderer.circle(this.getBounds().x, this.getBounds().x, this.radius, 999);
        this.renderer.end();
    }
}