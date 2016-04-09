package com.instance.working.whours.view;

import android.app.Fragment;
import android.util.Log;

import com.instance.working.whours.controller.ItemFragment;
import com.instance.working.whours.controller.ProjectFragment;

import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 * �������༭����ʾһ����Ϣ����ͼ
 */
public class ItemActivity extends SingFragmentActivity{
    public static final String EXTRA_PROJECT_ID = "com.instance.working.whours.controlle.ItemActivity.EXTRA_PROJECT_ID";
    public static final String EXTRA_ITEM_ID = "com.instance.working.whours.controlle.ItemActivity.EXTRA_ITEM_ID";

    @Override
    protected Fragment createFragment() {
        UUID projectid = (UUID)getIntent().getSerializableExtra(EXTRA_PROJECT_ID);
        UUID itemid = (UUID)getIntent().getSerializableExtra(EXTRA_ITEM_ID);

        return ItemFragment.newInstance(projectid,itemid);
    }


}
