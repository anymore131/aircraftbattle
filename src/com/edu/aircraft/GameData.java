package com.edu.aircraft;

public class GameData {
    static final private int windowWidth = 480;
    static final private int windowHeight = 800;
    static final private int myPlaneWidth = 62;
    static final private int myPlaneHeight = 74;
    static final private int enemyPlaneWidth = 39;
    static final private int enemyPlaneHeight = 27;
    static final private int shotWidth = 9;
    static final private int shotHeight = 18;
    static final private int enemyPlaneSpeed = 2;
    static final private int enemyShotSpeed = 5;
    static final private int myShotSpeed = 10;
    public static int getWindowWidth() {
        return windowWidth;
    }
    public static int getWindowHeight() {
        return windowHeight;
    }
    public static int getMyPlaneWidth(){
        return myPlaneWidth;
    }
    public static int getMyPlaneHeight(){
        return myPlaneHeight;
    }
    public static int getEnemyPlaneWidth(){
        return enemyPlaneWidth;
    }
    public static int getEnemyPlaneHeight(){
        return enemyPlaneHeight;
    }
    public static int getShotWidth() {
        return shotWidth;
    }
    public static int getShotHeight() {
        return shotHeight;
    }
    public static int getEnemyPlaneSpeed(){
        return enemyPlaneSpeed;
    }
    public static int getEnemyShotSpeed(){
        return enemyShotSpeed;
    }
    public static int getMyShotSpeed(){
        return myShotSpeed;
    }
    public GameData() {
    }
}