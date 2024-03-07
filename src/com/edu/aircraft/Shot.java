package com.edu.aircraft;

public class Shot implements Runnable{
    private int x;
    private int y;
    private int direction;
    private boolean isLive = true;
    private int fiyLong = 0;
    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean isLive() {
        return isLive;
    }
    public void setLive(boolean live) {
        isLive = live;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getFiyLong() {
        return fiyLong;
    }
    public void setFiyLong(int fiyLong) {
        this.fiyLong = fiyLong;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (direction == 0){
                y -= GameData.getMyShotSpeed();//我方子弹
                fiyLong += GameData.getMyShotSpeed();
            } else if (direction == 1) {
                y += GameData.getEnemyShotSpeed();//敌方子弹
                fiyLong += GameData.getEnemyShotSpeed();
            }
            if (!isLive || fiyLong > GameData.getWindowHeight()){
                isLive = false;
                break;
            }
        }
    }
}
