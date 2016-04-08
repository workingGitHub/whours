package com.instance.working.whours.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 * ÿһ���������Ϣ  ��ѧϰ��¼��Ϣ��
 * ������Ϣ���£�
 * id��  ��Ϊÿһ��������Ϣ��Ψһ��ʾ
 * startime:  ��ʼʱ��
 * endtime ������ʱ��
 * costtime ��  ѧϰ�����ĵ�ʱ��
 * text��   ѧϰ�ܽ�
 * picIndexList��  ���մ洢��*��
 * musicList��   ¼���ܽᣨ*��
 * weight��  �������Ҫ�̶ȣ����ԣ���Ҫ����Ҫ��������
 */

public class ItemInfo {
    private UUID Id; //��ĿID
    private Date StartTime;
    private Date EndTime;

    public long getMaxCostTime() {
        return MaxCostTime;
    }

    private long MaxCostTime;
    private long CostTime;
    private String Detail;

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
        return MaxCostTime != 0;
    }

}
