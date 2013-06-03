package com.easymorse.hScrollListViewChangeItem;

import android.app.Activity;
import android.os.Bundle;
import it.sephiroth.android.library.widget.HorizontalVariableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HScrollListViewChangeItem extends Activity {
    private HorizontalVariableListView hListView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        hListView=(HorizontalVariableListView)findViewById(R.id.list);


        ////////////////////////编辑 用户信息

        List dataList=new ArrayList();

        for (int i=0;i<40;i++)
        {
            Map dataMap=new HashMap<String,String>();
               if (i%5==0){
                   dataMap.put("key","1");
                   dataMap.put("value","可以点击的文章，哈哈哈");
               }else {
                   dataMap.put("key","0");
                   dataMap.put("value","我这里是日期,不可以点击");
               }
            dataList.add(dataMap);
        }

        listAdapter listAdapter=new listAdapter(this,dataList);
        hListView.setAdapter(listAdapter);

    }
}
