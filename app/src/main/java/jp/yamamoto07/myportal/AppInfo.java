package jp.yamamoto07.myportal;

import android.graphics.drawable.Drawable;

/**
 * Created by yamamoto on 2015/10/11.
 */
public class AppInfo {
    private String applicationName;
    private String packageName;
    private String className;
    private Drawable icon;

    public AppInfo(String applicationName, String packageName, String className, Drawable icon) {
        this.applicationName = applicationName;
        this.packageName = packageName;
        this.className = className;
        this.icon = icon;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName(){ return className; }

    public Drawable getIcon() {
        return icon;
    }
}
