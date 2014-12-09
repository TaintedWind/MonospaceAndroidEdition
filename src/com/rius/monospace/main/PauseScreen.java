package com.rius.monospace.main;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Input.TouchEvent;
import com.monospace.framework.Screen;
import com.rius.monospace.Assets;
import com.rius.monospace.Monospace;

public class PauseScreen extends Screen{

	private Paint title,paint,opts;

	public PauseScreen(Game game) {
		super(game);
		//draw objects
		paint = new Paint();
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        
        title = new Paint();
		title.setColor(Color.WHITE);
		title.setTextSize(60);
		title.setTypeface(Monospace.spaceFont);
		title.setShadowLayer((float)0.01, 7, 7, Color.DKGRAY);
		
		opts = new Paint();
		opts.set(title);
		opts.clearShadowLayer();

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	
            	//Resume
            	if (inBounds(event,200, 190, 500, 80)) {
            		game.setScreen(Assets.screen_running);
            	}
            	//Main Menu
            	if (inBounds(event,200, 290, 500, 80)) {
            		game.setScreen(new MainMenuScreen(game));
            	}
            }
        }
		
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(100, 0, 0, 0);
        g.drawString("PAUSED", 210, 100, title);
        
        g.drawString("RESUME", 210, 250, opts);
        g.drawRectHollow(200, 190, 500, 80, Color.WHITE);
        
        g.drawString("MAIN MENU", 210, 350, opts);
        g.drawRectHollow(200, 290, 500, 80, Color.WHITE);
		
	}
	
	private boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }
	
	//clear all variables
    public void nullify(){
    	
    	//ru garbage cleanup
    	System.gc();
    }


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		//game.setScreen(Assets.screen_running);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}
	
}
