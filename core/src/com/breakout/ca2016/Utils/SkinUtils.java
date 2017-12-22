package com.breakout.ca2016.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class SkinUtils {

    /*
     Title: Java Code Examples for com.badlogic.gdx.scenes.scene2d.ui.Skin.getFont() - Example 3
     Author: Programcreek
     Site owner/sponsor: http://www.programcreek.com/
     Date: 2016
     Code version: edited Jan 10 '13 at 17:42
     Availability: http://www.programcreek.com/java-api-examples/index.php?class=com.badlogic.gdx.scenes.scene2d.ui.Skin&method=getFont (Accessed 23 November 2016)
     Modified: Only taken needed code, changed color.
    */
    public static Skin CreateSkin(){
        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() /4, Gdx.graphics.getHeight() /11, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = skin.getFont("default");
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.background = skin.newDrawable("background", Color.GRAY);
        skin.add("default", textFieldStyle);

        return skin;
    }
}
