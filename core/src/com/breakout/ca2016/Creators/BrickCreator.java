package com.breakout.ca2016.Creators;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.breakout.ca2016.Entities.Board;
import com.breakout.ca2016.Entities.Brick;

/**
 * Created by womble on 17.11.2016.
 */
public class BrickCreator {
    private final Board board;

    private final Brick[] bricks;
    public Brick[] getBlocks() {return this.bricks; }

    protected final ShapeRenderer renderer;

    public BrickCreator(Board board, int amountBlocks)
    {
        this.board = board;
        this.bricks = generateBlocks(amountBlocks);
        this.renderer = new ShapeRenderer();
    }

    public BrickCreator(Board board, Brick[] bricks)
    {
        this.board = board;
        this.bricks = bricks;
        this.renderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        this.renderer.setProjectionMatrix(cam.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Brick block : this.bricks)
        {
            if (!block.isDestroyed())
            {
                this.renderer.setColor(block.getColor());
                this.renderer.rect(block.getPosition().x,
                        block.getPosition().y,
                        block.getBounds().width,
                        block.getBounds().height);
            }
        }
        this.renderer.end();
    }

    private Brick[] generateBlocks(int count)
    {
        float x = 0 + .5f;
        float y = Board.BOARD_HEIGHT - 1f;
        Brick[] temp = new Brick[count];
        for (int i = 0; i < count; i++)
        {
            if (x + Brick.WIDTH > Board.BOARD_WIDTH - .25f)
            {
                x = 0 + .5f;
                y = y - .4f;
            }
            Vector2 v = new Vector2(x, y);
            System.out.println(v);
            temp[i] = new Brick(v);
            x += Brick.WIDTH + .25f;
        }
        return temp;
    }
}
