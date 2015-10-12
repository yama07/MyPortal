package jp.yamamoto07.myportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yamamoto on 2015/10/11.
 */
public class AppInfoAdapter extends ArrayAdapter<AppInfo> {

    private LayoutInflater mInflater;

    public AppInfoAdapter(Context context, List<AppInfo> objects) {
        super(context, R.layout.app_list);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addAll(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.app_list, parent, false);
            holder.textLabel = (TextView) convertView.findViewById(R.id.app_label);
            holder.imageIcon = (ImageView) convertView.findViewById(R.id.app_icon);
            holder.packageName = (TextView) convertView.findViewById(R.id.app_pname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 表示データを取得
        final AppInfo data = getItem(position);

        // ラベルとアイコンをリストビューに設定
        holder.textLabel.setText(data.getApplicationName());
        holder.imageIcon.setImageDrawable(data.getIcon());
        holder.packageName.setText(data.getPackageName());

        return convertView;
    }
    private static class ViewHolder {
        TextView textLabel;
        ImageView imageIcon;
        TextView packageName;
    }
}

