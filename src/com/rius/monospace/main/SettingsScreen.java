package com.rius.monospace.main;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Screen;
import com.monospace.framework.Input.TouchEvent;
import com.rius.monospace.Assets;
import com.rius.monospace.Monospace;

public class SettingsScreen extends Screen{

	private Paint title, opts;
	
	public SettingsScreen(Game game) {
		super(game);
		
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
        	if (i < touchEvents.size()) {
	            TouchEvent event = touchEvents.get(i);
	            
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	
	            	//Music Button Up, down, Play/pause
	            	if (inBounds(event,710, 190, 80, 80)){
	            		if (Assets.theme_volume < 1f){
		            		Assets.theme_volume += 0.05f;
		            		Assets.theme.setVolume(Assets.theme_volume);
	            		}
	            	}
	            	if (inBounds(event,800, 190, 80, 80)){
	            		if (Assets.theme_volume > 0f){
	            			Assets.theme_volume -= 0.05f;
		            		Assets.theme.setVolume(Assets.theme_volume);
	            		}
	            	}
	            	
	            	if (inBounds(event,890, 190, 80, 80)){
	            		if (Assets.theme.isPlaying()){
	            			Assets.theme.pause();
	            		}else{
	            			Assets.theme.play();
	            			Assets.theme.setVolume(Assets.theme_volume);
	            		}
	            	}
	            	
	            }
            }
        }
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawScaledImage(Assets.stars, 0, 0, 1280, 720, 0, 0, Assets.stars.getWidth(), Assets.stars.getHeight());
		
		g.drawImage(Assets.settingGear, 100, 47);
		g.drawString("SETTINGS", 210, 100, title);
		
		//music button
		g.drawString("MUSIC: " + Math.round(Assets.theme_volume * 100), 210, 250, opts);
        g.drawRectHollow(200, 190, 500, 80, Color.WHITE);
        //up
        g.drawImage(Assets.triangle, 720, 200);
        g.drawRectHollow(710, 190, 80, 80, Color.WHITE);
        //down
        g.drawImage(Assets.triangleInv, 810, 200);
        g.drawRectHollow(801, 190, 80, 80, Color.WHITE); //x 800 doesn't work...
        //play pause
        g.drawString("P",910,250,opts);
        g.drawRectHollow(890, 190, 80, 80, Color.WHITE);
		
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
    	title = null;
    	//ru garbage cleanup
    	System.gc();
    }

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		System.out.println("Go back to main menu");
		//nullify();
		game.setScreen(new MainMenuScreen(game));
	}

}
