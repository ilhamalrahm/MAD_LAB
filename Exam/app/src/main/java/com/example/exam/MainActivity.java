package com.example.exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exam.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Private members of the class:
    ArrayList<String> listData = new ArrayList<String>();

    int counter = 1;    // range of [1-3]
    boolean canSetVisible = false;



    // Reference variables:
    EditText inputField;
    Button addButton;
    Button displayButton;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references:
        inputField = findViewById(R.id.text_field);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(MainActivity.this);

        displayButton = findViewById(R.id.display_button);
        displayButton.setOnClickListener(MainActivity.this);

        checkBox1 = findViewById(R.id.checkbox_1);
        checkBox2 = findViewById(R.id.checkbox_2);
        checkBox3 = findViewById(R.id.checkbox_3);

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(MainActivity.this);

        // Hide the checkboxes and submit button:
        checkBox1.setVisibility(View.INVISIBLE);
        checkBox2.setVisibility(View.INVISIBLE);
        checkBox3.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_button:       // Add a string to the list, increment counter
                if (counter < 4) {
                    String text = inputField.getText().toString();
                    listData.add(text);
                    counter += 1;
                    inputField.setText("");
                    Toast.makeText(MainActivity.this,"Added to list", Toast.LENGTH_SHORT).show();
                }
                else {
                    HideInputFields();
                    canSetVisible = true;
                }
                break;

            case R.id.display_button:
                SetCheckboxContents();
                SetChecksVisible();
                break;

            case R.id.submit_button:
                new AlertDialog.Builder(this)
                        .setTitle("Confirm Selections")
                        .setMessage("Are you sure you want to submit?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // todo: yes
                                Intent intent = new Intent(MainActivity.this, activity_second.class);
                                intent.putExtra("StrList", listData);

                                ArrayList<Boolean> boolList = new ArrayList<>();
                                boolList.add(checkBox1.isChecked());
                                boolList.add(checkBox2.isChecked());
                                boolList.add(checkBox3.isChecked());

                                intent.putExtra("BoolList", boolList);
                                startActivity(intent);

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
        }
    }


    // Hides input fields, after three items were entered
    private void HideInputFields() {
        inputField.setEnabled(false);
        inputField.setClickable(false);
        inputField.setFocusable(false);

        addButton.setClickable(false);
    }


    private void SetChecksVisible() {
        checkBox1.setVisibility(View.VISIBLE);
        checkBox2.setVisibility(View.VISIBLE);
        checkBox3.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }

    private void SetCheckboxContents() {

        for(String i:listData)
        {
            checkBox1.setText(i);
        }
//        checkBox1.setText("abc");
//        checkBox2.setText("def");
//        checkBox3.setText("hij");
    }


}