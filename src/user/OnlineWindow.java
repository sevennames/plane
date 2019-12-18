package user;

import javax.swing.*;
import java.awt.*;

public class OnlineWindow extends JFrame {
    Image map;
    JButton roll;
    JList<String> tools;
    JButton useTools;

    public OnlineWindow(){
        setTitle("飞行棋");
        setBounds(300, 0, 650, 300);
        setPreferredSize(new Dimension(1200,1000));
        Container containPane=getContentPane();
        containPane.setLayout(new GridLayout(1,2));

        ImagePanel map=new ImagePanel();
        containPane.add(map);


        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String args[]){
        new OnlineWindow();
    }
}
