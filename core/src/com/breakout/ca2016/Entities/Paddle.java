package com.breakout.ca2016.Entities;

/**
 * Created by t00191944 on 16/11/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.breakout.ca2016.Breakout;

public class Paddle
{
    private final Board board;

    private final float WIDTH = 1.5f;
    private final float HEIGHT = .25f;

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

    private final Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject()
        {
            return new Rectangle();
        }
    };

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
        if(Breakout.DEBUG) {
            Gdx.app.debug("DEBUG", "X Position: " + this.board.paddle.getPosition().x);
            Gdx.app.debug("DEBUG", "Y Position: " + this.board.paddle.getPosition().y);
        }
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
     *
     * @param delta
     * @return
     */
    public void collides(float delta)
    {
        // transform velocity to "frame-time"
        this.board.paddle.getVelocity().scl(delta);
        Rectangle r = rectPool.obtain();
        // set the fake rectangle to the same bounds as the paddle
        r.set(this.board.paddle.getBounds());
        // now increase the fake rectangle x position to the paddles x velocity
        r.x += this.board.paddle.getVelocity().x;

        Brick[] blocks = this.board.walls.getBlocks();
        for (Brick block : blocks)
        {
            // find a block that might overlap the fake rectangle
            if (r.overlaps(block.getBounds()))
            {
                // set the paddle velocity to 0
                this.board.paddle.getVelocity().x = 0f;
                break;
            }
        }

        // Scale Velocity to Frametime (Deltatime)
        r.x = this.board.paddle.getPosition().x;
        // add the paddle velocity (which may be ZERO (0) at this point) to the paddles position
        this.board.paddle.getPosition().add(this.board.paddle.getVelocity());
        // add the paddle velocity (which may be ZERO (0) at this point), so that the paddle moves in the screen.
        this.board.paddle.getBounds().setX(this.board.paddle.getPosition().x);
        this.board.paddle.getBounds().setY(this.board.paddle.getPosition().y);

        // Scale velocity back to base units
        this.board.paddle.getVelocity().scl(1 / delta);
    }

    public Vector2 getBallPosition()
    {
        float x = this.position.x + (this.bounds.width / 2) - Ball.SIZE / 2;
        float y = this.bounds.y + this.bounds.height;
        return new Vector2(x,y);
    }
}
