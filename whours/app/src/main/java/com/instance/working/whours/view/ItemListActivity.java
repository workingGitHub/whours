package com.instance.working.whours.view;

import android.app.Fragment;

import com.instance.working.whours.controller.ItemListFragment;

/**
 * Created by Administrator on 2016/1/28 0028.
 * ��ʾitem�б�
 * ͨ��fatherId��ȡ���ù��̵�ѧϰ�б���ʾ˳�����µ���ʾ���ǰ
 */
public class ItemListActivity extends SingFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ItemListFragment();
    }

}
