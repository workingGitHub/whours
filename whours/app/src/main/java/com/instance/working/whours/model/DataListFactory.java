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
        mProjectInfoList = new ArrayList<ProjectInfo>();
        for(int i = 0 ; i <  50 ; i++)
        {
            ProjectInfo p = new ProjectInfo();
            p.setTitle(String.format("第%d工程",i));
            p.setDetail(String.format("第%d个内容内容信息",i));
            p.setCostTime(i*i*i);
            AddProject(p);
        }
    }

    public void AddProject(ProjectInfo p){mProjectInfoList.add(p);}
    public void DelProject(ProjectInfo p){mProjectInfoList.remove(p);}

    public ArrayList<ProjectInfo> getProjectList(){return mProjectInfoList;}
    public ProjectInfo getProjectItem(UUID id){
        for(ProjectInfo p:mProjectInfoList)
        {
            if(p.getId().equals(id))
            {
                return p;
            }
        }
        return null;
    }

}
