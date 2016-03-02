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
