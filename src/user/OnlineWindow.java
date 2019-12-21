package user;

import user.resource.MapDate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OnlineWindow extends JFrame {
    Image map;
    JButton roll;
    JList<String> tools;
    JButton useTools;
    MapDate mapDate;
    public OnlineWindow(){
        setTitle("飞行棋");
        setBounds(300, 0, 650, 300);
        setPreferredSize(new Dimension(1200,1000));
        Container containPane=getContentPane();
        containPane.setLayout(new GridLayout(1,2));
        mapDate = new MapDate();
        ImagePanel map=new ImagePanel(mapDate);
        containPane.add(map);


        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
//    public static void main(String args[]){
//        new OnlineWindow();
//    }
}
