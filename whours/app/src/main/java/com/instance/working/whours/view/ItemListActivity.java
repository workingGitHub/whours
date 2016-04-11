package com.instance.working.whours.view;

import android.app.Fragment;

import com.instance.working.whours.controller.ItemListFragment;
import com.instance.working.whours.controller.ProjectFragment;

import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 * ��ʾitem�б�
 * ͨ��fatherId��ȡ���ù��̵�ѧϰ�б���ʾ˳�����µ���ʾ���ǰ
 */
public class ItemListActivity extends SingFragmentActivity {
    public static final String EXTRA_PROJECT_ID = "com.instance.working.whours.controlle.ItemListActivity.EXTRA_PROJECT_ID";

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID)getIntent().getSerializableExtra(EXTRA_PROJECT_ID);
        return ItemListFragment.newInstance(id);
    }

}
