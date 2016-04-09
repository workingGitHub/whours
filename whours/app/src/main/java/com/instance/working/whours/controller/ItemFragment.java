package com.instance.working.whours.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.instance.working.whours.R;
import com.instance.working.whours.model.DataListFactory;
import com.instance.working.whours.model.ItemInfo;
import com.instance.working.whours.model.ProjectInfo;

import java.util.UUID;

/**
 * Created by Administrator on 2016/1/28 0028.
 */
public class ItemFragment extends Fragment {
    public static final String EXTRA_PROJECT_ID = "com.instance.working.whours.controlle.ItemFragment.EXTRA_PROJECT_ID";
    public static final String EXTRA_ITEM_ID = "com.instance.working.whours.controlle.ItemFragment.EXTRA_ITEM_ID";

    public ProjectInfo _projcetinfo;
    public ItemInfo _iteminfo;

    public ItemFragment() {
        _iteminfo = null;
        _projcetinfo = null;
    }

    public static ItemFragment newInstance(UUID projectid,UUID itemid)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_PROJECT_ID, projectid);
        args.putSerializable(EXTRA_ITEM_ID, itemid);

        ItemFragment c = new ItemFragment();
        c.setArguments(args);
        return c;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID pid = (UUID) getArguments().getSerializable(EXTRA_PROJECT_ID);
        UUID iid = (UUID) getArguments().getSerializable(EXTRA_ITEM_ID);

        setHasOptionsMenu(true);

        if((pid != null)&&(iid != null))
        {
            _projcetinfo = DataListFactory.get(getActivity()).getProjectItem(pid);
            if(_projcetinfo != null)
            {
                _iteminfo = _projcetinfo.getItemInfo(iid);
            }
        }

        if(_iteminfo == null)
        {
            // Ö±½Ó·µ»Ø
            Log.e("working",String.format("_iteminfo is null!"));
            getActivity().finish();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _v = inflater.inflate(R.layout.layout_iteminfo,container,false);

        TextView _project_name = (TextView)_v.findViewById(R.id.item_projecttitle);
        _project_name.setText(_projcetinfo.getTitle());
        TextView _starttime = (TextView)_v.findViewById(R.id.item_starttime);
        _starttime.setText(_iteminfo.getStartTime().toString());
        TextView _endtime = (TextView)_v.findViewById(R.id.item_endtime);
        _endtime.setText(_iteminfo.getEndTime().toString());

        final EditText _costtime = (EditText)_v.findViewById(R.id.item_costtime);
        final EditText _itemdetail = (EditText)_v.findViewById(R.id.item_detail);
        _costtime.setText(String.format("%d", _iteminfo.getMaxCostTime()));
        _itemdetail.setText(_iteminfo.getDetail());


        _costtime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    return ;
                }
                long i = Long.parseLong(s.toString());
                _iteminfo.setCostTime(i);
                if (i > _iteminfo.getMaxCostTime()) {
                    Toast.makeText(getActivity(),R.string.err_exceedmaxcosttime,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        _itemdetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _iteminfo.setDetail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return _v;
    }
}

