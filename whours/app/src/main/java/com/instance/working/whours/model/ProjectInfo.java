package com.instance.working.whours.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 * 项目信息类
 * 存贮项目信息：
 * ID： 项目ID
 * FatherId：父项目ID
 * sonidList： 子项目ID
 * ItemList：项目的学习信息列表
 * CostTime： 花费在该项目上的时间
 * title： 项目标题
 * detail： 项目详细说明
 * picIndexList：  快照存储（*）
 * musicList：   录音总结（*）
 * weigth： 该项目的重要程度（忽略，次要，重要，紧急）
 * itemlist:  项目列表
 */
public class ProjectInfo {
    private UUID Id; //项目ID
    private ArrayList<ItemInfo> ItemList; //项目的学习信息列表
    private long CostTime;
    private String Title;
    private String Detail;
    private int Weigth;
    ProjectInfo()
    {
        Id = UUID.randomUUID();
    }
    public UUID getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDetail() {
        return Detail;
    }

    public long getCostTime() {
        return CostTime;
    }

    public int getWeigth() {
        return Weigth;
    }

    public ArrayList<ItemInfo> getItemList() {
        return ItemList;
    }

    public void setItemList(ArrayList<ItemInfo> itemList) {
        ItemList = itemList;
    }

    public void setCostTime(long costTime) {
        CostTime = costTime;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public void setWeigth(int weigth) {
        Weigth = weigth;
    }
}
