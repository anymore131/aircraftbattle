package com.edu.aircraft;

import java.util.Vector;

public class EnemyPlane implements Runnable{
    private int x;
    private int y;
    private int level = 1;
    private int enemyFlag = 0;
    private boolean isLive = true;
    Vector<Shot> shots = new Vector<>();
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
    public EnemyPlane(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public boolean isLive() {
        return isLive;
    }
    public void setLive(boolean live) {
        isLive = live;
    }
    public int getEnemyFlag() {
        return enemyFlag;
    }
    public void setEnemyFlag(int enemyFlag) {
        this.enemyFlag = enemyFlag;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (isLive && y < GameData.getWindowHeight()){
                y = (y + GameData.getEnemyPlaneSpeed());
            }else {
                isLive = false;
                break;
            }
        }
    }
}
