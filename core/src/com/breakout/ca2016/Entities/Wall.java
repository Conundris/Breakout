package com.breakout.ca2016.Entities;

/**
 * Created by womble on 21.11.2016.
 */
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Wall extends Brick
{
    private String name;
    public String getName() { return this.name; }

    public Wall(Vector2 position, float height, float width, String name)
    {
        super(position);
        this.getBounds().setHeight(height);
        this.getBounds().setWidth(width);
        this.name = name;
        this.color = Color.ORANGE;
    }
}
