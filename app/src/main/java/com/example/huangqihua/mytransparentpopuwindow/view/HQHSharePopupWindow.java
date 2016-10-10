package com.example.huangqihua.mytransparentpopuwindow.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.example.huangqihua.mytransparentpopuwindown.R;

/**
 * Created by huangqihua on 16/10/10.
 * 分享微信的弹框
 */
public class HQHSharePopupWindow extends PopupWindow implements View.OnClickListener {

    private Context mContext;

    public onItemClickListener listener;

    public interface onItemClickListener {
        void onItemSelected(Boolean b);
    }

    public void setItemSelectedListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public HQHSharePopupWindow(Context context) {
        this.mContext = context;
        initView();
    }

    private void initView() {

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.layout_popup_window, null);
        LinearLayout pyqLayout = (LinearLayout) contentView.findViewById(R.id.share_popup_pyq_layout);
        LinearLayout wxFriendLayout = (LinearLayout) contentView.findViewById(R.id.share_popup_wx_friend_layout);
        Button cancel = (Button) contentView.findViewById(R.id.share_popup_bt_cancel);

        setFocusable(true);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(contentView);

        pyqLayout.setOnClickListener(this);
        wxFriendLayout.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_popup_wx_friend_layout:
                if (listener != null) {
                    listener.onItemSelected(true);
                }
                break;

            case R.id.share_popup_pyq_layout:
                if (listener != null) {
                    listener.onItemSelected(false);
                }
                break;

            case R.id.share_popup_bt_cancel:

                break;
        }
        dismiss();
    }

}
