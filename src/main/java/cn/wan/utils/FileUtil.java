package cn.wan.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by 万洪基 on 2016/12/13.
 */
public class FileUtil {
    public static File newFile(String filename) throws IOException {
        File file=new File(filename);
        file.createNewFile();
        return file;
    }
}
