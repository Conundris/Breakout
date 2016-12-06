package com.breakout.ca2016.Creators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.breakout.ca2016.Entities.Board;
import com.breakout.ca2016.Entities.Brick;

import java.util.ArrayList;

/**
 * Created by womble on 17.11.2016.
 */
public class BrickCreator {

    private final ArrayList<Brick> bricks;
    public ArrayList<Brick> getBlocks() {return this.bricks; }

    protected final ShapeRenderer renderer;

    public BrickCreator(int amountBlocks)
    {
        this.bricks = generateBlocks(amountBlocks);
        this.renderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        //telling the renderer (Shaperenderer) to use the the projection and view matrices of the camera.
        // http://www.opengl-tutorial.org/beginners-tutorials/tutorial-3-matrices/#The_Model__View_and_Projection_matrices
        this.renderer.setProjectionMatrix(cam.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        // Lambda Function to do a foreach through the ArrayList.
        this.bricks.stream().filter(block -> !block.isDestroyed()).forEach(block -> {
            this.renderer.setColor(block.getColor());
            this.renderer.rect(block.getPosition().x,
                    block.getPosition().y,
                    block.getBounds().width,
                    block.getBounds().height);
        });
        this.renderer.end();
    }

    private ArrayList<Brick> generateBlocks(int count)
    {
        float x = 0 + .5f;
        float y = Board.BOARD_HEIGHT - 1f;

        ArrayList<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < count; i++)
        {
            if (x + Brick.WIDTH > Board.BOARD_WIDTH - .25f)
            {
                x = 0 + .5f;
                y = y - .4f;
            }
            Vector2 v = new Vector2(x, y);
            Gdx.app.debug("BrickCreator", "Created Block at X:" + v.x + ", Y:" + v.y);
            bricks.add(new Brick(v));
            x += Brick.WIDTH + .25f;
        }
        return bricks;
    }
}
