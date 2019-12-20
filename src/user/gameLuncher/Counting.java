package user.gameLuncher;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Thread.sleep;

public class Counting extends JLabel implements Runnable , Observer {
    NotifyBox messageBox;
    boolean ifContinue=true;
    public Counting(Observable o){
        setText("20");
        setHorizontalAlignment(CENTER);
        setFont(new Font("黑体", 2, 30));
        o.addObserver(this);
    }
    @Override
    public void run() {
        for(int i=0;i<20;i++){
            if(ifContinue){
                setText(""+(20-i)+"");
            }else{
                ifContinue=true;
                return;
            }
            try{
                sleep(1000);
            }catch (InterruptedException ie){

            }
        }
        messageBox.setOrder("Counting: run myAI");
        messageBox.notifyObservers();

    }

    @Override
    public void update(Observable o, Object arg) {
        String order=((NotifyBox)o).getOrder();
        if(order=="OfflineWindow: Stop Counting"){
            ifContinue=false;
            setText("");
        }
    }
}
