package com.example.huangqihua.mytransparentpopuwindow.model;

/**
 * Created by huangqihua on 16/10/10.
 */
public class SubMenuData {

    private String city;

    private int type;

    private boolean bSelected;

    public SubMenuData(String name, int type, boolean isSelected) {
        this.city = name;
        this.type = type;
        this.bSelected = isSelected;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isbSelected() {
        return bSelected;
    }

    public void setbSelected(boolean bSelected) {
        this.bSelected = bSelected;
    }
}
