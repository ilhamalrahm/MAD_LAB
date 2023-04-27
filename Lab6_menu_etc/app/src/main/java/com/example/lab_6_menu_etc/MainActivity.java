package com.example.lab_6_menu_etc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import com.example.lab_6_menu_etc.DBHelper;

public class MainActivity extends AppCompatActivity {
    public static int ID=0;



    private EditText nameEditText, emailEditText;
    private Button submitButton, showButton;
    private ToggleButton tb;
    private SharedPreferences sharedPreferences;

    private SQLiteDatabase database;

    private List<User> userList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        submitButton = findViewById(R.id.submitButton);
        showButton = findViewById(R.id.showButton);
        tb=findViewById(R.id.toggleButton);
        sharedPreferences=getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        String togglestate=sharedPreferences.getString("toggle state","");
        System.out.println(togglestate);

        if(togglestate.equals("Toggle button is on"))
        {
            tb.setChecked(true);
        }
        else {
            tb.setChecked(false);
        }



//        DBHelper.InitDB();

        // create the database
        database = openOrCreateDatabase("users.db", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS users2 (id Integer,name VARCHAR, email VARCHAR)");

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putString("toggle state","Toggle button is on");
                    editor.apply();
                } else {
                    // Toggle is OFF
                    editor.putString("toggle state","Toggle button is off");
                    editor.apply();
                }
            }


        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                int id=ID;
                ID=ID+1;

                // insert user into the database
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("email", email);
                values.put("id", id);
//                DBHelper.Insert("users",values);
                database.insert("users2", null, values);

                Toast.makeText(MainActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();

                // clear the input fields
                nameEditText.setText("");
                emailEditText.setText("");
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get all users from the database
                Cursor cursor = database.rawQuery("SELECT * FROM users2", null);

                List<User> users = new ArrayList<>();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String email = cursor.getString(cursor.getColumnIndex("email"));
                    User user = new User(id,name, email);
                    users.add(user);
                }
                cursor.close();

                // pass the list of users to the next activity
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                intent.putExtra("users", users.toArray(new User[0]));
                startActivity(intent);
            }
        });
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}