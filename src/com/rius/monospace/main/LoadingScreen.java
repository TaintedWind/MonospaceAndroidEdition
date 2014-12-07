package com.rius.monospace.main;

import android.graphics.Typeface;
import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Screen;
import com.monospace.framework.Graphics.ImageFormat;
import com.rius.monospace.Assets;
import com.rius.monospace.Monospace;


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
        
        //set up the screens
  		Assets.screen_mainMenu = new MainMenuScreen(game);
  		Assets.screen_paused = new PauseScreen(game);
  		Assets.screen_running = new PlayScreen(game);
  		//screen_ready = new ReadyScreen(this);
        
        //load the game
        game.setScreen(Assets.screen_mainMenu);

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