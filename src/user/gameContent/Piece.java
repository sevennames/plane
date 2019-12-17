package user.gameContent;

import java.util.Dictionary;

public class Piece {
    int position;
    PieceState state;
    String color;
    Dictionary<Integer,String> weather;//记录地图上的各种效果，例如rainstorm就是经过这个格子的飞机随机飞到某一个地方

    public Piece(String color){
        this.color=color;
        position=-1;
        state=PieceState.Stay;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setState(PieceState state) {
        this.state = state;
    }

    public int getPosition() {
        return position;
    }

    public PieceState getState() {
        return state;
    }

    public String getColor() {
        return color;
    }

    public double getDir() {//根据绝对坐标，计算旗子的朝向

        return 0;
    }

    public int getAbsolutePosition(){//绝对坐标是以蓝色的起点为0，逆时针数的序数，对于四条前往重点的路，分别是：蓝色（101~106），红色（111~116）
                                        // 黄色（121~126），绿色（131~136）
        switch (color){
            case "blue":
                if(position<=49){
                    return position;
                }else{
                    return position-49+100;
                }
            case "red":
                if(position<=49){
                    return (position+13)%52;
                }else{
                    return position-49+110;
                }
            case "yellow":
                if(position<49){
                    return (position+26)%52;
                }else{
                    return position-49+120;
                }
            case "green":
                if(position<49){
                    return (position+39)%52;
                }else{
                    return position-49+130;
                }
        }
        return 0;
    }



    private void moveByStep(){
        if(position<55){
            position++;
        }else{
            position--;
        }
    }

    public void move(int n){
        //先看飞机棋子能否动，能动设为flying
        if(state==PieceState.Ready||state==PieceState.Pause){
            setState(PieceState.Flying);
        }
        //先按照点数移动棋子，同时每一次移动判断一下到达点的天气情况
        for(int i=0;i<n;i++){
            if(state==PieceState.Flying){
                moveByStep();
                Effection.seteffection(weather.get(getAbsolutePosition()),this);
            }
        }
        //判断是否到达可以横跨的格子
        //经过横跨的格子的时候如果经过前往终点的路上的格子应该是要能对那格子上面的飞机有击毁效果的
        if(position==17&&state==PieceState.Flying){
            position=29;
        }
        //判断到达的位置是否为同色，同色则向前到下一个同色的格子
        if(position%4==1 && position!=49 &&state==PieceState.Flying){
            position+=4;
        }
        //回到停留状态，或者抵达终点
        if(position-100>0&&position%10==6){
            setState(PieceState.CompeleteMission);
        }else{
            setState(PieceState.Pause);
        }
    }
    public void fallBack(int n){//用于导弹等道具对其的作用
        position-=n;
        if(position<0){
            position=0;
        }
    }
    public Piece clone(){
        return new Piece(color);
    }
}
