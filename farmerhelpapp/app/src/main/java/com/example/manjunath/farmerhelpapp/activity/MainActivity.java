package com.example.manjunath.farmerhelpapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.manjunath.farmerhelpapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    TextView txtslogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        txtslogan = (TextView)findViewById(R.id.txtslogin);


        //Typeface face = Typeface.createFromAsset(getAssets(),"fonts/NABILA.rar");
        //txtslogan.setTypeface(face);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginactivity = new Intent(MainActivity.this, loginactivity1.class);
                startActivity(loginactivity);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupactivity1 = new Intent(MainActivity.this, googlesignup.class);
                startActivity(signupactivity1);
            }
        });
    }
}
