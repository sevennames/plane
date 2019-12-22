package user;

import user.gameLuncher.NotifyBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javafx.application.Platform.exit;

public class GameEnd extends JFrame {
    NotifyBox messageBox=new NotifyBox();
    public GameEnd(String winner){
        setTitle("飞行棋");
        setBounds(300, 650, 650, 300);
        setPreferredSize(new Dimension(500,300));
        Container containPane=getContentPane();
        containPane.setLayout(new GridLayout(2,1));

        JLabel s=new JLabel(winner+"获得了胜利！");
        containPane.add(s);

        JPanel bottonP=new JPanel();
        JButton goon=new JButton("继续游戏");
        JButton exit=new JButton("退出");
        JButton back=new JButton("回到难度选择");
        goon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageBox.setOrder("GameEnd: back to AIChooser");
                messageBox.notifyObservers();
                dispose();
            }
        });


        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
