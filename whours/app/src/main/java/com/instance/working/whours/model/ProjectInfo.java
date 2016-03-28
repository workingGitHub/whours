package com.instance.working.whours.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 * ��Ŀ��Ϣ��
 * ������Ŀ��Ϣ��
 * ID�� ��ĿID
 * FatherId������ĿID
 * sonidList�� ����ĿID
 * ItemList����Ŀ��ѧϰ��Ϣ�б�
 * CostTime�� �����ڸ���Ŀ�ϵ�ʱ��
 * title�� ��Ŀ����
 * detail�� ��Ŀ��ϸ˵��
 * picIndexList��  ���մ洢��*��
 * musicList��   ¼���ܽᣨ*��
 * weigth�� ����Ŀ����Ҫ�̶ȣ����ԣ���Ҫ����Ҫ��������
 * itemlist:  ��Ŀ�б�
 */
public class ProjectInfo {
    private UUID Id; //��ĿID
    private ArrayList<ItemInfo> ItemList; //��Ŀ��ѧϰ��Ϣ�б�
    private long CostTime;
    private String Title;
    private String Detail;
    private int Weigth;
    public ProjectInfo()
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
    public String getCostTimeStr() {
        float time_value = 0;
        if(CostTime/3600 != 0)
        {
            time_value = ((float)CostTime)/3600;
            return String.format("%.2f��", time_value);
        }else if(CostTime/60 != 0)
        {
            time_value = ((float)CostTime)/60;
            return  String.format("%.2fСʱ", time_value);
        }
        else
        {
            time_value = CostTime;
            return String.format("%.2f����", time_value);
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
}
