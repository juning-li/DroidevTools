package cn.lee.tools.crash;

import android.content.Context;

import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.lee.tools.EmailUtils;

/**
 * 邮件式（异常信息发送至指定邮箱）异常处理<br/>
 * 需要"android.permission.INTERNET"权限<br/>
 *
 * @author Juning.li
 * @since 2014-06-08
 */
public class EmailCrashHandler extends BaseCrashHandler {

    private final String mTargetEmailAddr;
    private final SimpleDateFormat mDateFormatter;
    private String mEmail;
    private String mPassword;

    public EmailCrashHandler(Context context, UncaughtExceptionHandler defaultHandler, String from, String password, String to) {
        super(context, defaultHandler);

        mDateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        mTargetEmailAddr = to;
        mEmail = from;
        mPassword = password;
    }

    @Override
    public void sendExceptionToTarget(String exceptionString) {
        EmailUtils.sendEmailFrom163(mEmail, mPassword, mTargetEmailAddr, "BUG报告（" + mDateFormatter.format(new Date(System.currentTimeMillis()) + "）"), exceptionString);
    }
}
