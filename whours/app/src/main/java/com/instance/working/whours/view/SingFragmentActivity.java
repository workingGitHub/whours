package com.instance.working.whours.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.instance.working.whours.R;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public abstract class SingFragmentActivity extends Activity{
    protected abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singfragmentview);

        FragmentManager framentmanager = getFragmentManager();
        Fragment fragment = framentmanager.findFragmentById(R.id.frameContainer);
        if(fragment == null)
        {
            fragment = createFragment();
            framentmanager.beginTransaction()
                    .add(R.id.frameContainer,fragment)
                    .commit();
        }
    }
}
