package com.breakout.ca2016.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by t00191944 on 16/11/2016.
 */
public class Brick {
    public static final float WIDTH = .4f;
    public static final float HEIGHT = .3f;

    private boolean destroyed = false;
    public boolean isDestroyed() {return this.destroyed; }
    public void setDestroyed(boolean b) { this.destroyed = b; }

    protected Color color;
    public Color getColor() { return this.color; }

    private final Rectangle bounds;
    public Rectangle getBounds() { return this.bounds; }

    private final Vector2 position;
    public Vector2 getPosition() {return this.position; }

    public Brick(Vector2 position)
    {
        this.color = Color.RED;
        this.position = position;
        this.bounds = new Rectangle(this.position.x, this.position.y, WIDTH, HEIGHT);
    }
}
