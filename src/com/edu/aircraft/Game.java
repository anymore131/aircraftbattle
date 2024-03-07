package com.edu.aircraft;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icon36x36.png"));

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        this.setTitle("Demo");
        this.setIconImage(imageIcon.getImage().getScaledInstance(36, 36,Image.SCALE_DEFAULT));
        GamePanel gamePanel = new GamePanel();
//        new Thread(gamePanel).start();
        this.add(gamePanel);
        this.setSize(GameData.getWindowWidth() + 14, GameData.getWindowHeight() + 37);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
