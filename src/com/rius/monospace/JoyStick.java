package com.rius.monospace;

import com.monospace.framework.Graphics;
import com.monospace.framework.Game;
import com.monospace.framework.Input.TouchEvent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class JoyStick {
	public static final int STICK_NONE = 0;
	public static final int STICK_UP = 1;
	public static final int STICK_UPRIGHT = 2;
	public static final int STICK_RIGHT = 3;
	public static final int STICK_DOWNRIGHT = 4;
	public static final int STICK_DOWN = 5;
	public static final int STICK_DOWNLEFT = 6;
	public static final int STICK_LEFT = 7;
	public static final int STICK_UPLEFT = 8; 
	
	private int STICK_ALPHA = 200;
	private int LAYOUT_ALPHA = 200;
	
	private float DRAW_MAX;
	
	private int touchId = -1;
	
	//the size and location of joystick
	private float stick_width = 0, stick_x = 0, stick_y = 0;
	private float pad_width = 0, pad_x = 0, pad_y = 0;
	
	//position values of stick
	private int position_x = 0, position_y = 0, min_distance = 0;
	private float distance = 0, angle = 0;

	//what it looks like
	private Paint paint_stick, paint_pad;
	private int stickColor, padColor;
	
	private boolean touch_state = false;
	
	public JoyStick () {
        
		//bg pad
        padColor = Color.DKGRAY;
        paint_pad = new Paint();
        paint_pad.setColor(padColor);
        
        //stick
        stickColor = Color.RED;
        paint_stick = new Paint();
        paint_stick.setColor(stickColor);
        
	}
	
	//things that only have to run once
	public void init(){
		//the farthest position that the stick can be
		DRAW_MAX = pad_width - (stick_width / 2);
	}
	
	//call this to be updated
	public void update(TouchEvent event){
		
		//====================================================Joysticks need to work independently===============
		
		float tapX = event.x;
		float tapY = event.y;
		
		if (touchId == -1 || touchId == event.pointer){

			//relative positions of the stick
			position_x = (int) (tapX - pad_x);
			position_y = (int) (tapY - pad_y);
			//distance = sqrt(a^2 + b^2) 
			distance = (float) Math.sqrt(Math.pow(position_x, 2) + Math.pow(position_y, 2));
			angle = (float) cal_angle(position_x, position_y);
			
		}
		
		if (event.type == TouchEvent.TOUCH_DOWN) {
			//if tap on joystick
	    	if ((int)distance <= DRAW_MAX){
	    		if (touchId == -1){
		    		touch_state = true;
		    		touchId = event.pointer;
	    		}
	    	}
	    }else if (event.type == TouchEvent.TOUCH_UP) {
	    	if (touchId == event.pointer) {
		    	touchId = -1;
	    	}
	    }
		
		//reset
		if (touchId == -1){
			distance = 0;
			angle = 0;
			touch_state = false;
			stick_x = pad_x;
	    	stick_y = pad_y;
		}
		
		//check if correct tapping
		if (event.pointer == touchId && touch_state){
			//draw the stick if it is in the circle
			if (distance <= DRAW_MAX){
				stick_x = tapX;
				stick_y = tapY;
			} else if (distance > DRAW_MAX){
				//draw inside if tap is outside
				stick_x = (float) (DRAW_MAX * (Math.cos(Math.toRadians(angle)))) + pad_x;
				stick_y = (float) (DRAW_MAX * (Math.sin(Math.toRadians(angle)))) + pad_y;
			}
			
		}
		
	}
	/*
	//call this to be updated
		public void update(int tapX, int tapY){
			//change position of stick
			
			//relative positions of the stick
			position_x = (int) (tapX - pad_x);
			position_y = (int) (tapY - pad_y);
			//distance = sqrt(a^2 + b^2) 
			distance = (float) Math.sqrt(Math.pow(position_x, 2) + Math.pow(position_y, 2));
			angle = (float) cal_angle(position_x, position_y);
			
			//draw the stick if it is in the circle
			if (distance <= DRAW_MAX){
				stick_x = tapX;
				stick_y = tapY;
			} else if (distance > DRAW_MAX && touch_state){
				//draw inside if tap is outside
				//distance = DRAW_MAX;
				stick_x = (float) (DRAW_MAX * (Math.cos(Math.toRadians(angle)))) + pad_x;
				stick_y = (float) (DRAW_MAX * (Math.sin(Math.toRadians(angle)))) + pad_y;
			}
			
		}*/
	
	//calculate an angle
	private double cal_angle(float x, float y) {
		if(x >= 0 && y >= 0)
			return Math.toDegrees(Math.atan(y / x));
	    else if(x < 0 && y >= 0)
	    	return Math.toDegrees(Math.atan(y / x)) + 180;
	    else if(x < 0 && y < 0)
	    	return Math.toDegrees(Math.atan(y / x)) + 180;
	    else if(x >= 0 && y < 0) 
	    	return Math.toDegrees(Math.atan(y / x)) + 360;
		return 0;
	}
	
	public void draw(Graphics g) {
		//draw the bg
		g.drawCircle(pad_x, pad_y, pad_width, paint_pad);
		//draw the joystick
		g.drawCircle(stick_x, stick_y, stick_width, paint_stick);
		
		//some debug stuff
		g.drawString("StickPos: " + stick_x + ", "+ stick_y, (int)pad_x-30, 310, paint_pad);
		g.drawString("Angle: " + angle, (int)pad_x-30, 320, paint_pad);
		g.drawString("Distance: " + distance, (int)pad_x-30, 330, paint_pad);
		g.drawString("TouchState:" + touch_state, (int)pad_x-30, 340, paint_pad);
		g.drawString("TouchId:" + touchId, (int)pad_x-30, 350, paint_pad);
	}
	
	public int[] getPosition() {
		if(distance > min_distance && touch_state) {
			return new int[] { position_x, position_y };
		}
		return new int[] { 0, 0 };
	}
	
	public int getX() {
		if(distance > min_distance && touch_state) {
			return position_x;
		}
		return 0;
	}
	
	public int getY() {
		if(distance > min_distance && touch_state) {
			return position_y;
		}
		return 0;
	}
	
	public float getAngle() {
		if(distance > min_distance && touch_state) {
			return angle;
		}
		return 0;
	}
	
	public float getDistance() {
		if(distance > min_distance && touch_state) {
			return distance;
		}
		return 0;
	}
	
	public void setMinimumDistance(int minDistance) {
		min_distance = minDistance;
	}
	
	public int getMinimumDistance() {
		return min_distance;
	}
	
	public int getDrawMax(){
		return (int)DRAW_MAX;
	}
	
	public int get8Direction() {
		if(distance > min_distance && touch_state) {
			if(angle >= 247.5 && angle < 292.5 ) {
				return STICK_UP;
			} else if(angle >= 292.5 && angle < 337.5 ) {
				return STICK_UPRIGHT;
			} else if(angle >= 337.5 || angle < 22.5 ) {
				return STICK_RIGHT;
			} else if(angle >= 22.5 && angle < 67.5 ) {
				return STICK_DOWNRIGHT;
			} else if(angle >= 67.5 && angle < 112.5 ) {
				return STICK_DOWN;
			} else if(angle >= 112.5 && angle < 157.5 ) {
				return STICK_DOWNLEFT;
			} else if(angle >= 157.5 && angle < 202.5 ) {
				return STICK_LEFT;
			} else if(angle >= 202.5 && angle < 247.5 ) {
				return STICK_UPLEFT;
			}
		} else if(distance <= min_distance && touch_state) {
			return STICK_NONE;
		}
		return 0;
	}
	
	public int get4Direction() {
		if(distance > min_distance && touch_state) {
			if(angle >= 225 && angle < 315 ) {
				return STICK_UP;
			} else if(angle >= 315 || angle < 45 ) {
				return STICK_RIGHT;
			} else if(angle >= 45 && angle < 135 ) {
				return STICK_DOWN;
			} else if(angle >= 135 && angle < 225 ) {
				return STICK_LEFT;
			}
		} else if(distance <= min_distance && touch_state) {
			return STICK_NONE;
		}
		return 0;
	}
	
	public void setStickAlpha(int alpha) {
		STICK_ALPHA = alpha;
		paint_stick.setAlpha(alpha);
	}
	
	public int getStickAlpha() {
		return STICK_ALPHA;
	}
	
	public void setLayoutAlpha(int alpha) {
		LAYOUT_ALPHA = alpha;
		paint_stick.setAlpha(alpha);
		//mLayout.getBackground().setAlpha(alpha);
	}
	
	public int getLayoutAlpha() {
		return LAYOUT_ALPHA;
	}
	
	public void setStickWidth(int width) {
        stick_width = width;
	}
	
	public int getStickWidth() {
		return (int)stick_width;
	}
	
	public void setIsDown(boolean check){
		touch_state = check;
	}
	
	public void setLayoutSize(int width) {
		pad_width = width;
	}
	public void setLayoutPosition(int X,int Y) {
		pad_x = X;
		pad_y = Y;
	}

	public int getLayoutWidth() {
		return (int)pad_width;
	}
	
	public void setStickColor(int color){
		stickColor = color;
	}
	
}
