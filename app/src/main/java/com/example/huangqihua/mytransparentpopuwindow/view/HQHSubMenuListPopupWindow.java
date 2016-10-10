package com.example.huangqihua.mytransparentpopuwindow.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.example.huangqihua.mytransparentpopuwindow.HQHRoundCornerAdapter;
import com.example.huangqihua.mytransparentpopuwindow.model.SubMenuData;
import com.example.huangqihua.mytransparentpopuwindown.R;

import java.util.List;

/**
 * Created by huangqihua on 16/10/10.
 */
public class HQHSubMenuListPopupWindow extends PopupWindow {

    private Context mContext;

    private HQHRoundCornerAdapter mAdapter;

    private boolean bClickColor;

    private List<SubMenuData> mMenuData;

    public OnItemSelectedClickListener listener;


    public interface OnItemSelectedClickListener {
        void onItemTypeClickListener(SubMenuData data);
    }

    public void setOnItemTypeClickListener(OnItemSelectedClickListener listener) {
        this.listener = listener;
    }

    public HQHSubMenuListPopupWindow(Context context,List<SubMenuData> mData) {
        this.mContext = context;
        this.mMenuData = mData;
        initView();
    }

    /**
     * 设置点击的颜色
     *
     * @param bColor
     */
    public void setClickColor(boolean bColor) {
        this.bClickColor = bColor;
    }


    private void initView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_sub_menu_list_popup_window, null);
        final HQHRoundCornerListView listView = (HQHRoundCornerListView) contentView.findViewById(R.id.list_view);

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        ColorDrawable drawable = new ColorDrawable(0x000000);
        setBackgroundDrawable(drawable);

        mAdapter = new HQHRoundCornerAdapter(mContext, mMenuData);
        listView.setAdapter(mAdapter);

        if (listView.getCount() > 5) {
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = dip2px(200);
            listView.setLayoutParams(params);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    SubMenuData subMenuData = mMenuData.get(position);
                    if (bClickColor) {
                        for (int i = 0; i < mAdapter.getCount(); i++) {
                            if (i == position) {
                                mMenuData.get(i).setbSelected(true);
                            } else {
                                mMenuData.get(i).setbSelected(false);
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                    listener.onItemTypeClickListener(subMenuData);
                }
                dismiss();
            }
        });

        listView.setBackgroundColor(Color.parseColor("#444444"));
        listView.setPressedColor(Color.parseColor("#EE666666"));
        setContentView(contentView);
    }


    public void showAtLocation(View v, int x, int y) {
        showAsDropDown(v, dip2px(x), dip2px(y));
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * @param dpValue
     * @return dip 转换成px
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
