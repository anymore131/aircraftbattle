package com.edu.aircraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverDialog extends JDialog implements ActionListener {
    JButton jb1 = new JButton();
    JButton jb2 = new JButton();
    private int key = 0;
    GameOverDialog(int grade) {
        this.setTitle("");
        this.setVisible(true);
        this.setLocation(140,430);
        this.setSize(200,100);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p.setLayout(new BorderLayout());
        JLabel jLabel = new JLabel("你的分数是：" + grade + "");
        jb1 = new JButton("重新开始");
        jb2 = new JButton("退出");
        contentPane.add(jLabel,BorderLayout.NORTH);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        p1.add(jb1);
        p2.add(jb2);
        p.add(p1,BorderLayout.WEST);
        p.add(p2,BorderLayout.EAST);
        contentPane.add(p,BorderLayout.CENTER);
        //center 居中
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            this.setVisible(false);

        } else if (e.getSource() == jb2) {
            //传输数据

            //结束进程
            System.exit(0);
        }
    }
}
