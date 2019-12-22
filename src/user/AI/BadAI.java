package user.AI;

import user.gameContent.Piece;
import user.gameContent.PieceState;

import java.util.Dictionary;

public class BadAI extends AI{

    boolean canmove;
    public BadAI(Piece[] p, Dictionary<Integer,String> weathermap){
        canmove=false;
        myPiece=p;
        weather=weathermap;
    }
    @Override
    public void decision(int points) {
        for(Piece myp:myPiece) {
            Piece testp=myp.clone();
            if (myp.getState() == PieceState.CompeleteMission) {
                continue;
            }
            if (myp.getState() != PieceState.Ready && myp.getState() != PieceState.Pause) {//选中的棋子在机场中时
                                                    //试着看变为ready状态后能否有机会干掉其他人的飞机
                if (points == 6) {
                    testp.setState(PieceState.Ready);
                    testp.move(1);
                    for(Piece p:pof1){
                        if(p.getAbsolutePosition()-testp.getAbsolutePosition()<6){
                            canmove=true;
                        }
                    }
                    for(Piece p:pof2){
                        if(p.getAbsolutePosition()-testp.getAbsolutePosition()<6){
                            canmove=true;
                        }
                    }
                    for(Piece p:pof3){
                        if(p.getAbsolutePosition()-testp.getAbsolutePosition()<6){
                            canmove=true;
                        }
                    }
                    if(canmove==true){
                        myp.setState(PieceState.Ready);
                        System.out.println("badai "+myPiece[0].getColor()+" set"+myp.getOrder()+" Ready");//生成报告用
                        hasmoved=true;
                        break;
                    }else {
                        continue;//如果没机会缺德到别人，就不起飞这架飞机了,找下一个棋子
                    }
                }
                continue;//点数不为六，stay状态的飞机动不了，找下一个棋子
            }
            //进入这里则一定是可以移动的棋子
            testp.move(points);
            for(Piece p:pof1){
                if(testp.getAbsolutePosition()==p.getAbsolutePosition()){
                    myp.move(points);
                    System.out.println("badai "+myPiece[0].getColor()+" move"+myp.getOrder()+" "+points);//生成报告用
                    hasmoved=true;
                    break;
                }
            }
            if(hasmoved==true){
                break;
            }
            for(Piece p:pof2){
                if(testp.getAbsolutePosition()==p.getAbsolutePosition()){
                    myp.move(points);
                    System.out.println("badai "+myPiece[0].getColor()+" move"+myp.getOrder()+" "+points);//生成报告用
                    hasmoved=true;
                    break;
                }
            }
            if(hasmoved==true){
                break;
            }
            for(Piece p:pof3){
                if(testp.getAbsolutePosition()==p.getAbsolutePosition()){
                    myp.move(points);
                    System.out.println("badai "+myPiece[0].getColor()+" move"+myp.getOrder()+" "+points);//生成报告用
                    hasmoved=true;
                    break;
                }
            }
            if(hasmoved==true){
                break;
            }
        }
        if(hasmoved==false){
            for(Piece myp:myPiece){//没法缺德到别人,则调动最先能动的一架
                if(myp.getState()==PieceState.Ready||myp.getState()==PieceState.Pause){
                    myp.move(points);
                    System.out.println("badai "+myPiece[0].getColor()+" move"+myp.getOrder()+" "+points);//生成报告用
                    break;
                }
                if(myp.getState()==PieceState.Stay&&points==6){
                    myp.setState(PieceState.Ready);
                    System.out.println("badai "+myPiece[0].getColor()+" set"+myp.getOrder()+" Ready");//生成报告用
                    break;
                }
            }
        }
        if(!hasmoved){
            System.out.println("badai "+myPiece[0].getColor()+" get"+points+" but can do nothing");
        }
        hasmoved=false;
    }
}
