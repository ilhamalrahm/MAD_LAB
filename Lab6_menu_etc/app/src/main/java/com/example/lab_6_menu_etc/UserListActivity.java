package com.example.lab_6_menu_etc;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ListView listView;
    private SQLiteDatabase database;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView = findViewById(R.id.listView);
        database = openOrCreateDatabase("users.db", MODE_PRIVATE, null);

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
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete:
                deleteUser(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteUser(int position) {
        String p=Integer.toString(position);
        database.delete("users2","id = ?",new String[]{p});
        MainActivity.ID=MainActivity.ID-1;
    }
}