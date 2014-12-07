package com.rius.monospace;

import com.monospace.framework.Image;
import com.monospace.framework.Music;
import com.monospace.framework.Screen;
import com.monospace.framework.Sound;

public class Assets {
	
	public static Image menu, player, stars;
    public static Image button;
    public static Sound click;
    public static Music theme;
    
    //screens
    public static Screen screen_paused,screen_running,screen_mainMenu,screen_ready;
    
    public static void load(Monospace monospace) {
        // TODO Auto-generated method stub
        theme = monospace.getAudio().createMusic("sounds/song.ogg");
        theme.setLooping(true);
        theme.setVolume(0.8f);
        theme.play();
    }
    
}
