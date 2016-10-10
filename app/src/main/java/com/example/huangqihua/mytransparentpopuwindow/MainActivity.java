package com.example.huangqihua.mytransparentpopuwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.huangqihua.mytransparentpopuwindow.model.SubMenuData;
import com.example.huangqihua.mytransparentpopuwindow.view.HQHSharePopupWindow;
import com.example.huangqihua.mytransparentpopuwindow.view.HQHSubMenuListPopupWindow;
import com.example.huangqihua.mytransparentpopuwindown.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button showPopup, showListPopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showPopup = (Button) findViewById(R.id.show_popup);

        showListPopup = (Button) findViewById(R.id.show_list_popup);

    }

    //分享微信的弹框
    public void showPop(View view) {
        HQHSharePopupWindow popupWindow = new HQHSharePopupWindow(this);
        popupWindow.setItemSelectedListener(new HQHSharePopupWindow.onItemClickListener() {
            @Override
            public void onItemSelected(Boolean b) {
                if (b) {
                    Toast.makeText(MainActivity.this, "微信好友", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "朋友圈", Toast.LENGTH_SHORT).show();
                }
            }
        });
        popupWindow.showAtLocation(showPopup, Gravity.BOTTOM, 0, 0);
    }

    //ListView的弹框
    public void showListPop(View view) {
        HQHSubMenuListPopupWindow listPopupWindow = new HQHSubMenuListPopupWindow(this,getListData());
        listPopupWindow.setOnItemTypeClickListener(new HQHSubMenuListPopupWindow.OnItemSelectedClickListener() {
            @Override
            public void onItemTypeClickListener(SubMenuData data) {
                int type = data.getType();
                switch (type) {
                    case 0:
                        //TODO
                        break;
                    case 1:
                        //TODO
                        break;
                    case 2:
                        //TODO
                        break;
                    case 3:
                        //TODO
                        break;
                    case 4:
                        //TODO
                        break;
                    case 5:
                        //TODO
                        break;
                    case 6:
                        //TODO
                        break;
                }
            }
        });
        listPopupWindow.showAtLocation(showListPopup,-55, -20);
    }

    public List<SubMenuData> getListData() {

        List<SubMenuData> mData = new ArrayList<>();
        mData.add(new SubMenuData("北京", 0, true));
        mData.add(new SubMenuData("上海", 1, true));
        mData.add(new SubMenuData("南京", 2, true));
        mData.add(new SubMenuData("山东", 3, true));
        mData.add(new SubMenuData("云南", 4, true));
        mData.add(new SubMenuData("西藏", 5, true));
        mData.add(new SubMenuData("青岛", 6, true));

        return mData;
    }
}
