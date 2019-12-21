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

import static java.lang.Thread.sleep;

public class OfflineWindow extends JFrame implements Observer {
    AI ai1;
    AI ai2;
    AI ai3;
    AI myAI;
    Piece[] myPiece;
    List<Piece> allPiece;
    Dictionary<Integer,String> weather;
    boolean myturn=true;
    int points=0;
    NotifyBox messageBox=new NotifyBox();
    Counting counting;
    ImagePanel map;
    JLabel information;
    MapDate mapDate;
    int vFix = -35;
    int hFix = -10;
    int rFix = 0;

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
        this.myAI=AIFactory.getAI(myAI,myPiece,weather);

        //放入按钮控制窗体
        JPanel control=new JPanel();
        control.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
        control.setLayout(new GridLayout(4,1));


        JLabel showcolor=new JLabel("你的回合了，请roll点",SwingConstants.CENTER);
        information=showcolor;
        switch (mycolor){
            case "蓝色":
                showcolor.setForeground(Color.blue);
                break;
            case "红色":
                showcolor.setForeground(Color.red);
                break;
            case "黄色":
                showcolor.setForeground(Color.yellow);
                break;
            case "绿色":
                showcolor.setForeground(Color.green);
                break;
        }
        showcolor.setFont(new Font("黑体",1,21));//启动游戏逻辑判断线程
        LogicThread logical=new LogicThread(ai1, ai2, ai3, this.myAI, showcolor);
        messageBox.addObserver(logical);

        JButton roll=new JButton("Roll!");
        roll.setFont(new Font("黑体", 0,50));
        roll.setPreferredSize(new Dimension(1,2));
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myturn==false){
                    return;
                }
                points=(int)((Math.random()*6)+1);
                logical.setPoints(points);
                if(points==6){
                    showcolor.setText("您roll到了"+points+",请选择您要移动的棋子");
                    return;
                }
                for(Piece mp:myPiece){
                    if(mp.getState()!=PieceState.Stay&&mp.getState()!=PieceState.CompeleteMission){
                        showcolor.setText("您roll到了"+points+",请选择您要移动的棋子");
                        return;
                    }
                }

                messageBox.setOrder("OfflineWindow: User has moved");
                messageBox.notifyObservers();
                showcolor.setText("您roll到了"+points+"没有roll到6，您不能行动");
            }
        });
        control.add(showcolor);
        control.add(roll);
        containPane.add(control);


        //以下为放入图片控制窗体
        ImageThread imt=new ImageThread(map,showcolor,myturn);
        logical.addListener(imt);
        imt.addListener(logical);
        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(myturn==false){
                    return;
                }
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
                    int deltaX = position.getX()+hFix - x + mapDate.getHeight()/2;
                    int deltaY = position.getY()+vFix - y + mapDate.getHeight()/2;//计算点击点离哪个棋子最近
                    if (4*deltaX*deltaX<(mapDate.getWeight()+rFix)*(mapDate.getWeight()+rFix)&&
                    4*deltaY*deltaY<(mapDate.getHeight()+rFix)*(mapDate.getHeight()+rFix)){
                        movePiece = i;
                        break;
                    }
                }
                //判断是哪个棋子
                if(points!=0 && movePiece!=-1){
                    if(myPiece[movePiece].getState()==PieceState.Stay&&points!=6){

                    }else if(myPiece[movePiece].getState()==PieceState.CompeleteMission){
                        //不能动则不通知逻辑线程对象
                    }else{
                        logical.setPieceorder(movePiece);
                        logical.setPoints(points);
                        messageBox.setOrder("OfflineWindow: User has moved");
                        messageBox.notifyObservers();
                    }
                }
//                myturn=false;
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
        }else if(Order=="ImageThread: ai3 has moved"){
            information.setText("你的回合了，请roll点");
        }
    }

    private synchronized void doInteraction(int movingPlayer){
        int pilecount=0;
        switch(movingPlayer){
            case 0://玩家动
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
            case 1://ai1动
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
            case 2://ai2动
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
            case 3://ai3动
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
