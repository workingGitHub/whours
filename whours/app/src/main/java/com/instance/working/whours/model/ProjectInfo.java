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
    private UUID mId; //��ĿID
    private ArrayList<ItemInfo> mItemList; //��Ŀ��ѧϰ��Ϣ�б�
    private long mCostTime;
    private String mTitle;
    private String mDetail;
    private int mWeigth;

}
