package user;

import user.gameLuncher.NotifyBox;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ImageThread implements Observer {
    ImagePanel ip;
    boolean ifupdate=false;
    String orders;
    NotifyBox messageBox=new NotifyBox();
    JLabel title;
    boolean turn;
    public ImageThread(ImagePanel ip,JLabel title,boolean myturn){
        this.ip=ip;
        this.title=title;
        turn=myturn;
    }

    public void addListener(Observer o){
        messageBox.addObserver(o);
    }

    @Override
    public void update(Observable o, Object arg) {
        orders=((NotifyBox)o).getOrder();
        if(orders=="LogicThread: User has moved"){
            ifupdate=false;
            //ImagePanel画图
            ip.repaint();
            messageBox.setOrder("ImagePanel: User has moved");
            messageBox.notifyObservers();
        }else if(orders=="LogicThread: ai1 has moved"){
            ifupdate=false;
            ip.repaint();
            messageBox.setOrder("ImagePanel: ai1 has moved");
            messageBox.notifyObservers();
        }else if(orders=="LogicThread: ai2 has moved"){
            ifupdate=false;
            ip.repaint();
            messageBox.setOrder("ImagePanel: ai2 has moved");
            messageBox.notifyObservers();
        }else if(orders=="LogicThread: ai3 has moved"){
            ifupdate=false;
            ip.repaint();
            title.setText("到你的回合了，请roll点");
            turn=true;
            messageBox.notifyObservers();
        }else if(orders=="OfflineWindow: EXIT"){
            //结束该线程
        }else {
            ifupdate=false;
        }
    }
}
