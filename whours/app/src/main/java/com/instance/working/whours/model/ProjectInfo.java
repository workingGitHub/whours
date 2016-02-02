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
    private UUID mId; //项目ID
    private ArrayList<ItemInfo> mItemList; //项目的学习信息列表
    private long mCostTime;
    private String mTitle;
    private String mDetail;
    private int mWeigth;

}
