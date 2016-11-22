package com.breakout.ca2016.Screens;

/**
 * Created by womble on 21.11.2016.
 */
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.breakout.ca2016.Entities.Board;
import com.breakout.ca2016.Entities.Wall;

public class WallRenderer extends BrickRenderer
{

    private final Wall[] blocks;
    @Override
    public Wall[] getBlocks() {return this.blocks; }

    public WallRenderer(Board board)
    {
        super(board, 0);
        this.blocks = this.generateWalls();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera cam)
    {
        this.renderer.setProjectionMatrix(cam.combined);
        this.renderer.begin(ShapeType.Filled);
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

    private Wall[] generateWalls()
    {
        Wall[] walls = new Wall[3];
        walls[0] = new Wall(new Vector2(0,0), Board.BOARD_HEIGHT, .25f, "left"); //left wall
        walls[1] = new Wall(new Vector2(0, Board.BOARD_HEIGHT - .25f), .25f, Board.BOARD_WIDTH, "top"); //top wall
        walls[2] = new Wall(new Vector2(Board.BOARD_WIDTH - .25f,0), Board.BOARD_HEIGHT, .25f, "right"); //right wall
        return walls;
    }
}