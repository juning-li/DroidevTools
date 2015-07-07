package cn.lee.tools.crash;

import android.content.Context;
import android.os.Build;

import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.lee.tools.AppUtils;

public abstract class BaseCrashHandler extends AbstractCrashHandler {

    SimpleDateFormat mDateFormatter;

    public BaseCrashHandler(Context context, UncaughtExceptionHandler defaultHandler) {
        super(context, defaultHandler);
        mDateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS");
    }

    @Override
    public String getEnvironmentVariables() {
        StringBuilder env = new StringBuilder();
        Context context = getContext();
        // 搜集包名
        if (context != null) {
            env.append(context.getPackageName()).append(BR);
        }
        // 搜集发生日期
        env.append(mDateFormatter.format(new Date(getExceptionTime()))).append(BR);
        // 搜集应用版本
        if (context != null) {
            env.append("versionName:").append(AppUtils.getVersionName(context));
            env.append("versionCode:").append(AppUtils.getVersionCode(context));
            env.append(BR);
        }
        // 搜集设备指纹
        env.append(Build.FINGERPRINT).append(BR);
        return env.toString();
    }
}
