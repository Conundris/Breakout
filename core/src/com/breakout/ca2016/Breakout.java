package com.breakout.ca2016;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.breakout.ca2016.Entities.LeaderBoard;
import com.breakout.ca2016.Entities.Player;
import com.breakout.ca2016.Screens.LeaderBoardScreen;
import com.breakout.ca2016.Screens.MainGameScreen;
import com.breakout.ca2016.Screens.MainMenuScreen;
import com.breakout.ca2016.Screens.PostGameScreen;
import com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT;

import java.util.ArrayList;

public class Breakout extends Game {

	public static boolean DEBUG = false;

	public LeaderBoard leaderBoard;
	public Player player;

	private static MainGameScreen mainGameScreen;
	private static MainMenuScreen mainMenuScreen;
	private static LeaderBoardScreen leaderBoardScreen;
	private static PostGameScreen postGameScreen;

	public Screen getScreenType(ScreenType screenType){
		switch(screenType){
			case MainMenu:
				return mainMenuScreen;
			case MainGame:
				return mainGameScreen;
			case LeaderBoard:
				return leaderBoardScreen;
			case PostGame:
				return postGameScreen;
			default:
				return mainGameScreen;
		}
	}

	@Override
	public void create () {

		Gdx.app.debug("Test", Gdx.files.getLocalStoragePath());

		LeaderBoard leaderBoard = LeaderBoard.getInstance();
		leaderBoard.loadLeaderBoard();

		// Creating Screns
		mainGameScreen = new MainGameScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		leaderBoardScreen = new LeaderBoardScreen(this);
		postGameScreen = new PostGameScreen(this);

		setScreen(mainMenuScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		mainGameScreen.dispose();
		mainMenuScreen.dispose();
	}

	public void endGame(int playerScore) {
		player.setScore(playerScore);
		setScreen(this.getScreenType(ScreenType.PostGame));
	}

}
