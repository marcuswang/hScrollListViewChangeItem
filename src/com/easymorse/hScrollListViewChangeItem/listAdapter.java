package com.easymorse.hScrollListViewChangeItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import it.sephiroth.android.library.widget.BaseAdapterExtended;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangjun
 * Date: 13-6-3
 * Time: 下午2:07
 * To change this template use File | Settings | File Templates.
 */
public class listAdapter extends BaseAdapterExtended {
    private Context mcontext;
    private List dataList=new ArrayList();
    public  listAdapter(Context context,List list){
         this.mcontext=context;
         this.dataList= list;
    }

    @Override
    public int getCount() {
        return dataList.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int position) {
        return position;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getItemViewType( int position ) {
        if ( getViewTypeCount() > 1 ) {
            Map map=(Map)getItem( position );
            return Integer.parseInt((String)map.get("key"));
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //一共有三种模式  第一种是 只有日期的不能点击的 第二种是有文章的日期 是可以点击的  第三种是有文章详细的 可以点击

        View view=null;
        int viewType=getItemViewType( position );

        Log.d("tag","viewType-->>>>"+viewType);
        Log.d("tag","convertView-->>>>"+convertView);

        if ( convertView == null ) {
            if (viewType==0)
            {
                view = LayoutInflater.from(mcontext).inflate( R.layout.view1, parent, false );
            }else  if (viewType==1){
                view = LayoutInflater.from(mcontext).inflate( R.layout.view2, parent, false );
            }else  if (viewType==2){
                view = LayoutInflater.from(mcontext).inflate( R.layout.view3, parent, false );
            }
        } else {
            view = convertView;

        }

        if (viewType==1)
        {
            final int oldType=viewType;
            Button mButton=(Button)view.findViewById(R.id.button);
            final  View vi=view;
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replace(position,oldType);
                }
            });

        }else if (viewType==2)
        {
            final int oldType=viewType;
            Button mButton=(Button)view.findViewById(R.id.backbutton);
            final  View vi=view;
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replace(position,oldType);
                }
            });

        }

        return view;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void replace(int position,int oldViewType) {
        synchronized ( dataList ) {
            this.dataList.remove( position );
            if (oldViewType==1){
            Map dataMap=new HashMap<String,String>();
                dataMap.put("key","2");
                dataMap.put("value", "这里是文章的详细页面");
                this.dataList.add(position, dataMap);
            }else if (oldViewType==2){
                Map dataMap=new HashMap<String,String>();
                dataMap.put("key","1");
                dataMap.put("value", "可以点击的文章，哈哈哈");
                this.dataList.add(position, dataMap);
            }

        }
        notifyDataSetReplaced(position, oldViewType);
    }
}
