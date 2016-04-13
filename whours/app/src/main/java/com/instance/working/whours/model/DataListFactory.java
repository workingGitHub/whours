package com.instance.working.whours.model;

import android.content.Context;
import android.util.Log;

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

    private static String FileName = "whours";
    private ProjectIntentJSONSerial _projectJsonSerial;



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

        _projectJsonSerial = new ProjectIntentJSONSerial(context,FileName);
        try {
            mProjectInfoList = _projectJsonSerial.loadData();
        }catch (Exception e)
        {
            mProjectInfoList = new ArrayList<ProjectInfo>();
            Log.e("working", e.toString());
            Log.e("working", e.getMessage());
        }
    }
    public boolean onSave()
    {
        try {
            _projectJsonSerial.onSave(mProjectInfoList);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println("--------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------");
            e.printStackTrace();
            Log.e("working", e.toString());
            Log.e("working", e.getMessage());
            return false;
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
