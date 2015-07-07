package cn.lee.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.View;

public class AppUtils {

    /**
     * 换行符
     */
    private static final String BR = "\r\n";

    /**
     * 获取应用的VersionName
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取应用的VersionCode
     */
    public static int getVersionCode(Context context) {
        int versionCode = -1;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 为View设置可见性
     */
    public static void setVisibility(View v, int vis) {
        if (v != null && v.getVisibility() != vis) {
            v.setVisibility(vis);
        }
    }

    /**
     * 获取手机屏幕的信息，如大小和密度等
     */
    public static String getScreenInformation(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("我的手机：").append(BR);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        sb.append("屏幕宽度：").append(dm.widthPixels).append("像素").append(BR);
        sb.append("屏幕高度：").append(dm.heightPixels).append("像素").append(BR);
        sb.append("屏幕密度：").append(dm.density).append(BR);
        sb.append("X-dpi:").append(dm.xdpi).append(BR);
        sb.append("Y-dpi:").append(dm.ydpi);
        return sb.toString();
    }
}
