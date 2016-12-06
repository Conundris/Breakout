package com.breakout.ca2016.Entities;

/**
 * Created by t00191944 on 16/11/2016.
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.breakout.ca2016.Breakout;

import java.util.HashMap;
import java.util.Map;

public class Ball {
    private final Board board;

    public enum States { ACTIVE }
    public static Map<States, Boolean> states = new HashMap<>();
    static {states.put(States.ACTIVE, false);}

    public Boolean isActive() { return states.get(States.ACTIVE);}
    public void setActive(Boolean b) {states.put(States.ACTIVE, b);}

    public static final float SIZE = .15f;
    private static final float SPEED = 2f;
    private float max_vel = 3f;
    private final float ACCELERATION = 40f;

    private final Vector2 velocity = new Vector2();
    public Vector2 getVelocity() { return this.velocity; }

    private final Vector2 acceleration = new Vector2();
    public Vector2 getAcceleration() { return this.acceleration; }

    private final Vector2 position;
    public Vector2 getPosition() { return this.position; }

    private final Rectangle bounds;
    public Rectangle getBounds() { return this.bounds; }

    private final float ballSpeed;
    public float getBallSpeed() { return this.ballSpeed; }

    private int direction_y = 1;
    private int direction_x = 1;

    private final ShapeRenderer renderer;

    private final SpriteBatch batch;
    private final BitmapFont font;

    private Rectangle rect = new Rectangle();

    public Ball(Board board)
    {
        this.board = board;
        this.ballSpeed = SPEED;
        this.setActive(false);
        this.position = this.board.paddle.getBallOriginPosition();
        this.bounds = new Rectangle(this.position.x, this.position.y, SIZE, SIZE);
        this.renderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        this.renderer.setProjectionMatrix(cam.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);

        this.renderer.setColor(Color.GREEN);
        this.renderer.rect(this.position.x, this.position.y, this.bounds.height, this.bounds.width);

        this.renderer.end();
    }

    public void update(float delta)
    {
        // if ball is active, move it around
        if(this.isActive())
        {
            // check for collisions!
            this.acceleration.scl(delta);
            this.collides(delta);

            this.velocity.add(this.acceleration.x, this.acceleration.y);
            this.capVelocity();
            // has the ball gone off the screen?
            if (this.position.y < 0 || this.position.y > Board.BOARD_HEIGHT)
            {
                // remove a ball from the player
                this.board.ballLives -= 1;
                if (this.board.ballLives < 0)
                {
                    //set the end screen
                    this.board.blnFinished = true;
                }
                else
                {
                    this.board.blnFinished = true;
                }
            }
        }
        else // it's not active, it should be "attached" to the paddle
        {
            this.position.x = this.board.paddle.getBallOriginPosition().x;
            this.position.y = this.board.paddle.getBallOriginPosition().y;
            this.bounds.setX(this.position.x);
            this.bounds.setY(this.position.y);
        }
        if (Breakout.DEBUG)
        {
            this.debugBall();
        }

    }

    private void collides(float delta)
    {
        this.velocity.scl(delta);

        rect.set(this.bounds);

        rect.x += this.velocity.x;

        // bounce off walls
        for (Wall wall : this.board.walls.getWalls())
        {
            if (rect.overlaps(wall.getBounds()))
            {
                // walls have names, bricks don't
                if (wall.getName().equals("left") || wall.getName().equals("right"))
                {
                    this.direction_x *= -1;
                    this.velocity.x = -this.velocity.x;
                    this.acceleration.x = this.direction_x * 0.5f;
                }
                if (wall.getName().equals("top"))
                {
                    this.direction_y *= -1;
                    this.velocity.y = -this.velocity.y;
                    this.acceleration.y = this.direction_y * 0.5f;
                }
                break;
            }
            else
            {
                this.acceleration.x = this.direction_x * 1f;
                this.acceleration.y = this.direction_y * 1f;
            }
        }

        // detected brick collisions
        for (Brick brick : this.board.bricks.getBlocks())
        {
            if (rect.overlaps(brick.getBounds()) && !brick.isDestroyed())
            {
                brick.setDestroyed(true);
                this.max_vel += .5f;
                this.board.player_score += 100 * this.max_vel;

                // Possible Sound play

                // Another brick has been destroyed
                this.board.destroyedBricks = this.board.destroyedBricks + 1;
                this.direction_x *= 1;
                this.acceleration.x = this.direction_x * this.velocity.x;

                // Change Direction after hit
                this.direction_y *= -1;
                this.velocity.y = -this.velocity.y;
                this.acceleration.y += this.direction_y;
            }
            else
            {
                this.acceleration.x = this.direction_x * this.ballSpeed;
                this.acceleration.y = this.direction_y * this.ballSpeed;
            }
        }

        // Hit Paddle
        if (rect.overlaps(this.board.paddle.getBounds()))
        {
            this.direction_y *= -1f;
            this.velocity.y = -this.velocity.y;
            this.acceleration.y = this.direction_y * 1f;

            if (this.board.destroyedBricks == this.board.bricks.getBlocks().size())
            {
                this.board.blnFinished = true;
            }
        }

        // Making sure the bounds are updated
        rect.x = this.position.x;
        rect.y = this.position.y;
        this.position.add(this.velocity);
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);

        this.velocity.scl(1 / delta);
    }

    // Makes sure that the max velocity gets forced
    private void capVelocity()
    {
        if(this.velocity.y > this.max_vel)
        {
            this.velocity.y = this.max_vel;
        }
        if(this.velocity.y < -this.max_vel)
        {
            this.velocity.y = -this.max_vel;
        }
        if(this.velocity.x > this.max_vel)
        {
            this.velocity.x = this.max_vel;
        }
        if(this.velocity.x < -this.max_vel)
        {
            this.velocity.x = -this.max_vel;
        }
    }

    // Debug information for ball position, velocity, acceleration
    private void debugBall() {
        this.batch.begin();
        this.font.setColor(Color.WHITE);
        this.font.draw(this.batch, "Ball: ", 20f, 120f);
        this.font.draw(this.batch, "velocity: " + this.velocity, 20f, 100f);
        this.font.draw(this.batch, "position: " + this.position, 20f, 80f);
        this.font.draw(this.batch, "acceleration: " + this.acceleration, 20f, 60f);
        this.batch.end();
    }
}