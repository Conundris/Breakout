package com.breakout.ca2016.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.breakout.ca2016.Breakout;
import com.breakout.ca2016.Entities.Board;
import com.breakout.ca2016.ScreenType;

/**
 * Created by t00191944 on 17/11/2016.
 */
public class MainGameController implements InputProcessor {

    private Board board;
    private float ACCELERATION = 160f;

    public MainGameController(Board board){
        this.board = board;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.LEFT)
        {
            // Check position of Paddle, so that it doesn't go out of screen
            if (this.board.paddle.getPosition().x > 0)
            {
                this.board.paddle.getAcceleration().x -= ACCELERATION;
            }
            else
            {
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
                this.board.paddle.getPosition().x = Board.BOARD_WIDTH - this.board.paddle.getBounds().width;
            }
        }

        if( keycode == Input.Keys.ESCAPE) {
            //TODO: make popup for confirmation if you want to go back to MainMenu
            board.game.setScreen(board.game.getScreenType(ScreenType.MainMenu));
        }

        if( keycode == Input.Keys.F1) {
            if(!Breakout.DEBUG) {
                Breakout.DEBUG = true;
                return true;
            }
            if(Breakout.DEBUG) {
                Breakout.DEBUG = false;
                return true;
            }
        }

        if (keycode == Input.Keys.SPACE) {
            if (!this.board.ball.isActive())
            {
                this.board.ball.setActive(true);
                this.board.ball.getVelocity().y = 0f;
            }
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.LEFT)
        {
            this.board.paddle.getVelocity().x = 0;
        }

        if (keycode == Input.Keys.RIGHT)
        {
            this.board.paddle.getVelocity().x = 0;
        }
        return true;
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
