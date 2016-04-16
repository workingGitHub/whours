package com.instance.working.whours.controller;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 * 显示 List列表，数据的真正控制类
 */
public class ItemListFragment extends ListFragment {
    public static final String EXTRA_PROJECT_ID = "com.instance.working.whours.controlle.ItemListFragment.EXTRA_PROJECT_ID";
    public ProjectInfo _projcetinfo;

    public static ItemListFragment newInstance(UUID projectid)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_PROJECT_ID, projectid);

        ItemListFragment c = new ItemListFragment();
        c.setArguments(args);
        return c;
    }

    @Override
    public void onPause() {
        super.onPause();
        DataListFactory.get(getActivity()).onSave();
    }

    public ItemListFragment() {
        this._projcetinfo = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ItemAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID) getArguments().getSerializable(EXTRA_PROJECT_ID);
        if(id != null)
        {
            _projcetinfo = DataListFactory.get(getActivity()).getProjectItem(id);
        }

        if(_projcetinfo != null) {
            ItemAdapter adpter = new ItemAdapter(_projcetinfo.getItemList());
            setListAdapter(adpter);
        }
        //保存临时信息
        setRetainInstance(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ItemAdapter cAdper = (ItemAdapter)getListAdapter();
        ItemInfo c = cAdper.getItem(position);
        Intent i;
        if(c != null) {
            i = new Intent(getActivity(), ItemActivity.class);
            i.putExtra(ItemActivity.EXTRA_PROJECT_ID, c.getProjectId());
            i.putExtra(ItemActivity.EXTRA_ITEM_ID, c.getId());
            startActivity(i);
        }
    }

    private class ItemAdapter extends ArrayAdapter<ItemInfo>
    {
        public ItemAdapter(ArrayList<ItemInfo> projectInfos)
        {
            super(getActivity(),0,projectInfos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_itemlist_item,null);
            }
            TextView starttime_text = (TextView)convertView.findViewById(R.id.list_item_starttime);
            TextView detail_text = (TextView)convertView.findViewById(R.id.list_item_detail);
            TextView time_text = (TextView)convertView.findViewById(R.id.list_item_time);
            ItemInfo itemInfo = getItem(position);

            starttime_text.setText(itemInfo.getStartTimestr("yyyy.MM.dd"));
            detail_text.setText(itemInfo.getDetail());
            time_text.setText(itemInfo.getCostTimeStr());

            return convertView;
        }
    }
}
