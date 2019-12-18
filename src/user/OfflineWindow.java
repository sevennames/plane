package user;

import user.AI.AI;
import user.gameContent.Piece;

import javax.lang.model.element.PackageElement;
import javax.swing.*;
import java.awt.*;

public class OfflineWindow extends JFrame {
    AI ai1;
    AI ai2;
    AI ai3;
    Piece[] myPiece;
    public OfflineWindow(){
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
}
