package cn.lee.tools.crash;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.lee.tools.FileUtils;

/**
 * 文件式（异常信息保存成文件）异常处理<br/>
 * 需要"android.permission.WRITE_EXTERNAL_STORAGE"权限<br/>
 *
 * @author Juning.li
 * @since 2014-05-28
 */
public class FileCrashHandler extends BaseCrashHandler {

    private final String mBasePath;
    private SimpleDateFormat mDateFormatterWithoutYear;

    public FileCrashHandler(Context context, UncaughtExceptionHandler defaultHandler, String basePath) {
        super(context, defaultHandler);
        if (!TextUtils.isEmpty(basePath)) {
            mBasePath = basePath;
        } else {
            // 设默认存储路径
            mBasePath = Environment.getExternalStorageDirectory() + "/CrashReports/" + (context == null ? "" : context.getPackageName());
        }
        mDateFormatterWithoutYear = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void sendExceptionToTarget(String exceptionString) {
        // 1.确保base目录存在
        FileUtils.ensureFileExists(mBasePath, true);
        // 2.确保月份目录存在
        final String month = mDateFormatterWithoutYear.format(new Date(getExceptionTime()));
        FileUtils.ensureFileExists(mBasePath + File.separator + month, true);
        // 3.确保文件存在
        final String fileName = getExceptionName() + "_" + mDateFormatter.format(new Date(getExceptionTime())) + ".txt";
        final String filePath = mBasePath + File.separator + month + File.separator + fileName;
        FileUtils.ensureFileExists(filePath, false);
        // 4.保存至文件
        FileUtils.saveString2file(new File(filePath), exceptionString);
    }
}
