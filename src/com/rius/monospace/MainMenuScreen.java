package com.rius.monospace;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Image;
import com.monospace.framework.Screen;
import com.monospace.framework.Input.TouchEvent;


public class MainMenuScreen extends Screen {
	
	//the tile will be drawn in this font
	Paint title,titleSub,textReg;
	
	double bgY = 0;
	Timer backgroundTimer;
	ArrayList<Image> background;
	ArrayList<Bitmap> backgroundB;
	
    public MainMenuScreen(Game game) {
        super(game);
        
        //make the BG scroll list
        background = new ArrayList<Image>();
        background.add(Assets.stars);
        background.add(Assets.stars);
        
        //fonts
        title = new Paint();
        title.setTextSize(120);
        title.setTextAlign(Paint.Align.CENTER);
        title.setAntiAlias(true);
        title.setColor(Color.WHITE);
        title.setTypeface(Monospace.spaceFont);
        title.setShadowLayer((float)0.01, 7, 7, Color.DKGRAY);
        
        titleSub = new Paint();
        titleSub.set(title);
        titleSub.setTextSize(60);
        
        textReg = new Paint();
        textReg.set(title);
        textReg.setTextAlign(Paint.Align.LEFT);
        textReg.setTextSize(40);
        
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        
        //update bg
        bgY += 0.5 * deltaTime;
        
        if(bgY > (background.size() * 720) - 800){
        	background.add(Assets.stars);
        	System.out.println("Added BG Image");
        }

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, 0, 0, 1280, 720)) {
                    //START GAME
                    game.setScreen(new GameScreen(game));               
                }


            }
        }
    }


    private boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        
        //g.clearScreen(Color.BLACK);
        
        for (int i = 0; i != background.size(); i++) {
            if (bgY - (720 * i) < 720) {
                g.drawScaledImage(background.get(i), 0, (int)bgY - (720 * i),
                		1280, 720, 0, 0, background.get(i).getWidth(), background.get(i).getHeight());
                //g.drawRectHollow(0,(int)-bgY + (720 * i), 1280, 720, Color.MAGENTA);
            }
        }
        
        g.drawString("MONOSPACE", (640),(140), title);
        g.drawString("TAP TO PLAY", (640),(370), titleSub);
        g.drawString("0.0.1", (20),(700), textReg);
        
        
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
        //Display "Exit Game?" Box


    }
}