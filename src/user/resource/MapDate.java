package user.resource;

import user.util.ResourceLoader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MapDate {
    int weight;
    int height;
    HashMap<String,Position>road;
    HashMap<String,Position>start;
    HashMap<String,HashMap<String,Position>>airports;
    static class Position{
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    static class StatementProcess{
        private final String end = "end";
        private final String start = ":";
        private final String endLine = ";";
        private final String delimiter = ","; //分隔符
        private final String subStart = "{";
        private final String subEnd = "}";

        private int startPos;
        private int delimiterPos;
        String statement;
        String key;
        Object value;

        public StatementProcess() {
        }

        public StatementProcess(String statement) {
            this.statement = statement;
        }

        public StatementProcess(String statement, String key) {
            this.statement = statement;
            this.key = key;
        }

        public void process(){
            startPos = statement.indexOf(start);
            delimiterPos = statement.indexOf(delimiter);
            key = statement.substring(0,startPos);
            if (delimiterPos==-1){
                value = Integer.valueOf(statement.substring(startPos+1,statement.indexOf(endLine)));
            } else {
                Position tmpValue = new Position(
                        Integer.valueOf(statement.substring(startPos+1,delimiterPos)),
                        Integer.valueOf(statement.substring(delimiterPos+1,statement.indexOf(endLine)))
                        );
                value = tmpValue;
            }
        }

        public void setStatement(String statement) {
            this.statement = statement;
            process();
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

    }

    private void initialize(){
        String readLine;
        String mapInfoList="weight,height,road,start,airports";

        StatementProcess sp = new StatementProcess();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        ResourceLoader.load(MapDate.class,"not/exist","map.txt"))
        );
        try {
            sp.setStatement(bufferedReader.readLine());
            weight = (int) sp.getValue();
            sp.setStatement(bufferedReader.readLine());
            height = (int) sp.getValue();
            bufferedReader.readLine();
            road = new HashMap<>();
            for (int i=0;i<76;i++){
                sp.setStatement(bufferedReader.readLine());
                road.put(sp.getKey(),(Position)sp.getValue());
            }
            bufferedReader.readLine();
            start = new HashMap<>();
            for (int i=0;i<4;i++){
                sp.setStatement(bufferedReader.readLine());
                start.put(sp.getKey(),(Position)sp.getValue());
            }
            bufferedReader.readLine();
            airports = new HashMap<>();
            bufferedReader.readLine();
            airports.put("blue",new HashMap<String, Position>());
            for (int i=0;i<4;i++){
                sp.setStatement(bufferedReader.readLine());
                airports.get("blue").put(sp.getKey(),(Position)sp.getValue());
            }
            bufferedReader.readLine();
            airports.put("green",new HashMap<String, Position>());
            for (int i=0;i<4;i++){
                sp.setStatement(bufferedReader.readLine());
                airports.get("green").put(sp.getKey(),(Position)sp.getValue());
            }
            bufferedReader.readLine();
            airports.put("yellow",new HashMap<String, Position>());
            for (int i=0;i<4;i++){
                sp.setStatement(bufferedReader.readLine());
                airports.get("yellow").put(sp.getKey(),(Position)sp.getValue());
            }
            bufferedReader.readLine();
            airports.put("red",new HashMap<String, Position>());
            for (int i=0;i<4;i++){
                sp.setStatement(bufferedReader.readLine());
                airports.get("red").put(sp.getKey(),(Position)sp.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public MapDate() {
        initialize();
    }

    public static void main(String[] args) {
        MapDate m = new MapDate();
        System.out.println(m.getStart().get("blue"));
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public HashMap<String, Position> getRoad() {
        return road;
    }

    public HashMap<String, Position> getStart() {
        return start;
    }

    public HashMap<String, HashMap<String, Position>> getAirports() {
        return airports;
    }
}
