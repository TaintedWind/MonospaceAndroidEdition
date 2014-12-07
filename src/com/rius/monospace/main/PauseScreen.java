package com.rius.monospace.main;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Input.TouchEvent;
import com.monospace.framework.Screen;
import com.rius.monospace.Assets;

public class PauseScreen extends Screen{

	private Paint paint;

	public PauseScreen(Game game) {
		super(game);
		//draw objects
		paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	//go back to game
            	game.setScreen(Assets.screen_running);
            }
        }
		
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(100, 0, 0, 0);
        g.drawString("PAUSED",640, 300, paint);
		
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
