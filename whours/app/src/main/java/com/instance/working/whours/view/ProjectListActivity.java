package com.instance.working.whours.view;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.instance.working.whours.controller.ProjectListFragment;
import com.instance.working.whours.model.ProjectInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class ProjectListActivity extends SingFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ProjectListFragment();
    }

}
