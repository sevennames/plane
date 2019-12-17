package user.gameContent;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Effection {
    public static void seteffection(String effection,Piece p){
        switch (effection){
            case "rainstorm":
                p.position=((int)(Math.random()*49));
                p.setState(PieceState.Pause);
        }
    }
}
