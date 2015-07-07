package cn.lee.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class FileUtils {

    private static final String[] SIZE_LEVELS = {"B", "KB", "MB", "GB", "TB"};

    /**
     * 确保文件/文件夹存在
     */
    public static void ensureFileExists(String path, boolean isDir) {
        File f = new File(path);
        ensureFileExists(f, isDir);
    }

    /**
     * 确保文件/文件夹存在
     */
    public static void ensureFileExists(File f, boolean isDir) {
        if (f != null && !f.exists()) {
            if (isDir) {
                f.mkdirs();
            } else {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将文本保存至文件
     */
    public static void saveString2file(File outFile, String string) {
        if (outFile != null) {
            ensureFileExists(outFile, false);
            BufferedOutputStream out = null;
            try {
                out = new BufferedOutputStream(new FileOutputStream(outFile));
                out.write(string.getBytes());
                out.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
            }
        }
    }

    public static String getFileSize(long sizeInByte) {
        float t = sizeInByte;
        int i;
        for (i = 0; i < SIZE_LEVELS.length; i++) {
            if (t / 1024 >= 1) {
                t = t / 1024;
            } else {
                break;
            }
        }
        DecimalFormat fnum = new DecimalFormat("##0.00");
        return fnum.format(t) + SIZE_LEVELS[i];
    }
}
