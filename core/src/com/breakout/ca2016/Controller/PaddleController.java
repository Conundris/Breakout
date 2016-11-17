package com.breakout.ca2016.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.breakout.ca2016.Entities.Board;

/**
 * Created by t00191944 on 17/11/2016.
 */
public class PaddleController implements InputProcessor {

    private Board board;
    private static float DAMP = .915f;
    private float MAX_VEL = 4f;
    private float ACCELERATION = 40f;

    public PaddleController(Board board){
        this.board = board;
    }

    @Override
    public boolean keyDown(int keycode) {

        // left key is pressed?
        if (keycode == Input.Keys.LEFT)
        {
            if (this.board.paddle.getPosition().x > 0)
            {
                this.board.paddle.getAcceleration().x -= ACCELERATION;
            }
            else
            {
                // no longer sure if this works any more
                // reset to zero if it's less than the board width
                this.board.paddle.getPosition().x = 0;
            }
        }

        if (keycode == Input.Keys.RIGHT)
        {
            if (this.board.paddle.getPosition().x < Board.BOARD_WIDTH - this.board.paddle.getBounds().width)
            {
                this.board.paddle.getAcceleration().x += ACCELERATION;
            }
            else
            {
                // no longer sure if this works any more
                this.board.paddle.getPosition().x = Board.BOARD_WIDTH - this.board.paddle.getBounds().width;
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
