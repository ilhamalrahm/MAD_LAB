package com.example.l3q1_grid;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Adapter extends BaseAdapter {

    private Context mycontext;
     Integer [] pics={
             R.drawable.crisis,
             R.drawable.indian,
             R.drawable.security,
             R.drawable.social,
             R.drawable.un,
             R.drawable.uny
     };

    public Adapter(Context context)
    {
        this.mycontext=context;
    }

    @Override
    public int getCount() {
        return pics.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return pics[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        ImageView imgView=new ImageView(mycontext);




        imgView.setImageResource(pics[i]);
        imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300,300);
        lp.setMargins(0, 200, 0, 200);
        imgView.setLayoutParams(lp);
//        imgView.setLayoutParams(new GridView.LayoutParams(300,300));

//        imgView.setPadding(0,100,0,100);


        return imgView;

    }
}
