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
        setBounds(650, 300, 650, 300);
        setPreferredSize(new Dimension(650,300));
        Container containPane=getContentPane();
        containPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();//建立窗口

        try{
            map=ImageIO.read(new File("map.jpg"));
            JPanel image=new JPanel(){
                public void paint(Graphics2D g){
                    g.drawImage(map,0,0,200,200,null);
                }
            };
            containPane.add(image);
        }catch(IOException ioe){
            System.out.println("打开文件出现问题");
        }


        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
