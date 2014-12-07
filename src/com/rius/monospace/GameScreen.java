package com.rius.monospace;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.monospace.framework.Game;
import com.monospace.framework.Graphics;
import com.monospace.framework.Image;
import com.monospace.framework.Screen;
import com.monospace.framework.Input.TouchEvent;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;
    
    JoyStick joystick_movement, joystick_fire;
    
    public static float deltaTime;
    
    Screen paused;

    // Variable Setup
    // You would create game objects here.

    int livesLeft = 1;
    Paint paint;

    public GameScreen(Game game) {
        super(game);
        //make the screens to be updated and stuff
        paused = new PauseScreen(game);

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
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        
        this.deltaTime = deltaTime;

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        // When the user touches the screen, the game begins. 
        // state now becomes GameState.Running.
        
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        Graphics g = game.getGraphics();
        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            
            //may want to streamline touch events and put them here
            joystick_movement.update(event);
            joystick_fire.update(event);
            
        }
        
        // 2. Check miscellaneous events like death:
        
        if (livesLeft == 0) {
            state = GameState.GameOver;
        }
        
        // 3. Call individual update() methods here.
        
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	state = GameState.Running;
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 300 && event.x < 980 && event.y > 100
                        && event.y < 500) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.clearScreen(Color.BLACK);
        g.drawString("Tap to play!",
                640, 300, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.clearScreen(Color.BLACK);
        g.drawString("GameScreen!", 640, 300, paint);
        
        //draw joysticks
        joystick_movement.draw(g);
        joystick_fire.draw(g);
        

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(100, 0, 0, 0);
        g.drawString("PAUSED",
                640, 300, paint);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 640, 300, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }
    
}