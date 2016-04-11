package com.instance.working.whours.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 * 每一项的数据信息  （学习记录信息）
 * 包含信息如下：
 * id：  作为每一项数据信息的唯一标示
 * startime:  开始时间
 * endtime ：结束时间
 * costtime ：  学习所消耗的时间
 * text：   学习总结
 * picIndexList：  快照存储（*）
 * musicList：   录音总结（*）
 * weight：  该项的重要程度（忽略，次要，重要，紧急）
 */

public class ItemInfo {
    private UUID Id; //项目ID
    private Date StartTime;
    private Date EndTime;

    private long MaxCostTime;
    private long CostTime;
    private String Detail;

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public void setMaxCostTime(long maxCostTime) {
        MaxCostTime = maxCostTime;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public void setCostTime(long costTime) {
        CostTime = costTime;
    }

    public UUID getId() {
        return Id;
    }

    public String getDetail() {
        return Detail;
    }

    public long getCostTime() {
        return CostTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public long getMaxCostTime() {
        return MaxCostTime;
    }

    public ItemInfo()
    {
        Id = UUID.randomUUID();
        Detail = new String();
        CostTime = 0;
        StartTime = null;
        EndTime = null;
    }

    public void Start()
    {
        if(StartTime == null)
        {
            StartTime = new Date();
        }
    }
    public boolean End()
    {
        if(EndTime == null)
        {
            EndTime = new Date();
        }

        MaxCostTime = EndTime.getTime() - StartTime.getTime();
        MaxCostTime /= 1000 * 60;
        MaxCostTime++;
        CostTime = MaxCostTime;
        return MaxCostTime != 1;
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
    public String getStartTimestr(String s) {
        if(StartTime == null)
        {
            return new String();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(s);
        return sdf.format(StartTime);
    }


}
