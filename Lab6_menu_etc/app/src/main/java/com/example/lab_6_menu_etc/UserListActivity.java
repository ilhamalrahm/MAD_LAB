package com.example.lab_6_menu_etc;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ListView listView;
    private SQLiteDatabase database;

    private TextView tv;

    private SharedPreferences sharedPreferences;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView = findViewById(R.id.listView);
        database = openOrCreateDatabase("users.db", MODE_PRIVATE, null);
        sharedPreferences=getSharedPreferences("prefs", Context.MODE_PRIVATE);
        tv=findViewById(R.id.textView);

        String s=sharedPreferences.getString("toggle state","");

        tv.setText(s);


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
//        database.delete("users2","id = ?",new String[]{p});


        int count=0;
        Cursor cursor = database.rawQuery("SELECT * FROM users2", null);


        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            if(count==position)
            {
                database.delete("users2","name = ?",new String[]{name});
                MainActivity.ID=MainActivity.ID-1;
                break;
            }
            count++;
        }
        cursor.close();
    }
}