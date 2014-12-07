package com.rius.monospace.main;

import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Graphics.ImageFormat;
import com.monospace.framework.Screen;
import com.rius.monospace.Assets;

public class SplashLoadingScreen extends Screen {
    public SplashLoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.player= g.newImage("images/player.png", ImageFormat.RGB565);
        
        game.setScreen(new LoadingScreen(game));

    }

    @Override
    public void paint(float deltaTime) {

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