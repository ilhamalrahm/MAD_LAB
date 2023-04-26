package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {
    private TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        nameTextView = findViewById(R.id.name_text_view);

        // Get the name and color from the intent extras
        String name = getIntent().getStringExtra("name");
        int color = getIntent().getIntExtra("color", R.color.black);

        // Highlight the vowels in the specified color
        SpannableString spannableString = new SpannableString(name);
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (isVowel(c)) {
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
                spannableString.setSpan(colorSpan, i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        nameTextView.setText(spannableString);
    }

    // Helper function to check if a character is a vowel
    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}