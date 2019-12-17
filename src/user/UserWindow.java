package user;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class UserWindow extends JFrame {
    Image map;
    JButton roll;
    JList<String> tools;
    JButton useTools;

    public UserWindow(){
        setTitle("飞行棋");
        setBounds(300, 0, 650, 300);
        setPreferredSize(new Dimension(1200,1000));
        Container containPane=getContentPane();
        containPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();//建立窗口

        ImagePanel map=new ImagePanel();
        c.ipadx=0;
        c.ipady=0;
        c.gridwidth=650;
        c.gridheight=650;
        containPane.add(map,c);


        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String args[]){
        new UserWindow();
    }
}
