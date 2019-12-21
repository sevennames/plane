package user.gameLuncher;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Thread.sleep;

public class Counting extends JLabel implements Runnable , Observer {
    NotifyBox messageBox;
    boolean ifContinue=true;
    int secondLeft = 20;
    public Counting(Observable o){
        setText(String.valueOf(secondLeft));
        setHorizontalAlignment(CENTER);
        setFont(new Font("黑体", 2, 30));
        o.addObserver(this);
    }
    @Override
    public void run() {
        if(ifContinue){
            secondLeft -=1;
        }
        try{
            sleep(1000);
        }catch (InterruptedException ie){

        }
        messageBox.setOrder("Counting: run myAI");
        messageBox.notifyObservers();

    }

    @Override
    public void update(Observable o, Object arg) {
        String order=((NotifyBox)o).getOrder();
        if(order=="OfflineWindow: Stop Counting"){
            ifContinue=false;
        }
    }
}
