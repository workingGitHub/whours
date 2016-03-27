package com.instance.working.whours.view;

import android.app.Fragment;
import com.instance.working.whours.controller.ProjectFragment;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class ProjectActivity extends SingFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new ProjectFragment();
    }
}
