package user;

import user.AI.AI;
import user.AI.AIFactory;
import user.gameContent.Piece;
import user.gameLuncher.Counting;
import user.gameLuncher.NotifyBox;

import javax.lang.model.element.PackageElement;
import javax.swing.*;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

public class OfflineWindow extends JFrame implements Observer {
    AI ai1;
    AI ai2;
    AI ai3;
    AI myAI;
    Piece[] myPiece;
    Dictionary<Integer,String> weather;
    int points=0;
    NotifyBox messageBox=new NotifyBox();
    Counting counting;
    public OfflineWindow(String mycolor,String AI1,String AI2,String AI3,String myAI){
        setTitle("飞行棋");
        setBounds(300, 0, 650, 300);
        setPreferredSize(new Dimension(1200,1000));
        Container containPane=getContentPane();
        containPane.setLayout(new GridLayout(1,2));

        ImagePanel map=new ImagePanel();

        containPane.add(map);//放入图像化窗体

        Piece[] bluePieces=Piece.getPieces("blue");
        Piece[] redPieces=Piece.getPieces("red");
        Piece[] yellowPieces=Piece.getPieces("yellow");
        Piece[] greenPieces=Piece.getPieces("green");
        weather=new Dictionary<Integer, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Enumeration<Integer> keys() {
                return null;
            }

            @Override
            public Enumeration<String> elements() {
                return null;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(Integer key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }
        };


        switch (mycolor){
            case "蓝色":
                myPiece=bluePieces;
                ai1= AIFactory.getAI(AI1,redPieces,weather);
                ai2=AIFactory.getAI(AI2,yellowPieces,weather);
                ai3=AIFactory.getAI(AI3,greenPieces,weather);
            case "红色":
                myPiece=redPieces;
                ai1= AIFactory.getAI(AI1,yellowPieces,weather);
                ai2=AIFactory.getAI(AI2,greenPieces,weather);
                ai3=AIFactory.getAI(AI3,bluePieces,weather);
            case "黄色":
                myPiece=yellowPieces;
                ai1= AIFactory.getAI(AI1,greenPieces,weather);
                ai2=AIFactory.getAI(AI2,bluePieces,weather);
                ai3=AIFactory.getAI(AI3,redPieces,weather);
            case "绿色":
                myPiece=greenPieces;
                ai1= AIFactory.getAI(AI1,bluePieces,weather);
                ai2=AIFactory.getAI(AI2,redPieces,weather);
                ai3=AIFactory.getAI(AI3,yellowPieces,weather);
        }


        //放入按钮控制窗体
        JPanel control=new JPanel();
        control.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
        control.setLayout(new GridLayout(4,1));


        JLabel showcolor=new JLabel("你的回合了，请roll点",SwingConstants.CENTER);
        showcolor.setFont(new Font("黑体",1,21));
        counting=new Counting(messageBox);
        JButton roll=new JButton("Roll!");
        roll.setPreferredSize(new Dimension(1,2));
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                points=((int) (Math.random()*6))+1;
                showcolor.setText("选择移动的飞机");
            }
        });
        control.add(showcolor);
        control.add(counting);
        control.add(roll);
        (new Thread(counting)).start();
        containPane.add(control);


        //以下为放入图片控制窗体
        map.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //得到鼠标坐标;
                int x=e.getX();
                int y=e.getY();
                //判断是哪个棋子，这边就先第一个棋子搞定
                if(points!=0){
                    myPiece[0].move(points);
                    messageBox.setOrder("OfflineWindow: Stop Counting");
                    messageBox.notifyObservers();
                    points=0;
                    showcolor.setText("等待其他人操作");
                    ai1.decision(((int) (Math.random()*6))+1);
                    //map.play()
                    ai2.decision(((int) (Math.random()*6))+1);
                    //map.play()
                    ai3.decision(((int) (Math.random()*6))+1);
                    //map.play();
                    showcolor.setText("你的回合了，请roll点");
                    (new Thread(counting)).start();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        String Order=((NotifyBox)o).getOrder();
        if(Order=="Counting: run myAI"){
            points=((int) (Math.random()*6))+1;
            //播放扔骰子动画
            //播放骰子图像
            myAI.decision(points);
            //播放移动动画
            ai1.decision(((int) (Math.random()*6))+1);
            ai2.decision(((int) (Math.random()*6))+1);
            ai3.decision(((int) (Math.random()*6))+1);
        }
    }
}
