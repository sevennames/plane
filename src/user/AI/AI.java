package user.AI;

import user.gameContent.Piece;
import user.gameContent.PieceState;

import java.util.ArrayList;
import java.util.Dictionary;

public abstract class AI {
    boolean hasmoved=false;
    Piece[] myPiece;
    Piece[] pof1;
    Piece[] pof2;
    Piece[] pof3;
    Dictionary<Integer,String> weather;//记录地图上的各种效果，例如rainstorm就是经过这个格子的飞机随机飞到某一个地方
    ArrayList<String> tools;

    public void setPof1(Piece[] pof1) {
        this.pof1 = pof1;
    }

    public void setPof2(Piece[] pof2) {
        this.pof2 = pof2;
    }

    public void setPof3(Piece[] pof3) {
        this.pof3 = pof3;
    }

    public void addTools(String[] tools) {
        for(String t:tools){
            this.tools.add(t);
        }
    }
    public void addTools(String tools){
        this.tools.add(tools);
    }

    public boolean canmove(int points){
        if(points==6){
            return true;
        }
        for(Piece myp:myPiece){
            if(myp.getState()== PieceState.Ready||myp.getState()==PieceState.Pause){
                return true;
            }
        }
        return false;
    }
    public abstract void decision(int points);

}
