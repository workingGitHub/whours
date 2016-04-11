package com.instance.working.whours.controller;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.instance.working.whours.R;
import com.instance.working.whours.model.DataListFactory;
import com.instance.working.whours.model.ItemInfo;
import com.instance.working.whours.model.ProjectInfo;

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

    public ItemListFragment() {
        this._projcetinfo = null;
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
