package user.AI;

import user.gameContent.Piece;
import user.gameContent.PieceState;

import java.util.Dictionary;

public class KindAI extends AI{
    boolean canmove;
    public KindAI(Piece[] p, Dictionary<Integer,String> weathermap){
        canmove=true;
        myPiece=p;
        weather=weathermap;
    }
    @Override
    public void decision(int points) {
        for(Piece myp:myPiece){
            if(myp.getState()==PieceState.CompeleteMission){
                continue;
            }
            if(myp.getState()!= PieceState.Ready&&myp.getState()!=PieceState.Pause){
                if(points==6){
                    if(Math.random()<0.5){
                        myp.setState(PieceState.Ready);
                        hasmoved=true;
                        break;
                    }else {
                        continue;
                    }
                }
            }
            Piece testp=myp.clone();
            testp.move(points);
            for(Piece p:pof1){
                if(testp.getAbsolutePosition()==p.getAbsolutePosition()){
                    canmove=false;
                    break;
                }
            }
            for(Piece p:pof2){
                if(testp.getAbsolutePosition()==p.getAbsolutePosition()){
                    canmove=false;
                    break;
                }
            }
            for(Piece p:pof3){
                if(testp.getAbsolutePosition()!=p.getAbsolutePosition()){
                    canmove=false;
                    break;
                }
            }
            if(canmove==true){
                myp.move(points);
                hasmoved=true;
                break;
            }
            if(hasmoved=true){
                break;
            }
        }
        if(hasmoved==false){//没法善良（每一个能动的棋子动了之后都会干掉别人）,则调动最先能动的一架
            for(Piece myp:myPiece){
                if(myp.getState()==PieceState.Ready||myp.getState()==PieceState.Pause){
                    myp.move(points);
                    break;
                }
                if(myp.getState()==PieceState.Stay&&points==6){
                    myp.setState(PieceState.Ready);
                    break;
                }
            }
        }
        hasmoved=false;
    }
}
