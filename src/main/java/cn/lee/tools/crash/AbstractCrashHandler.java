package cn.lee.tools.crash;

import android.content.Context;

import java.lang.Thread.UncaughtExceptionHandler;

import cn.lee.tools.CrashUtils;

/**
 * 异常处理的虚类
 *
 * @author Juning.li
 * @since 2014-05-28
 */
public abstract class AbstractCrashHandler implements UncaughtExceptionHandler {

    public static final String BR = "\r\n";
    private Context mContext;
    private long mExceptionTime;
    private String mExceptionName;
    private UncaughtExceptionHandler mDefaultExceptionHandler;
    private boolean mInvokeDefault = true;

    public AbstractCrashHandler(Context context, UncaughtExceptionHandler defaultHandler) {
        mContext = context;
        mDefaultExceptionHandler = defaultHandler;
    }

    protected Context getContext() {
        return mContext;
    }

    /**
     * 异常发生时间
     */
    public long getExceptionTime() {
        return mExceptionTime;
    }

    /**
     * 异常名称
     */
    public String getExceptionName() {
        return mExceptionName;
    }

    public boolean getInvokeDefault() {
        return mInvokeDefault;
    }

    /**
     * 是否在结尾调用默认Handler处理
     */
    public void setInvokeDefault(boolean invoke) {
        mInvokeDefault = invoke;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // 发生时间
        mExceptionTime = System.currentTimeMillis();
        // 异常名称
        mExceptionName = ex.getClass().getName();
        // 开始收集信息
        StringBuilder exceptionStrBuilder = new StringBuilder();
        // 环境信息
        exceptionStrBuilder.append(getEnvironmentVariables()).append(BR);
        // 异常信息
        exceptionStrBuilder.append(CrashUtils.getExceptionAsString(ex)).append(BR);
        // 发送
        sendExceptionToTarget(exceptionStrBuilder.toString());
        // 默认处理
        if (mInvokeDefault && mDefaultExceptionHandler != null) {
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    }

    /**
     * 收集异常发生时的环境信息
     */
    public abstract String getEnvironmentVariables();

    /**
     * 将异常信息发送至目的地
     */
    public abstract void sendExceptionToTarget(String exceptionString);
}
