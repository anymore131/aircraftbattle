package com.edu.aircraft;

import java.util.Vector;

public class MyPlane extends Plane{
    Vector<Shot> shots = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    private int blood = 10;
    private boolean isLive = true;
    public int getBlood() {
        return blood;
    }
    public void setBlood(int blood) {
        this.blood = blood;
    }
    public boolean isLive() {
        return isLive;
    }
    public void setLive(boolean live) {
        isLive = live;
    }
    public MyPlane(int x, int y) {
        super(x, y);
    }
    public void reMyPlane(){
        blood = 10;
        isLive = true;
        shots.clear();
    }
}
