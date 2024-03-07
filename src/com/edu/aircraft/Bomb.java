package com.edu.aircraft;

public class Bomb {
    int x;
    int y;
    int live = 90;
    int level = 0;
    boolean isLive = true;
    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void liveDown(){
        if (live > 0){
            live--;
        }else isLive = false;
    }
}
