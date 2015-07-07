package cn.lee.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CrashUtils {

    /**
     * 取得Exception的文本信息
     */
    public static String getExceptionAsString(Throwable tr) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            tr.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e) {
            // ignore
        }
        return null;
    }
}
