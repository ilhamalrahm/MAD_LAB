package com.example.l3q1_grid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] list = {"Dogs", "cats", "Phones"};

        ListView l = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> arr = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        l.setAdapter(arr);

        GridView g=(GridView)findViewById(R.id.Grid);

        Adapter imgAdapter=new Adapter(this);
        g.setAdapter(imgAdapter);




    }
}
