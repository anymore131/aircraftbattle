package com.edu.aircraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Vector;

public class GamePanel extends JPanel{
    Vector<EnemyPlane> enemyPlanes = new Vector<>();
    Vector<Bomb> smallBombs = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    MyPlane myPlane = new MyPlane(400, 400);
    int grade = 0;
    int myFlag = 0;
    int level = 0;
    int temp = 0;
    int over = 0;
    private int key = 0;
    Font font = new Font("微软雅黑", Font.BOLD, 25);
    Image imageMyPlane = null;
    Image imageEnemyPlane = null;
    Image imageMyShot = null;
    Image imageEnemyShot = null;
    Image imageGameBackground = null;
    Image imageStartBackground = null;
    Image imageGameOver = null;
    Image imageBomb1 = null;
    Image imageBomb2 = null;
    Image imageBomb3 = null;
    URL classUrl01 = this.getClass().getResource("");
    URL classUrl02 = this.getClass().getResource("/mouse.png");
    public GamePanel() {
        imageMyPlane = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/my.png"));
        imageEnemyPlane = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/enemy.png"));
        imageMyShot = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/shot.png"));
        imageEnemyShot = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/shot2.png"));
        imageGameBackground = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/background.png"));
        imageStartBackground = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/startBackground.png"));
        imageGameOver = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/gameover.png"));
        imageBomb3 = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/bomb26x26.png"));
        imageBomb2 = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/bomb38x39.png"));
        imageBomb1 = Toolkit.getDefaultToolkit().getImage(
                GamePanel.class.getResource("/bomb40x42.png"));
    }

