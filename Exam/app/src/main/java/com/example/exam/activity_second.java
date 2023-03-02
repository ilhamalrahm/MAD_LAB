package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exam.R;

import java.sql.Array;
import java.util.ArrayList;

public class activity_second extends AppCompatActivity {

    ListView listView;
    Button backButton;

    ArrayList<String> listData = new ArrayList<>();
    ArrayList<Boolean> boolData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView = findViewById(R.id.list_view);
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_second.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        listData = (ArrayList<String>) intent.getSerializableExtra("StrList");
        boolData = (ArrayList<Boolean>) intent.getSerializableExtra("BoolList");

        for (int i = 0; i < listData.size(); i++) {
            if (boolData.get(i) == true) {
                String originalStr = listData.get(i);
                String reversedStr = "";
                for (int j = 0; j < originalStr.length(); j++) {
                    reversedStr = originalStr.charAt(j) + reversedStr;
                }
                listData.set(i, reversedStr);
            }
        }



        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String message = listData.get(i);
                Toast.makeText(activity_second.this, message + " clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

}