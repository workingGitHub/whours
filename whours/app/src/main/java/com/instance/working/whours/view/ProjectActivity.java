package com.instance.working.whours.view;

import android.app.Fragment;
import android.util.Log;

import com.instance.working.whours.controller.ProjectFragment;

import java.util.UUID;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class ProjectActivity extends SingFragmentActivity{
    public static final String EXTRA_PROJECT_ID = "com.instance.working.whours.controlle.ProjectActivity.EXTRA_PROJECT_ID";

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID)getIntent().getSerializableExtra(EXTRA_PROJECT_ID);
        return ProjectFragment.newInstance(id);
    }
}
