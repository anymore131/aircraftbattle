package com.edu.aircraft;

public class Plane {
    private int x;
    private int y;
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public Plane(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
