package com.rius.monospace.main;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Screen;
import com.monospace.framework.Input.TouchEvent;
import com.rius.monospace.Assets;
import com.rius.monospace.JoyStick;

public class PlayScreen extends Screen{

	//drawing
	private Paint paint;
	
	//joysticks
	private JoyStick joystick_movement, joystick_fire;

	public PlayScreen(Game game) {
		super(game);
		// Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        
        //init joysticks
        joystick_movement = new JoyStick();
        joystick_movement.setStickWidth(50);
	    joystick_movement.setLayoutSize(125);
	    joystick_movement.setLayoutAlpha(150);
	    joystick_movement.setLayoutPosition(170,550);
	    joystick_movement.setStickAlpha(100);
	    joystick_movement.setMinimumDistance(15);
	    
	    joystick_movement.init();
	    
	    joystick_fire = new JoyStick();
	    joystick_fire.setStickWidth(50);
	    joystick_fire.setLayoutSize(125);
	    joystick_fire.setLayoutAlpha(150);
	    joystick_fire.setLayoutPosition(1110,550);
	    joystick_fire.setStickAlpha(100);
	    joystick_fire.setMinimumDistance(15);
	    
	    joystick_fire.init();
		
	}

	@Override
	public void update(float deltaTime) {
		
		//touch events
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            
            //may want to streamline touch events and put them here
            joystick_movement.update(event);
            joystick_fire.update(event);
            
        }
		
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
        g.clearScreen(Color.BLACK);
        g.drawString("GameScreen!", 640, 300, paint);
        
        //draw joysticks
        joystick_movement.draw(g);
        joystick_fire.draw(g);
		
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
		pause();
		game.setScreen(Assets.screen_paused);
	}

}
