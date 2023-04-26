package com.example.lab_6_menu_etc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView = findViewById(R.id.listView);

        // get the list of users from the previous activity
        User[] users = (User[]) getIntent().getSerializableExtra("users");

        // create an array list to hold the list of users
        ArrayList<User> userList = new ArrayList<>();

        for (User user : users) {
            userList.add(user);
        }

        // create an array adapter to display the list of users in the ListView
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listView.setAdapter(adapter);
    }
}