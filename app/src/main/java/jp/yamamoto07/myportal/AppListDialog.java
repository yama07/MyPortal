package jp.yamamoto07.myportal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamamoto on 2015/10/11.
 */
public class AppListDialog extends DialogFragment {

    private AlertDialog mAlertDialog;
    private List<AppInfo> appList;

    public AppListDialog(){
        this(new ArrayList<AppInfo>());
    }
    public AppListDialog(List<AppInfo> appList){
        this.appList = appList;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("選択してください");

//        ListView listView = new ListView(getContext());
//        listView.setAdapter(new ApplicationInfoAdapter(getContext(),appList));
        builder.setAdapter(new AppInfoAdapter(getContext(), appList), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("test", "tap!");
            }
        });

        mAlertDialog = builder.create();

        return mAlertDialog;
    }
}
