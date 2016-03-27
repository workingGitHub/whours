package com.instance.working.whours.controller;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.instance.working.whours.R;
import com.instance.working.whours.model.DataListFactory;
import com.instance.working.whours.model.ProjectInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/2 0002.
 */
public class ProjectListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v("DebugTimeLoop", "ListCrimFrament.onCreate");
        super.onCreate(savedInstanceState);
        //启用菜单栏
        setHasOptionsMenu(true);
        //保存临时信息
        setRetainInstance(true);
        //mCrims = CrimLab.get(getActivity()).get_crimList();
        ProjectAdapter adpter = new ProjectAdapter(DataListFactory.get(getActivity()).getProjectList());
        setListAdapter(adpter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_projectlist,menu);
    }

    private class ProjectAdapter extends ArrayAdapter<ProjectInfo>
    {
        public ProjectAdapter(ArrayList<ProjectInfo> projectInfos)
        {
            super(getActivity(),0,projectInfos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_projectlist_item,null);
            }
            TextView title_text = (TextView)convertView.findViewById(R.id.list_item_name);
            TextView detail_text = (TextView)convertView.findViewById(R.id.list_item_detail);
            TextView time_text = (TextView)convertView.findViewById(R.id.list_item_time);
            ProjectInfo projectInfo = getItem(position);
            title_text.setText(projectInfo.getTitle());
            detail_text.setText(projectInfo.getDetail());

            float time_value = 0;
            if(projectInfo.getCostTime()/3600 != 0)
            {
                time_value = ((float)projectInfo.getCostTime())/3600;
                time_text.setText(String.format("%.2f天", time_value));
            }else if(projectInfo.getCostTime()/60 != 0)
            {
                time_value = ((float)projectInfo.getCostTime())/60;
                time_text.setText(String.format("%.2f小时", time_value));
            }
            else
            {
                time_value = projectInfo.getCostTime();
                time_text.setText(String.format("%.2f分钟", time_value));
            }

            return convertView;
        }
    }
}
