package com.example.l3q2_grocery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String list[]={"Banana","Apple","Orange","Mango"};

        TextView tv=(TextView) findViewById(R.id.textView);

        ListView lv=(ListView) findViewById(R.id.list);
        ArrayAdapter<String> arr=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(arr);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String k="The Selected item is : "+ adapterView.getItemAtPosition(i).toString();
                tv.setText(k);
            }
        });

    }
}