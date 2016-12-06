package com.breakout.ca2016.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * <h1>Paddle<h1/>
 * The Paddle Class defines a paddle in the @{@Link Board}.
 *
 * @author  Jason Brockett
 * @version 1.0
 * @since   2016-12-06
 */
public class Paddle
{
    /**
     * Board Reference @{@link Board}
     */
    private final Board board;

    /**
     * Width of Paddle
     */
    private final float WIDTH = 1.5f;
    /**
     * Height of Paddle
     */
    private final float HEIGHT = .25f;

    /**
     * Renderer used to display paddle @{@link ShapeRenderer}
     */
    private final ShapeRenderer renderer;

    /**
     * Vector for the position of the paddle
     */
    private Vector2 position;

    /**
     * This method returns the postion vector of the paddle.
     * @return Vector2 Position vector of Paddle
     */
    public Vector2 getPosition() {return this.position;}

    /**
     * Vector for the velocity of the paddle
     */
    private Vector2 velocity = new Vector2();
    /**
     * This method returns the velocity vector of the paddle.
     * @return Vector2 Velocity vector of Paddle
     */
    public Vector2 getVelocity() { return this.velocity;}

    /**
     * Vector for the acceleration of the paddle
     */
    private Vector2 acceleration = new Vector2();
    /**
     * This method returns the acceleration vector of the paddle.
     * @return Vector2 Acceleration vector of Paddle
     */
    public Vector2 getAcceleration() { return this.acceleration; }

    /**
     *  Rectangle displaying bounds of paddle
     */
    private Rectangle bounds;
    /**
     * This method returns the bounds of the paddle.
     * @return Rectangle bounds of Paddle
     */
    public Rectangle getBounds() { return this.bounds; }

    /**
     * Rectangle Object that is used to determine if the paddle has collided with anything.
     */
    private Rectangle rect;

    /**
     * Constructor
     *
     * @param board Reference of Board, paddle will be displayed in.
     */
    public Paddle(Board board)
    {
        this.board = board;
        this.position = new Vector2(Board.BOARD_WIDTH / 2 - WIDTH / 2, 0.5f);
        this.bounds = new Rectangle(this.position.x, this.position.y, WIDTH, HEIGHT);
        this.renderer = new ShapeRenderer();
        this.rect = new Rectangle();
    }

    /**
     * This method renders the paddle on the screen.
     *
     * @param batch @{@link SpriteBatch} batch used to render the paddle.
     * @param cam @{@Link OrthographicCamera} displays the content on the screen.
     */
    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        this.renderer.setProjectionMatrix(cam.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(Color.BLUE);
        this.renderer.rect(this.getPosition().x,
                this.getPosition().y,
                this.getBounds().width,
                this.getBounds().height);
        this.renderer.end();
    }

    /**
     * This method handles the collision handling and velocity of the paddle
     *
     * @param delta time since last frame update
     */
    public void update(float delta)
    {
        // paddle can't move up. set y to 0
        this.board.paddle.getAcceleration().y = 0f;

        // transform acceleration into "frame-time"
        // Using Vector2 Method scale "scl".
        this.board.paddle.getAcceleration().scl(delta);

        // add the current paddle acceleration to the velocity
        this.board.paddle.getVelocity().add(this.board.paddle.getAcceleration().x, this.board.paddle.getAcceleration().y);

        this.collides(delta);
    }

    /**
     * This method handles the possible collisions of the paddle
     *
     * @param delta time since last frame update
     */
    public void collides(float delta)
    {
        // transform velocity to "frame-time"
        this.board.paddle.getVelocity().scl(delta);
        // set the fake rectangle to the same bounds as the paddle
        rect.set(this.board.paddle.getBounds());
        // now increase the fake rectangle x position to the paddles x velocity
        rect.x += this.board.paddle.getVelocity().x;

        ArrayList<Wall> walls = this.board.walls.getWalls();
        for (Wall wall : walls) {
            // find a Wall that might overlap the fake rectangle
            if (rect.overlaps(wall.getBounds()))
            {
                // set the paddle velocity to 0
                this.board.paddle.getVelocity().x = 0f;
                break;
            }
        }

        // Scale Velocity to Frametime (Deltatime)
        rect.x = this.board.paddle.getPosition().x;
        // add the paddle velocity (which may be ZERO (0) at this point) to the paddles position
        this.board.paddle.getPosition().add(this.board.paddle.getVelocity());
        // add the paddle velocity (which may be ZERO (0) at this point), so that the paddle moves in the screen.
        this.board.paddle.getBounds().setX(this.board.paddle.getPosition().x);
        this.board.paddle.getBounds().setY(this.board.paddle.getPosition().y);

        // Scale velocity back to base units
        this.board.paddle.getVelocity().scl(1 / delta);
    }

    /**
     *  This method gets the starting point for the ball, relative to the paddle position
     *  @return Vector2 Returns Vector2 of ball origin position.
     */
    public Vector2 getBallOriginPosition()
    {
        float x = this.position.x + (this.bounds.width / 2) - this.board.ball.SIZE / 2;
        float y = this.bounds.y + this.bounds.height;
        return new Vector2(x,y);
    }
}
