# whours

制作一个工作 日志记录APP

工作日志

## 2016-4-6：完成上下文菜单的实现。

通过fragment实现上下文菜单

第一步：创建上下文菜单

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_projectlist_context, menu);
        //super.onCreateContextMenu(menu, v, menuInfo);
    }

第二步：实现按键后功能

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //因为ListView是AdpterView的子类，所以getmenuInfo返回的为AdpaterContextmenuInfo
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int postion = info.position;
        ProjectAdapter cAdper = (ProjectAdapter)getListAdapter();
        ProjectInfo c = cAdper.getItem(postion);
        switch (item.getItemId())
        {
            case R.id.menu_delete_projectinfo:
                DataListFactory.get(getActivity()).DelProject(c);
                cAdper.notifyDataSetChanged();
                return true;
            case R.id.menu_change_projectinfo:
                Intent i = new Intent(getActivity(),ProjectActivity.class);
                i.putExtra(ProjectActivity.EXTRA_PROJECT_ID, c.getId());
                startActivity(i);
                return true;
            default :
                break;
        }
        return super.onContextItemSelected(item);
    }


第三步：将上下文菜单与list联动


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 注册上下文状态
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ListView listview = (ListView)v.findViewById(android.R.id.list);
        listview.setBackgroundResource(R.drawable.projectinfo_background);
        registerForContextMenu(listview);
        return v;
    }

## 2016-4-8：
>tips:时间的计算方法
>
>当前时间的获取 Date curdate = new Date
>
>时间差的计算  long diff = a.gettime() - b.gettime() 
>
>间隔为毫秒

## 2016-4-10
完成item的详细页面设计

下一步计划完成itemlist页面的设计