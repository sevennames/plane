package user;

import user.gameContent.Piece;
import user.gameContent.PieceState;
import user.resource.MapDate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ImagePanel extends JPanel {
    MapDate mapDate;
    List<Piece> allPiece;
    List<Piece> lastPiece;
    int vFix = -35;
    int hFix = -10;
    int rFix = 3;
    class PieceDraw{
        Color color;
        int x;
        int y;
        int r;
        Position position;
        public PieceDraw(Piece piece) {
            switch (piece.getColor()){
                case "blue":
                    color = Color.CYAN;
                    break;
                case "yellow":
                    color = Color.yellow;
                    break;
                case "green":
                    color = Color.green;
                    break;
                case "red":
                    color = Color.red;
                    break;
            }
            if (piece.getState().equals(PieceState.Ready)) {
                position = mapDate.getStart(piece.getColor());
            }
            else if (piece.getState().equals(PieceState.Stay)||piece.getPosition()==-1){
                position = mapDate.getAirports(piece.getColor(),piece.getOrder());
            }
            else {
                position = mapDate.getRoad(piece.getAbsolutePosition());
            }
            x = position.getX();
            y = position.getY();
            r = mapDate.getHeight();
        }

        public Color getColor() {
            return color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getR() {
            return r;
        }
    }
    public ImagePanel(MapDate mapDate) {
        this.mapDate = mapDate;
    }
    public void setAllPiece(List allPiece){
        this.allPiece = allPiece;
    }

    @Override
    public void paint(Graphics g) {
        try{
            g.clearRect(0,0,getWidth(),getHeight());
            Image map= ImageIO.read(ImagePanel.class.getResourceAsStream("map.jpg"));
            g.drawImage(map,0,0,650,650,null);
            for (int i=0;i<allPiece.size();i++){
                PieceDraw pieceDraw = new PieceDraw(allPiece.get(i));
                g.setColor(pieceDraw.getColor());
                g.fillOval(pieceDraw.getX()+hFix,pieceDraw.getY()+vFix,pieceDraw.getR(),pieceDraw.getR());
                g.setColor(Color.darkGray);
                g.drawOval(pieceDraw.getX()+hFix,pieceDraw.getY()+vFix,pieceDraw.getR(),pieceDraw.getR());
            }
            System.gc();
        }catch (IOException ioe){
            System.out.println("no image");
        }
    }

}
