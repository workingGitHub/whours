package com.instance.working.whours.controller;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.instance.working.whours.R;
import com.instance.working.whours.model.DataListFactory;
import com.instance.working.whours.model.ProjectInfo;
import com.instance.working.whours.view.ProjectActivity;

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
    public void onResume() {
        super.onResume();
        ((ProjectAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_projectlist,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_newproject:
                ProjectInfo c = new ProjectInfo();
                Intent i = new Intent(getActivity(),ProjectActivity.class);
                DataListFactory.get(getActivity()).getProjectList().add(c);
                i.putExtra(ProjectActivity.EXTRA_PROJECT_ID, c.getId());
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            time_text.setText(projectInfo.getCostTimeStr());
            return convertView;
        }
    }
}
