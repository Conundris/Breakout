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
import com.breakout.ca2016.Screens.LeaderBoardScreen;
import com.breakout.ca2016.Screens.MainGameScreen;
import com.breakout.ca2016.Screens.MainMenuScreen;
import com.breakout.ca2016.Screens.PostGameScreen;

public class Breakout extends Game {

	public static boolean DEBUG = false;

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
		// Creating Creators
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

}