    public void paint(Graphics g) {
        super.paint(g);

        switch (key) {
            case 0:// 主页面
                g.drawImage(imageGameOver, 0, 0,
                        GameData.getWindowWidth(), GameData.getWindowHeight(), this);
                Image imageCursor02 = Toolkit.getDefaultToolkit().getImage(classUrl02);

                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(imageCursor02,
                        new Point(0, 0), "cursor"));
                g.drawImage(imageStartBackground, 0, 0,
                        GameData.getWindowWidth(), GameData.getWindowHeight(), this);

                if (key == 0) {
                    addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getButton() == e.BUTTON1 && key == 0) {
                                myPlane.setX(e.getX() - GameData.getMyPlaneWidth() / 2);
                                myPlane.setY(e.getY() - GameData.getMyPlaneHeight() / 2);
                                key = 1;
                                removeMouseListener(this);
                            }
                        }
                    });

                }
                break;
            case 1:// 游戏页面
                   // 设置背景
                g.drawImage(imageGameBackground, 0, 0,
                        GameData.getWindowWidth(), GameData.getWindowHeight(), this);
                Image imageCursor01 = Toolkit.getDefaultToolkit().getImage(classUrl01);
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(imageCursor01,
                        new Point(0, 0), "cursor"));

                if (myPlane.isLive()) {
                    g.drawImage(imageMyPlane, myPlane.getX(), myPlane.getY(),
                            GameData.getMyPlaneWidth(), GameData.getMyPlaneHeight(), this);
                    myPlaneShot();
                    // 绘制我方子弹
                    for (int i = 0; i < myPlane.shots.size(); i++) {
                        Shot shot = myPlane.shots.get(i);
                        if (shot != null && shot.isLive()) {
                            g.drawImage(imageMyShot, shot.getX(), shot.getY(),
                                    GameData.getShotWidth(), GameData.getShotHeight(), this);
                        } else if (!shot.isLive()) {
                            myPlane.shots.remove(i);
                        }
                    }
                }
                //跟着鼠标移动
                addMouseMotionListener(new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        myPlane.setX(e.getX() - GameData.getMyPlaneWidth() / 2);
                        myPlane.setY(e.getY() - GameData.getMyPlaneHeight() / 2);
                        removeMouseMotionListener(this);
                    }
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        myPlane.setX(e.getX() - GameData.getMyPlaneWidth() / 2);
                        myPlane.setY(e.getY() - GameData.getMyPlaneHeight() / 2);
                        removeMouseMotionListener(this);
                    }
                });

                // 添加敌方飞机
                //得到鼠标位置




                moreEnemyPlane();
                while (enemyPlanes.size() <= level) {
                    newEnemyPlane();
                }
                // 绘制我方飞机

                // 绘制敌方飞机
                for (int i = 0; i < enemyPlanes.size(); i++) {
                    EnemyPlane enemyPlane = enemyPlanes.get(i);
                    if (enemyPlane.isLive()) {
                        g.drawImage(imageEnemyPlane, enemyPlane.getX(), enemyPlane.getY(),
                                GameData.getEnemyPlaneWidth(), GameData.getEnemyPlaneHeight(), this);
                        enemyPlaneShot(enemyPlane);
                    }
                    // 绘制敌方子弹
                    for (int j = 0; j < enemyPlane.shots.size(); j++) {
                        Shot shot = enemyPlane.shots.get(j);
                        if (shot != null && shot.isLive()) {
                            g.drawImage(imageEnemyShot, shot.getX(), shot.getY(),
                                    GameData.getShotWidth(), GameData.getShotHeight(), this);
                        } else if (!shot.isLive()) {
                            enemyPlane.shots.remove(j);
                        }
                    }
                    if (!enemyPlane.isLive() && enemyPlane.shots.size() == 0) {
                        enemyPlanes.remove(i);
                    }
                }
                // 绘制我方受伤
                for (int i = 0; i < myPlane.bombs.size(); i++) {
                    Bomb bomb = myPlane.bombs.get(i);
                    // 阵亡
                    if (bomb.live > 60 && bomb.level == 0) {
                        g.drawImage(imageBomb3, bomb.x, bomb.y, 50, 50, this);
                    } else if (bomb.live > 30 && bomb.level == 0) {
                        g.drawImage(imageBomb2, bomb.x, bomb.y, 65, 65, this);
                    } else if (bomb.live > 0 && bomb.level == 0) {
                        g.drawImage(imageBomb1, bomb.x, bomb.y, 70, 70, this);
                    }
                    // 小伤
                    if (bomb.level == 1 && bomb.live > 0) {
                        g.drawImage(imageBomb3, bomb.x + 7, bomb.y, 26, 26, this);
                    }
                    bomb.liveDown();
                    if (bomb.live == 0 && bomb.level == 0) {
                        over = 1;
                    }
                    if (!bomb.isLive) {
                        myPlane.bombs.remove(i);
                    }
                }
                // 绘制子弹爆炸
                for (int i = 0; i < smallBombs.size(); i++) {
                    Bomb bomb = smallBombs.get(i);
                    if (bomb.live > 0) {
                        g.drawImage(imageBomb3, bomb.x, bomb.y, 18, 18, this);
                    }
                    bomb.liveDown();
                    if (!bomb.isLive) {
                        smallBombs.remove(i);
                    }
                }
                // 绘制敌机爆炸
                for (int i = 0; i < bombs.size(); i++) {
                    Bomb bomb = bombs.get(i);
                    if (bomb.live > 60 && bomb.level == 0) {
                        g.drawImage(imageBomb3, bomb.x + 7, bomb.y, 26, 26, this);
                    } else if (bomb.live > 30 && bomb.level == 0) {
                        g.drawImage(imageBomb2, bomb.x, bomb.y - 4, 38, 38, this);
                    } else if (bomb.live > 0 && bomb.level == 0) {
                        g.drawImage(imageBomb1, bomb.x, bomb.y - 6, 40, 40, this);
                    }
                    if (bomb.live > 60 && bomb.level == 2) {
                        g.drawImage(imageBomb3, bomb.x + 7, bomb.y, 40, 40, this);
                    } else if (bomb.live > 30 && bomb.level == 2) {
                        g.drawImage(imageBomb2, bomb.x, bomb.y - 4, 52, 52, this);
                    } else if (bomb.live > 0 && bomb.level == 2) {
                        g.drawImage(imageBomb1, bomb.x, bomb.y - 6, 56, 56, this);
                    }
                    bomb.liveDown();
                    if (!bomb.isLive) {
                        bombs.remove(i);
                    }
                }
                hitShot();
                hitMyPlane();
                hitEnemyPlane();
                hitBothPlane();
                // 设置分数
                show(g);
                if (myPlane.isLive() == false && over == 1
                        && myPlane.bombs.size() == 0 && smallBombs.size() == 0 && bombs.size() == 0) {
                    key = 2;
                }
                break;
            case 2:// 结束页面
                g.drawImage(imageGameOver, 0, 0,
                        GameData.getWindowWidth(), GameData.getWindowHeight(), this);
                showWordCenter(g);
                imageCursor02 = Toolkit.getDefaultToolkit().getImage(classUrl02);
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(imageCursor02,
                        new Point(0, 0), "cursor"));
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getX() >= 160 && e.getX() <= 320 && e.getY() >= 490 && e.getY() <= 540
                                && key == 2) {
                            key = 1;
                            grade = 0;
                            myFlag = 0;
                            level = 0;
                            temp = 0;
                            over = 0;
                            myPlane.reMyPlane();
                            addMouseMotionListener(new MouseMotionListener() {
                                @Override
                                public void mouseDragged(MouseEvent e) {
                                    myPlane.setX(e.getX() - GameData.getMyPlaneWidth() / 2);
                                    myPlane.setY(e.getY() - GameData.getMyPlaneHeight() / 2);
                                    removeMouseMotionListener(this);
                                }
                                @Override
                                public void mouseMoved(MouseEvent e) {
                                    myPlane.setX(e.getX() - GameData.getMyPlaneWidth() / 2);
                                    myPlane.setY(e.getY() - GameData.getMyPlaneHeight() / 2);
                                    removeMouseMotionListener(this);
                                }
                            });
                            enemyPlanes.clear();
                            bombs.clear();
                            smallBombs.clear();
                            removeMouseListener(this);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else if (e.getX() >= 160 && e.getX() <= 320 && e.getY() >= 580 && e.getY() <= 630
                                && key == 2) {
                            System.exit(0);
                        }
                    }
                });
                break;
        }
        this.repaint();
    }

    public void show(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("分数： " + grade + "", 10, 20);
        g.setColor(Color.red);
        g.drawString("生命： " + myPlane.getBlood() + "", GameData.getWindowWidth() - 150, 20);
    }

    public void showWordCenter(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(font);
        int length = 0;
        int grade2 = grade;
        while (grade2 > 0) {
            grade2 = grade2 / 10;
            length++;
        }
        g.drawString(grade + "", (GameData.getWindowWidth() / 2) - ((length * 15) / 2), 400);
    }

    public void myPlaneShot() {
        myFlag++;
        if (myFlag % 75 == 0) {
            Shot shot = new Shot(myPlane.getX() + GameData.getMyPlaneWidth() / 2 - GameData.getShotWidth() / 2,
                    myPlane.getY() - GameData.getShotHeight(), 0);
            myPlane.shots.add(shot);
            new Thread(shot).start();
        }
    }

    public void hitMyPlane() {
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            for (int j = 0; j < enemyPlane.shots.size(); j++) {
                Shot shot = enemyPlane.shots.get(j);
                if (shot != null && myPlane.isLive() && shot.isLive()) {
                    if (shot.getX() + GameData.getShotWidth() > myPlane.getX()
                            && shot.getX() + GameData.getShotWidth() < myPlane.getX() + GameData.getMyPlaneWidth()
                            && shot.getY() + GameData.getShotHeight() > myPlane.getY()
                            && shot.getY() < myPlane.getY() + GameData.getMyPlaneHeight()) {
                        shot.setLive(false);
                        myPlane.setBlood(myPlane.getBlood() - 1);
                        Bomb bomb = new Bomb(shot.getX(), shot.getY());
                        if (myPlane.getBlood() > 0) {
                            bomb.level = 1;
                            bomb.live = 30;
                        } else {
                            bomb.live = 90;
                            myPlane.setLive(false);
                        }
                        myPlane.bombs.add(bomb);
                        enemyPlane.shots.remove(j);
                    }
                }
            }
        }
    }

    public void hitEnemyPlane() {
        for (int i = 0; i < myPlane.shots.size(); i++) {
            Shot shot = myPlane.shots.get(i);
            for (int j = 0; j < enemyPlanes.size(); j++) {
                EnemyPlane enemyPlane = enemyPlanes.get(j);
                if (shot != null && enemyPlane.isLive() && shot.isLive()) {
                    if (shot.getX() + GameData.getShotWidth() > enemyPlane.getX()
                            && shot.getX() + GameData.getShotWidth() < enemyPlane.getX() + GameData.getEnemyPlaneWidth()
                            && shot.getY() + GameData.getShotHeight() > enemyPlane.getY()
                            && shot.getY() + GameData.getShotHeight() < enemyPlane.getY()
                                    + GameData.getEnemyPlaneHeight()) {
                        shot.setLive(false);
                        enemyPlane.setLive(false);
                        myPlane.shots.remove(i);
                        Bomb bomb = new Bomb(enemyPlane.getX(), enemyPlane.getY());
                        bombs.add(bomb);
                        grade += 10;
                    } else if (shot.getX() > enemyPlane.getX()
                            && shot.getX() < enemyPlane.getX() + GameData.getEnemyPlaneWidth()
                            && shot.getY() > enemyPlane.getY()
                            && shot.getY() < enemyPlane.getY() + GameData.getEnemyPlaneHeight()) {
                        shot.setLive(false);
                        enemyPlane.setLive(false);
                        myPlane.shots.remove(i);
                        Bomb bomb = new Bomb(enemyPlane.getX(), enemyPlane.getY());
                        bombs.add(bomb);
                        grade += 10;
                    }
                }
            }
        }
    }

    public void hitBothPlane() {
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            if (enemyPlane.isLive() && myPlane.isLive()) {
                if (enemyPlane.getX() + GameData.getEnemyPlaneWidth() > myPlane.getX()
                        && enemyPlane.getX() + GameData.getEnemyPlaneWidth() < myPlane.getX()
                                + GameData.getMyPlaneWidth()
                        && enemyPlane.getY() + GameData.getEnemyPlaneHeight() > myPlane.getY()
                        && enemyPlane.getY() < myPlane.getY() + GameData.getMyPlaneHeight()) {
                    myPlane.setBlood(myPlane.getBlood() - 2);
                    Bomb bomb = new Bomb(enemyPlane.getX(), enemyPlane.getY());
                    bomb.level = 2;
                    bombs.add(bomb);
                    if (myPlane.getBlood() <= 0) {
                        Bomb bomb1 = new Bomb(myPlane.getX(), myPlane.getY());
                        bomb1.level = 0;
                        bomb1.live = 90;
                        myPlane.bombs.add(bomb1);
                        myPlane.setLive(false);
                    }
                    enemyPlane.setLive(false);
                }
            }
        }
    }

    public void hitShot() {
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            for (int j = 0; j < enemyPlane.shots.size(); j++) {
                Shot shot02 = enemyPlane.shots.get(j);
                for (int k = 0; k < myPlane.shots.size(); k++) {
                    Shot shot01 = myPlane.shots.get(k);
                    if (shot01 != null && shot01.isLive() && shot02 != null && shot02.isLive()) {
                        if (shot01.getY() >= shot02.getY() && shot01.getY() <= shot02.getY() + 20
                                && shot01.getX() <= shot02.getX() + 10 && shot01.getX() >= shot02.getX()) {
                            shot01.setLive(false);
                            shot02.setLive(false);
                            enemyPlanes.get(i).shots.remove(j);
                            myPlane.shots.remove(k);
                            Bomb bomb = new Bomb(shot01.getX(), shot01.getY());
                            bomb.live = 30;
                            smallBombs.add(bomb);
                            grade++;
                        } else if (shot01.getY() >= shot02.getY() && shot01.getY() <= shot02.getY() + 20
                                && shot01.getX() + 10 <= shot02.getX() + 10 && shot01.getX() + 10 >= shot02.getX()) {
                            shot01.setLive(false);
                            shot02.setLive(false);
                            enemyPlanes.get(i).shots.remove(j);
                            myPlane.shots.remove(k);
                            Bomb bomb = new Bomb(shot01.getX(), shot01.getY());
                            bomb.live = 30;
                            smallBombs.add(bomb);
                            grade++;
                        }
                    }
                }
            }
        }
    }

    public void moreEnemyPlane() {
        temp++;
        if (temp / 1000 > 3 && level <= 4) {
            level++;
            temp = 0;
        }
    }

    public void enemyPlaneShot(EnemyPlane enemyPlane) {
        int enemyFlag = enemyPlane.getEnemyFlag();
        enemyFlag++;
        if (enemyFlag % 200 == 0) {
            Shot shot = new Shot(enemyPlane.getX() + GameData.getEnemyPlaneWidth() / 2 - GameData.getShotWidth() / 2,
                    enemyPlane.getY() + GameData.getEnemyPlaneHeight() - GameData.getShotHeight(), 1);
            enemyPlane.shots.add(shot);
            new Thread(shot).start();
        }
        enemyPlane.setEnemyFlag(enemyFlag);
    }

    public void newEnemyPlane() {
        int xPlace = (int) (Math.random() * (GameData.getWindowWidth() - GameData.getEnemyPlaneWidth()));
        int yPlace = -GameData.getEnemyPlaneHeight();
        EnemyPlane enemyPlane = new EnemyPlane(xPlace, yPlace);
        new Thread(enemyPlane).start();
        enemyPlanes.add(enemyPlane);
    }

//    @Override
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
