package user.AI;

import user.gameContent.Piece;

import java.util.Dictionary;

public class AIFactory {
    public static AI getAI(String setting, Piece[] AIPieces, Dictionary<Integer,String> weather){
        if(setting=="good"){
            return new KindAI(AIPieces,weather);
        }else{
            return new BadAI(AIPieces,weather);
        }
    }
}
