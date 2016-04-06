package com.instance.working.whours.controller;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import com.instance.working.whours.R;
import com.instance.working.whours.model.DataListFactory;
import com.instance.working.whours.model.ProjectInfo;

import java.util.UUID;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class ProjectFragment extends Fragment {
    public static final String EXTRA_PROJECT_ID = "com.instance.working.whours.controlle.ProjectFragment.EXTRA_PROJECT_ID";
    public ProjectInfo _projcetinfo;
    public static ProjectFragment newInstance(UUID id)
    {
        Bundle args = new Bundle();
        Log.v("working", "1.1");

        args.putSerializable(EXTRA_PROJECT_ID, id);
        Log.v("working", "1.2");
        ProjectFragment c = new ProjectFragment();
        c.setArguments(args);
        Log.v("working", "1.3");
        return c;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID) getArguments().getSerializable(EXTRA_PROJECT_ID);
        Log.v("working", "2.1");
        setHasOptionsMenu(true);
        Log.v("working", "2.2");
        if(id != null)
        {
            _projcetinfo = DataListFactory.get(getActivity()).getProjectItem(id);
        }
        else
        {
            _projcetinfo = new ProjectInfo();
        }
        Log.v("working", "2.3");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _v = inflater.inflate(R.layout.layout_projectinfo,container,false);
        Log.v("working", "3.1");
        EditText _project_name = (EditText)_v.findViewById(R.id.project_title);
        EditText _project_detail = (EditText)_v.findViewById(R.id.project_detail);
        _project_name.setText(_projcetinfo.getTitle());
        _project_detail.setText(_projcetinfo.getDetail());
        Log.v("working", String.format("3.2 %s %s",_projcetinfo.getTitle(),_projcetinfo.getDetail()));
        _project_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _projcetinfo.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        _project_detail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _projcetinfo.setDetail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Log.v("working", "3.3");
        return _v;
    }
}
