package com.breakout.ca2016.Creators;

/**
 * Created by womble on 21.11.2016.
 */
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.breakout.ca2016.Entities.Board;
import com.breakout.ca2016.Entities.Wall;

import java.util.ArrayList;

public class WallCreator extends BrickCreator
{

    private final ArrayList<Wall> blocks;

    public ArrayList<Wall> getWalls() {return this.blocks; }

    public WallCreator(Board board)
    {
        super(0);
        this.blocks = this.generateWalls();
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        //telling the renderer (Shaperenderer) to use the the projection and view matrices of the camera.
        // http://www.opengl-tutorial.org/beginners-tutorials/tutorial-3-matrices/#The_Model__View_and_Projection_matrices
        this.renderer.setProjectionMatrix(cam.combined);
        // Set ShapeType to Filled Forms.
        this.renderer.begin(ShapeType.Filled);
        // Render Walls
        for (Wall wall : this.blocks)
        {
            this.renderer.setColor(wall.getColor());
            this.renderer.rect(wall.getPosition().x,
                    wall.getPosition().y,
                    wall.getBounds().width,
                    wall.getBounds().height);
        }
        this.renderer.end();
    }

    private ArrayList<Wall> generateWalls()
    {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Vector2(0,0), Board.BOARD_HEIGHT, .10f, "left"));
        walls.add(new Wall(new Vector2(0, Board.BOARD_HEIGHT - .10f), .25f, Board.BOARD_WIDTH, "top"));
        walls.add(new Wall(new Vector2(Board.BOARD_WIDTH - .10f,0), Board.BOARD_HEIGHT, .25f, "right"));
        return walls;
    }
}