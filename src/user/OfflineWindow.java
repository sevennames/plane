package user;

import user.AI.AI;
import user.AI.AIFactory;
import user.gameContent.Piece;
import user.gameContent.PieceState;
import user.gameLuncher.Counting;
import user.gameLuncher.NotifyBox;
import user.resource.MapDate;

import javax.lang.model.element.PackageElement;
import javax.swing.*;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class OfflineWindow extends JFrame implements Observer {
    AI ai1;
    AI ai2;
    AI ai3;
    AI myAI;
    Piece[] myPiece;
    List<Piece> allPiece;
    Dictionary<Integer,String> weather;
    int points=0;
    NotifyBox messageBox=new NotifyBox();
    Counting counting;
    ImagePanel map;
    MapDate mapDate;
    int vFix = -35;
    int hFix = -10;
    int rFix = 3;

    public OfflineWindow(String mycolor,String AI1,String AI2,String AI3,String myAI){
        setTitle("飞行棋");
        setBounds(300, 0, 650, 300);
        setPreferredSize(new Dimension(1200,1000));
        Container containPane=getContentPane();
        containPane.setLayout(new GridLayout(1,2));
        mapDate = new MapDate();
        map=new ImagePanel(mapDate);

        containPane.add(map);//放入图像化窗体
        Piece[] bluePieces=Piece.getPieces("blue");
        Piece[] redPieces=Piece.getPieces("red");
        Piece[] yellowPieces=Piece.getPieces("yellow");
        Piece[] greenPieces=Piece.getPieces("green");
        allPiece = new ArrayList<>();
        Collections.addAll(allPiece, bluePieces);
        Collections.addAll(allPiece, redPieces);
        Collections.addAll(allPiece, yellowPieces);
        Collections.addAll(allPiece, greenPieces);
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
        map.setAllPiece(allPiece);
        switch (mycolor){
            case "蓝色":
                myPiece=bluePieces;
                ai1= AIFactory.getAI(AI1,redPieces,weather);
                ai2=AIFactory.getAI(AI2,yellowPieces,weather);
                ai3=AIFactory.getAI(AI3,greenPieces,weather);
                ai1.setPofOthers(myPiece, yellowPieces,greenPieces );
                ai2.setPofOthers(myPiece, redPieces, greenPieces);
                ai3.setPofOthers(myPiece, redPieces, yellowPieces);
                break;
            case "红色":
                myPiece=redPieces;
                ai1= AIFactory.getAI(AI1,yellowPieces,weather);
                ai2=AIFactory.getAI(AI2,greenPieces,weather);
                ai3=AIFactory.getAI(AI3,bluePieces,weather);
                ai1.setPofOthers(myPiece, greenPieces,bluePieces );
                ai2.setPofOthers(myPiece, yellowPieces, bluePieces);
                ai3.setPofOthers(myPiece, yellowPieces, greenPieces);
                break;
            case "黄色":
                myPiece=yellowPieces;
                ai1= AIFactory.getAI(AI1,greenPieces,weather);
                ai2=AIFactory.getAI(AI2,bluePieces,weather);
                ai3=AIFactory.getAI(AI3,redPieces,weather);
                ai1.setPofOthers(myPiece, bluePieces, redPieces);
                ai2.setPofOthers(myPiece, greenPieces, redPieces);
                ai3.setPofOthers(myPiece, greenPieces, bluePieces);
                break;
            case "绿色":
                myPiece=greenPieces;
                ai1= AIFactory.getAI(AI1,bluePieces,weather);
                ai2=AIFactory.getAI(AI2,redPieces,weather);
                ai3=AIFactory.getAI(AI3,yellowPieces,weather);
                ai1.setPofOthers(myPiece, redPieces, yellowPieces);
                ai2.setPofOthers(myPiece, bluePieces, yellowPieces);
                ai3.setPofOthers(myPiece, bluePieces, redPieces);
                break;
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
                showcolor.setText("你roll的点数是"+points+"\n选择移动的飞机");
            }
        });
        control.add(showcolor);
        control.add(counting);
        control.add(roll);
//        (new Thread(counting)).start();
        containPane.add(control);


        //以下为放入图片控制窗体
        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //得到鼠标坐标;
                int x=e.getX();
                int y=e.getY();
                int movePiece = -1;
                Position position;
                for (int i=0;i<4;i++){
                    Piece piece = myPiece[i];
                    if (piece.getState().equals(PieceState.Stay)||piece.getAbsolutePosition()==-1){
                        position = mapDate.getAirports(piece.getColor(),piece.getOrder());
                    }else if (piece.getState().equals(PieceState.Ready)){
                        position = mapDate.getStart(piece.getColor());
                    }else {
                        position = mapDate.getRoad(piece.getAbsolutePosition());
                    }
                    int deltaX = position.getX()+hFix - x;
                    int deltaY = position.getY()+vFix - y;
                    if (deltaX*deltaX<(mapDate.getWeight()+rFix)*(mapDate.getWeight()+rFix)&&
                    deltaY*deltaY<(mapDate.getHeight()+rFix)*(mapDate.getHeight()+rFix)){
                        movePiece = i;
                        break;
                    }
                }
                //判断是哪个棋子，这边就先第一个棋子搞定
                if(points!=0 && movePiece!=-1){
                    myPiece[movePiece].move(points);
                    doInteraction(0);
//                    messageBox.setOrder("OfflineWindow: Stop Counting");
//                    messageBox.notifyObservers();
                    points=0;
                    map.repaint();
                    //map.play
                    showcolor.setText("等待其他人操作");
                    ai1.decision(((int) (Math.random()*6))+1);
                    doInteraction(1);
                    //map.play()
                    ai2.decision(((int) (Math.random()*6))+1);
                    doInteraction(2);
                    //map.play()
                    ai3.decision(((int) (Math.random()*6))+1);
                    doInteraction(3);
                    //map.play();
                    showcolor.setText("你的回合了，请roll点");
//                    (new Thread(counting)).start();
                }
            }
        });
        pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        int oo=0;
        while (oo<4){
            gameloop();
        }
    }

    private void gameloop(){
        myPiece[0].move(1);
        System.out.println(myPiece[0].getPosition());
        System.out.println(myPiece[0].getState());
        map.repaint();
        update(getGraphics());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    private synchronized void doInteraction(int movingPlayer){
        int pilecount=0;
        switch(movingPlayer){
            case 0:
                for(Piece mp:myPiece){
                    for(Piece api:ai1.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    for(Piece api:ai2.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    for(Piece api:ai3.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    if(pilecount>1){
                        mp.pinnedDown();
                    }
                }
                break;
            case 1:
                for(Piece mp:ai1.getMyPiece()){
                    for(Piece api:myPiece){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    for(Piece api:ai2.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    for(Piece api:ai3.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    if(pilecount>1){
                        mp.pinnedDown();
                    }
                }
                break;
            case 2:
                for(Piece mp:ai2.getMyPiece()){
                    for(Piece api:myPiece){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    for(Piece api:ai1.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    for(Piece api:ai3.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    if(pilecount>1){
                        mp.pinnedDown();
                    }
                }
                break;
            case 3:
                for(Piece mp:ai3.getMyPiece()){
                    for(Piece api:myPiece){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    for(Piece api:ai1.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    for(Piece api:ai2.getMyPiece()){
                        if(api.getAbsolutePosition()==mp.getAbsolutePosition()){
                            pilecount++;
                            api.pinnedDown();
                        }
                    }
                    if(pilecount>1){
                        mp.pinnedDown();
                    }
                }
                break;
        }
    }
}
