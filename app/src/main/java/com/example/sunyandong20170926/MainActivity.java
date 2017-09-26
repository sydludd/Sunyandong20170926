package com.example.sunyandong20170926;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener{
private  XListView Myxlist;
    private int pageIndex = 1;
    //默认值是false
    private boolean flag;
    private Myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Myxlist= (XListView) findViewById(R.id.xListView);
        Myxlist.setXListViewListener(MainActivity.this);
        Myxlist.setPullRefreshEnable(true);
        Myxlist.setPullLoadEnable(true);
    }
    private void getData(){

        try {
            getNweWork("http://apis.juhe.cn/cook/query.php?" +
                    "key=6787f0fab124bcf9149a5fec0aba7a86&menu="+
                    URLEncoder.encode("秘制红烧肉","utf-8")+"&pn"+pageIndex+"&rn="+10);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
   private void getNweWork(String url){

       new AsyncTask<String, Void, String>() {

           @Override
           protected void onPostExecute(String s) {
               super.onPostExecute(s);
               if (s==null){
                   return;
               }
               Gson gson=new Gson();
               MuneInf meinfo = gson.fromJson(s, MuneInf.class);
               List<MuneInf.ResultBean.DataBean> dataBeen=meinfo.getResult().getData();

               if (adapter==null){
                   adapter=new Myadapter(MainActivity.this,dataBeen);
                   Myxlist.setAdapter(adapter);

               }else{
                   adapter.Lode(dataBeen,flag);
               }

           }

           @Override
           protected String doInBackground(String... strings) {
               try {
                   URL url=new URL(strings[0]);
                   HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                   connection.setRequestMethod("GET");
                   connection.setConnectTimeout(5000);
                   connection.setReadTimeout(5000);

                   int code=connection.getResponseCode();
                   if (code==200){
                       InputStream inputStream = connection.getInputStream();
                       return getStriong.readFromNetWork(inputStream);
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
               return null;
           }
       }.execute(url);
   }


    @Override
    public void onRefresh() {

        Log.d("MainActivity", "下拉刷新");
        //pageIndex = 1 -> 2
        flag = false;
        ++pageIndex;
        getData();
        Myxlist.stopRefresh();
    }

    @Override
    public void onLoadMore() {
        Log.d("MainActivity", "上拉加载更多");
        flag = true;
        ++pageIndex;
        getData();
        //停止加载更多
        Myxlist.stopLoadMore();
    }
}
