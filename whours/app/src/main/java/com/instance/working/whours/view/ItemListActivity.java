package com.instance.working.whours.view;

import android.app.Fragment;

import com.instance.working.whours.controller.ItemListFragment;
import com.instance.working.whours.controller.ProjectFragment;

import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 * 显示item列表，
 * 通过fatherId获取，该工程的学习列表，显示顺序最新的显示在最靠前
 */
public class ItemListActivity extends SingFragmentActivity {
    public static final String EXTRA_PROJECT_ID = "com.instance.working.whours.controlle.ItemListActivity.EXTRA_PROJECT_ID";

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID)getIntent().getSerializableExtra(EXTRA_PROJECT_ID);
        return ItemListFragment.newInstance(id);
    }

}
