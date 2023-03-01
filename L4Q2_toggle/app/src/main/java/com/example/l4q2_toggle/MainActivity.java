package com.example.l4q2_toggle;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt=(Button) findViewById(R.id.button);
        ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton);

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    LayoutInflater inflater= getLayoutInflater();
                    View layout=inflater.inflate(R.layout.custom_layout,null);

                    ImageView img=(ImageView) layout.findViewById(R.id.img);
                    TextView text=(TextView) layout.findViewById(R.id.txt);
                    img.setImageResource(R.drawable.crisis);
                    text.setText("Toggle button is Checked!");

                    Toast toast=new Toast(MainActivity.this);

                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);

                    toast.show();
                }
                else{
                    LayoutInflater inflater= getLayoutInflater();
                    View layout=inflater.inflate(R.layout.custom_layout,null);

                    ImageView img=(ImageView) layout.findViewById(R.id.img);
                    TextView text=(TextView) layout.findViewById(R.id.txt);
                    img.setImageResource(R.drawable.crisis);
                    text.setText("Toggle Button is Unchecked!");

                    Toast toast=new Toast(MainActivity.this);

                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);

                    toast.show();
                }
            }
        });



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Hello there!",Toast.LENGTH_LONG).show();
                LayoutInflater inflater= getLayoutInflater();
                View layout=inflater.inflate(R.layout.custom_layout,null);

                ImageView img=(ImageView) layout.findViewById(R.id.img);
                TextView text=(TextView) layout.findViewById(R.id.txt);
                img.setImageResource(R.drawable.crisis);
                text.setText("Button is Clicked!");

                Toast toast=new Toast(MainActivity.this);

                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);

                toast.show();

            }
        });
    }
}