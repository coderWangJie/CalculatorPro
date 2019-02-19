package cn.wangj.baslib.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

public class AndroidUtil {
    private static final String TAG = AndroidUtil.class.getSimpleName();

    // 获取系统信息：SDK_INT，SDK，BRAND，MODEL
    public static void printSysInfo() {
        Logger.d(TAG, "RELEASE:" + Build.VERSION.RELEASE);  // Android Version
        Logger.d(TAG, "SDK_INT:" + Build.VERSION.SDK_INT);  // Android API Level
        Logger.d(TAG, "BRAND:" + Build.BRAND);   // Device Brand
        Logger.d(TAG, "MODEL:" + Build.MODEL);   // Device Model
        Logger.d(TAG, "INCREMENTAL:" + Build.VERSION.INCREMENTAL);  // UI Model
        Logger.d(TAG, "DEVICE:" + Build.DEVICE);
        Logger.d(TAG, "DISPLAY:" + Build.DISPLAY);
    }

    /**
     * get the APP VersionCode
     *
     * @return versionCode
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(
                    context.getApplicationInfo().packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, "getAppVersionCode: " + e.toString());
        }
        Logger.d(TAG, "AppVersionCode = " + versionCode);
        return versionCode;
    }

    /**
     * get the App VersionName
     *
     * @return versionName
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            versionName = "v" + context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, "getAppVersionName: " + e.toString());
        }
        Logger.d(TAG, "AppVersionName = " + versionName);
        return versionName;
    }
}
