package com.rius.monospace;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.Display;

import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Image;
import com.monospace.framework.Screen;
import com.monospace.framework.Graphics.ImageFormat;
import com.rius.monospace.R.string;


public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
        
    }
    
    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        
        //load images
        Assets.menu = g.newImage("images/starfield.png", ImageFormat.RGB565);
        Assets.stars = g.newImage("images/starfield.png", ImageFormat.RGB565);
        
        
        
        //load fonts
        Monospace.spaceFont = Typeface.createFromAsset(Monospace.getContext().getAssets(),"fonts/font.ttf");
        
        //load sounds
        //Assets.click = game.getAudio().createSound("explode.ogg");
        
        //load the game
        game.setScreen(new MainMenuScreen(game));

    }


    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	g.drawImage(Assets.player, 640, 360);

    }


    @Override
    public void pause() {


    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {


    }
}