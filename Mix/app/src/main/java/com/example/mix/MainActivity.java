package com.example.mix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    EditText edittext;
    RadioButton male;
    RadioButton female;
    CheckBox wcc;
    CheckBox mad;
    Switch sw;
    Spinner country;
    Button submit;
    TextView last;

    String name;
    String gender;
    ArrayList<String> subjects=new ArrayList<String>();
    String beastness="Lame";
    String nation;
    SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.mix/database/mix.db",null,null);

    SharedPreferences prefs;
    SharedPreferences.Editor editprefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs=getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editprefs= prefs.edit();
        String CreateTableQuery="Create table if not exists User(name VARCHAR,gender VARCHAR,beastness VARCHAR,nation VARCHAR)";
        db.execSQL(CreateTableQuery);
        String[] countries ={"India","Usa","Australia"};

        country=(Spinner)findViewById(R.id.country);
        submit=(Button)findViewById(R.id.button);
        sw=(Switch)findViewById(R.id.switch1) ;
        edittext=(EditText)findViewById(R.id.name);
        wcc=(CheckBox)findViewById(R.id.wcc);
        mad=(CheckBox)findViewById(R.id.MAD);
        male=(RadioButton)findViewById(R.id.Male);
        female=(RadioButton)findViewById(R.id.Female);
        last=(TextView)findViewById(R.id.last);

        String lastadded="You last added : "+prefs.getString("name","Unavailable");

        last.setText(lastadded);



        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setChecked(false);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setChecked(false);
            }
        });



        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,countries);
        country.setAdapter(adapter);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               name= edittext.getText().toString();
               if(wcc.isChecked())
               {
                   subjects.add("WCC");
               }
                if(mad.isChecked())
                {
                    subjects.add("MAD");
                }
                if(sw.isChecked())
                {
                    beastness="Beast Mode";
                }
                else {
                   beastness= "Lame";
                }
                gender=male.isChecked()?"Male":"Female";
                nation=country.getSelectedItem().toString();



                System.out.println(name);
                System.out.println(beastness);
                System.out.println(nation);
                System.out.println(gender);
                ContentValues values=new ContentValues();

                values.put("name",name);
                values.put("gender",gender);
                values.put("beastness",beastness);
                values.put("nation",nation);

                db.insert("User",null,values);

                Intent i=new Intent(MainActivity.this,Homepage.class);
                startActivity(i);




            }
        });

    }

    protected void onResume()
    {
        super.onResume();
        last=(TextView)findViewById(R.id.last);
        prefs=getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editprefs= prefs.edit();

        String lastadded="You last added : "+prefs.getString("name","Unavailable");

        last.setText(lastadded);
        System.out.println(prefs.getString("name","Unavailable"));




    }
}