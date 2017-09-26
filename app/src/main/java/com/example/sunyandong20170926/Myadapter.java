package com.example.sunyandong20170926;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 悻 on 2017/9/26.
 */

public class Myadapter  extends BaseAdapter{
    private List<MuneInf.ResultBean.DataBean> dataBeanList;
    private Context context;
    public Myadapter(Context context, List<MuneInf.ResultBean.DataBean> dataBeanList){
        this.context = context;
        this.dataBeanList = dataBeanList;
    }
    public void Lode(List<MuneInf.ResultBean.DataBean> datas,boolean flag){


        for (MuneInf.ResultBean.DataBean dataBean : datas) {

            //flag true 数据要添加到最前面  false要添加到最后面
            if(flag){
                dataBeanList.add(dataBean);
            }else {
                //首先我把数据都添加到最前面
                dataBeanList.add(0, dataBean);
            }


        }
        //刷新界面
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return dataBeanList != null ? dataBeanList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return dataBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){

            view = view.inflate(context,R.layout.item,null);

        }

        ImageView ivIcon = (ImageView) view.findViewById(R.id.iamge);
        TextView tvContent = (TextView) view.findViewById(R.id.textview);
        //设置图片以及文字信息

        tvContent.setText(dataBeanList.get(i).getTitle());

//        ImageLoader.getInstance().displayImage(dataBeanList.get(i).getAlbums().get(0),
//                ivIcon,MyApplication.getDisplayImageOptions());
        return view;
    }
}



