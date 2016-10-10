package com.example.huangqihua.mytransparentpopuwindow;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.huangqihua.mytransparentpopuwindow.model.SubMenuData;
import com.example.huangqihua.mytransparentpopuwindown.R;

import java.util.List;

/**
 * Created by huangqihua on 16/10/10.
 */
public class HQHRoundCornerAdapter extends BaseAdapter {
    private Context mContext;
    private List<SubMenuData> mMenuData;

    public HQHRoundCornerAdapter(Context context, List<SubMenuData> mData) {
        this.mContext = context;
        this.mMenuData = mData;
    }


    @Override
    public int getCount() {
        return mMenuData.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenuData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        boolean selected = false;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_round_corner, parent, false);
            holder = new ViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.item_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SubMenuData subMenuData = mMenuData.get(position);
        holder.mTitle.setText(subMenuData.getCity());

        selected = subMenuData.isbSelected();
        if (selected) {
            holder.mTitle.setTextColor(Color.parseColor("#ff9a2d"));
        } else {
            holder.mTitle.setTextColor(Color.parseColor("#ffffff"));
        }

        return convertView;
    }

    class ViewHolder {
        TextView mTitle;
    }

}
