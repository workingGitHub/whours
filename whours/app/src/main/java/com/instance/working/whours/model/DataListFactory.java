package com.instance.working.whours.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/2/2 0002.
 * 数据工厂，单件模式
 */
public class DataListFactory {
    private Context mContext;
    private static DataListFactory sInstance;
    private ArrayList<ProjectInfo> mProjectInfoList;


    public static DataListFactory get(Context c)
    {
        if(sInstance == null)
        {
            sInstance = new DataListFactory(c);
        }
        return sInstance;
    }
    private DataListFactory(Context context)
    {
        mContext = context;
    }

    public void AddProject(ProjectInfo p){mProjectInfoList.add(p);}
    public void DelProject(ProjectInfo p){mProjectInfoList.remove(p);}

    public ArrayList<ProjectInfo> getProjectList(){return mProjectInfoList;}
    public ProjectInfo getProjectInfo(UUID id){
        for(ProjectInfo p:mProjectInfoList)
        {

        }
        return null;
    }

}
