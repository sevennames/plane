package user;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        try{
            Image map= ImageIO.read(new File("C:\\Users\\13276\\Desktop\\飞行棋\\src\\user\\map.jpg"));
            g.drawImage(map,0,0,650,650,null);
        }catch (IOException ioe){
            System.out.println("no image");
        }
    }
}
