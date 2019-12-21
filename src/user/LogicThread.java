package user;

import user.AI.AI;
import user.gameContent.Piece;
import user.gameLuncher.NotifyBox;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class LogicThread implements Observer{

        AI ai1;
        AI ai2;
        AI ai3;
        AI myai;
        Piece myPiece[];
        JLabel title;
        int points;
        int Pieceorder;

        boolean haschanged=false;
        String orders;
        NotifyBox messageBox;

        public void setPieceorder(int pieceorder) {
            Pieceorder = pieceorder;
        }

        public void setPoints(int points) {
            this.points = points;
        }
        public void addListener(Observer o){
            messageBox.addObserver(o);
        }
        public LogicThread(AI ai1, AI ai2, AI ai3, AI myai, JLabel title){
            this.ai1=ai1;
            this.ai2=ai2;
            this.ai3=ai3;
            this.myai=myai;
            this.title=title;
            myPiece=myai.getMyPiece();
            messageBox=new NotifyBox();

        }
        @Override
        public void update(Observable o, Object arg) {//自身更新
            orders=((NotifyBox)o).getOrder();
            if(orders=="OfflineWindow: User has moved"){
                haschanged=false;
                title.setText("您roll到的点数是"+points);
                myPiece[Pieceorder].move(points);
                doInteraction(0);
                messageBox.setOrder("LogicThread: User has moved");
                messageBox.notifyObservers();
            }else if(orders=="ImagePanel: User has moved"){
                haschanged=false;
                title.setText("等待对手操作");
                ai1.decision((int)((Math.random()*6)+1));
                doInteraction(1);
                messageBox.setOrder("LogicThread: ai1 has moved");
                messageBox.notifyObservers();
            }else if(orders=="ImagePanel: ai1 has moved"){
                haschanged=false;
                ai2.decision((int)((Math.random()*6)+1));
                doInteraction(2);
                messageBox.setOrder("LogicThread: ai2 has moved");
                messageBox.notifyObservers();
            }else if(orders=="ImagePanel: ai2 has moved"){
                haschanged=false;
                ai3.decision((int)((Math.random()*6)+1));
                doInteraction(3);
                messageBox.setOrder("LogicThread: ai3 has moved");
                messageBox.notifyObservers();
            }else if(orders=="OfflineWindow: EXIT"){
                //结束该线程
            }else{
                haschanged=false;
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
