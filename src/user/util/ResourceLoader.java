package user.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceLoader {
    public static InputStream load(Class<?> clazz,String filepath,String resPath){
        InputStream inputStream = null;
        if (!( resPath == null) || resPath.isEmpty()){
            inputStream = clazz.getResourceAsStream(resPath);
        }
        if (inputStream == null){
            try {
                inputStream = new FileInputStream(filepath);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        return inputStream;
    }

}
