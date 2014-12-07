package com.rius.monospace;

import android.graphics.Bitmap;

import com.monospace.framework.Image;
import com.monospace.framework.Music;
import com.monospace.framework.Sound;

public class Assets {
	
	public static Image menu, player, stars;
    public static Image button;
    public static Sound click;
    public static Music theme;
    
    public static void load(Monospace monospace) {
        // TODO Auto-generated method stub
        theme = monospace.getAudio().createMusic("sounds/song.ogg");
        theme.setLooping(true);
        theme.setVolume(0.8f);
        theme.play();
    }
    
}
