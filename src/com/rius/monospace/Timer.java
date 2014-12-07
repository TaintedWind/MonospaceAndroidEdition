package com.rius.monospace;

import com.rius.monospace.GameScreen;

public class Timer {

    int time, maxTime;

    public Timer(int max) {
        this.time = 0;
        this.maxTime = max;
    }

    public void update() {
        time += GameScreen.deltaTime;
    }

    public void reset() {
        time = 0;
    }
    
    public boolean expired() {
        if (time >= maxTime) {
            return true;
        } else {
            return false;
        }
    }

    public int getTime() {
        return time;
    }
    
    public int getMinutes() {
        return time / 1000 / 60;
    }
    
    public int getSeconds() {
        return time / 1000 % 60;
    }
    
    public void setTime(int t) {
        time = t;
    }
    
    public void setMaxTime(int t) {
        maxTime = t;
    }
    
}