package com.instance.working.whours.controller;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.instance.working.whours.R;
import com.instance.working.whours.model.DataListFactory;
import com.instance.working.whours.model.ItemInfo;
import com.instance.working.whours.model.ProjectInfo;
import com.instance.working.whours.view.ItemActivity;
import com.instance.working.whours.view.ItemListActivity;
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
    public void onPause() {
        super.onPause();
        DataListFactory.get(getActivity()).onSave();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ProjectAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_projectlist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_newproject:
                ProjectInfo c = new ProjectInfo();
                Intent i = new Intent(getActivity(),ProjectActivity.class);
                DataListFactory.get(getActivity()).AddProject(c);
                i.putExtra(ProjectActivity.EXTRA_PROJECT_ID, c.getId());
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 创建上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_projectlist_context, menu);
        //super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //因为ListView是AdpterView的子类，所以getmenuInfo返回的为AdpaterContextmenuInfo
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int postion = info.position;
        ProjectAdapter cAdper = (ProjectAdapter)getListAdapter();
        ProjectInfo c = cAdper.getItem(postion);
        Intent i;
        switch (item.getItemId())
        {
            case R.id.menu_delete_projectinfo:
                DataListFactory.get(getActivity()).DelProject(c);
                cAdper.notifyDataSetChanged();
                return true;
            case R.id.menu_change_projectinfo:
                i = new Intent(getActivity(),ProjectActivity.class);
                i.putExtra(ProjectActivity.EXTRA_PROJECT_ID, c.getId());
                startActivity(i);
                return true;
            case R.id.menu_cancel_studystate:
                c.cancelStart();
                cAdper.notifyDataSetChanged();
                return true;
            case R.id.menu_show_itemlist:
                i = new Intent(getActivity(),ItemListActivity.class);
                i.putExtra(ItemListActivity.EXTRA_PROJECT_ID, c.getId());
                startActivity(i);
                return true;
            default :
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 注册上下文状态
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ListView listview = (ListView)v.findViewById(android.R.id.list);
        listview.setBackgroundResource(R.drawable.projectinfo_background);
        registerForContextMenu(listview);
        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ProjectAdapter cAdper = (ProjectAdapter)getListAdapter();
        ProjectInfo c = (ProjectInfo)(cAdper.getItem(position));
        if(c.isStart()) {
            //v.setBackgroundColor(Color.WHITE);
            ItemInfo _itemInfo = c.getGoingItem();
            if(c.End()) {
                Intent i = new Intent(getActivity(),ItemActivity.class);
                i.putExtra(ItemActivity.EXTRA_PROJECT_ID, c.getId());
                i.putExtra(ItemActivity.EXTRA_ITEM_ID, _itemInfo.getId());
                startActivity(i);
            }

        }
        else
        {
            c.Start();
            //v.setBackgroundColor(Color.RED);
        }
        cAdper.notifyDataSetChanged();
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
            TextView title_text = (TextView)convertView.findViewById(R.id.list_project_name);
            TextView detail_text = (TextView)convertView.findViewById(R.id.list_project_detail);
            TextView time_text = (TextView)convertView.findViewById(R.id.list_project_time);
            ProjectInfo projectInfo = getItem(position);
            title_text.setText(projectInfo.getTitle());
            detail_text.setText(projectInfo.getDetail());
            time_text.setText(projectInfo.getCostTimeStr());
            if(projectInfo.isStart())
            {
                convertView.setBackgroundColor(Color.RED);
            }
            else
            {
                convertView.setBackgroundColor(Color.WHITE);
            }
            return convertView;
        }
    }
}
