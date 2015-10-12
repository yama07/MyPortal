package jp.yamamoto07.myportal;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button[] buttons;
    private AppInfo[] apps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[6];
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);
        buttons[5] = (Button) findViewById(R.id.button6);
        for (Button btn : buttons) {
            btn.setOnClickListener(this);
            btn.setOnLongClickListener(this);
        }

        apps = new AppInfo[6];
        List<AppInfo> appList = getAppInfoList();
        String[] default_packages = getResources().getStringArray(R.array.default_app);
        for(int i=0; i<default_packages.length; i++){
            String packageNames = default_packages[i];
            for(AppInfo app: appList){
                if (app.getPackageName().equals(packageNames)){
                    apps[i] = app;
                    buttons[i].setCompoundDrawablesWithIntrinsicBounds(null, app.getIcon() , null, null);
//                    buttons[i].setBackground(app.getIcon());
                    buttons[i].setText(app.getApplicationName());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        Log.i("test", "onClick!");

        Intent intent = new Intent();
        for(int i=0; i<buttons.length; i++){
            if(v == buttons[i]){
                //intent.setClassName(apps[i].getPackageName(),apps[i].getClassName());
                //intent.setClassName("com.google.android.apps.maps","com.google.android.apps.gmm.base.app.GoogleMapsApplication");
                intent.setPackage(apps[i].getPackageName());
                break;
            }
        }
        // アプリを起動
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        Log.i("test", "onLonClick");
        List<AppInfo> appList = getAppInfoList();

        AppListDialog dialog = new AppListDialog(appList);
        dialog.show(getFragmentManager(), "longClick");
        return false;
    }

    private List<AppInfo> getAppInfoList(){
        final PackageManager pm;
        pm = getPackageManager();

        // 端末にインストール済のアプリケーション一覧情報を取得
        final int flags = PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_DISABLED_COMPONENTS;
        final List<ApplicationInfo> installedAppList = pm.getInstalledApplications(flags);
        List<AppInfo> appList = new ArrayList<>();
        for (ApplicationInfo app : installedAppList) {
            if(app.className != null) {
                Log.i("test", app.className);
                Log.i("test", app.packageName);
                Log.i("test", app.loadLabel(pm).toString());
                appList.add(
                        new AppInfo(app.loadLabel(pm).toString(), app.packageName, app.className, app.loadIcon(pm))
                );
            }
        }
        return appList;
    }
}
