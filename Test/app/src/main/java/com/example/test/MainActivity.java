package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private RadioGroup colorRadioGroup;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name_edit_text);
        colorRadioGroup = findViewById(R.id.color_radio_group);
        nextButton = findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected color
                int selectedColorId = colorRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedColorRadioButton = findViewById(selectedColorId);
                int selectedColor = selectedColorRadioButton.getHighlightColor();

                // Get the entered name
                String name = nameEditText.getText().toString();

                // Start the secondary activity
                Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("color", selectedColor);
                startActivity(intent);
            }
        });
    }
}