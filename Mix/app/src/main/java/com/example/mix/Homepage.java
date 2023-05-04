package com.example.mix;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

public class Homepage extends AppCompatActivity {

    SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.mix/database/mix.db",null,null);

    SharedPreferences prefs;
    SharedPreferences.Editor editprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editprefs= prefs.edit();

        Cursor cursor=db.rawQuery("Select name from User",null);
        cursor.moveToLast();
        String name=cursor.getString(0);
        cursor.close();
        editprefs.putString("name",name);
        editprefs.apply();
        TextView n=(TextView)findViewById(R.id.Welcome);
        String welcomeMes="Welcome "+ name;

        n.setText(welcomeMes);
        Button b = (Button) findViewById(R.id.button2);

        registerForContextMenu(b);


//        PopupMenu p=new PopupMenu(Homepage.this,b);
//        Toolbar t=(Toolbar)findViewById(R.id.toolbar3) ;





//        p.getMenuInflater().inflate(R.menu.popup,p.getMenu());

//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p.show();
//            }
//        });



//        MenuInflater menuInflater=getMenuInflater();
//        menuInflater.inflate(R.menu.popup,p);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.cont1:
//                Cursor cursor= db.rawQuery("delete from User where n",);
                Cursor cursor=db.rawQuery("Select name from User",null);
                cursor.moveToFirst();
                String name=cursor.getString(0);
                String[] names={name};
                db.delete("User","name=?",names);

                return true;
            case R.id.cont2:
//                deleteNote(info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }




}