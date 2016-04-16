package com.instance.working.whours.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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
public class ProjectInfo extends ProjectInterface {
    private static String PROJECT_ID = "PROJECT_ID";
    private static String PROJECT_COSTTIME = "PROJECT_COSTTIME";
    private static String PROJECT_TITLE = "PROJECT_TITLE";
    private static String PROJECT_DETAIL = "PROJECT_DETAIL";
    private static String PROJECT_WEIGTH = "PROJECT_WEIGTH";
    private static String PROJECT_ITEMLIST = "PROJECT_ITEMLIST";
    private static String PROJECT_GOINGITEM = "PROJECT_GOINGITEM";

    private UUID Id; //项目ID
    private ArrayList<ItemInfo> ItemList; //项目的学习信息列表
    private long CostTime;
    private String Title;
    private String Detail;
    private int Weigth;
    private ItemInfo GoingItem; //正在进行的学习项目


    ProjectInfo(JSONObject json) throws JSONException
    {
        Id = UUID.fromString(json.getString(PROJECT_ID));

        CostTime = 0;
        Weigth = 0;
        GoingItem = null;

        if(json.has(PROJECT_COSTTIME))
        {
            CostTime = json.getLong(PROJECT_COSTTIME);
        }

        if(json.has(PROJECT_TITLE))
        {
            Title = json.getString(PROJECT_TITLE);
        }else{
            Title = new String();
        }
        if(json.has(PROJECT_DETAIL))
        {
            Detail = json.getString(PROJECT_DETAIL);
        }else{
            Detail = new String();
        }

        if(json.has(PROJECT_WEIGTH))
        {
            Weigth = json.getInt(PROJECT_WEIGTH);
        }

        if(json.has(PROJECT_ITEMLIST))
        {
            ItemList = CreateJsonItemList(json.getJSONArray(PROJECT_ITEMLIST));
        }else{
            ItemList = new ArrayList<ItemInfo>();
        }


        if(json.has(PROJECT_GOINGITEM))
        {
            GoingItem = new ItemInfo(json.getJSONObject(PROJECT_GOINGITEM),this);
        }

        CostTime = 0;
        for(ItemInfo c:ItemList)
        {
            CostTime  = CostTime + c.getCostTime();
        }
    }

    @Override
    public void UpdateCostTime(long NewCostTime, long OldCostTime) {
        CostTime = CostTime - OldCostTime + NewCostTime;
    }

    @Override
    public UUID getProjectId() {
        return Id;
    }

    private ArrayList<ItemInfo> CreateJsonItemList(JSONArray jArray) throws JSONException
    {
        ArrayList<ItemInfo> _itemlist = new ArrayList<ItemInfo>();
        try{
            //打开要读取的文件
            for(int i = 0 ;i < jArray.length(); i++)
            {
                _itemlist.add(new ItemInfo(jArray.getJSONObject(i),this));
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println("--------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------");
            e.printStackTrace();
            Log.e("working", e.toString());
            Log.e("working",e.getMessage());

        }
        return _itemlist;
    }

    private JSONArray GetJsonItemList()  throws JSONException
    {
        JSONArray jArray = new JSONArray();
        for(ItemInfo c:ItemList)
        {
            // JSONObject jObject = c.toJSON();
            jArray.put(c.toJSON());
        }
        return jArray;
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject jObject = new JSONObject();
        jObject.put(PROJECT_ID,Id.toString());
        jObject.put(PROJECT_COSTTIME,CostTime);
        jObject.put(PROJECT_TITLE,Title);
        jObject.put(PROJECT_DETAIL,Detail);
        jObject.put(PROJECT_WEIGTH,Weigth);
        jObject.put(PROJECT_ITEMLIST,GetJsonItemList());
        if(GoingItem != null) {
            jObject.put(PROJECT_GOINGITEM, GoingItem.toJSON());
        }

        return jObject;
    }


    public ItemInfo getGoingItem() {
        return GoingItem;
    }

    public void setGoingItem(ItemInfo goingItem) {
        GoingItem = goingItem;
    }

    public boolean isStart()
    {
        return GoingItem != null;
    }
    public void Start()
    {
        Log.v("working",String.format("Start [%s]",Title));
        GoingItem = new ItemInfo(this);
        GoingItem.Start();

    }
    public void cancelStart()
    {
        GoingItem = null;
    }
    public boolean End()
    {
        boolean isEnd = GoingItem.End();
        if(isEnd)
        {
            ItemList.add(GoingItem);
        }
        else
        {
            CostTime--;
        }
        Log.v("working",String.format("End [%s] [%d]",Title,GoingItem.getMaxCostTime()));
        GoingItem = null;
        return isEnd;
    }

    public ProjectInfo()
    {
        Id = UUID.randomUUID();
        Title = new String();
        Detail = new String();
        CostTime = 0;
        Weigth = 0;
        GoingItem = null;
        ItemList = new ArrayList<ItemInfo>();
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
    public String getCostTimeStr() {
        float time_value = 0;
        if(CostTime/3600 != 0)
        {
            time_value = ((float)CostTime)/3600;
            return String.format("%.2f天", time_value);
        }else if(CostTime/60 != 0)
        {
            time_value = ((float)CostTime)/60;
            return  String.format("%.2f小时", time_value);
        }
        else
        {
            time_value = CostTime;
            return String.format("%.2f分钟", time_value);
        }
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

    public ItemInfo getItemInfo(UUID id){
        for(ItemInfo p:ItemList)
        {
            if(p.getId().equals(id))
            {
                return p;
            }
        }
        return null;
    }


}
