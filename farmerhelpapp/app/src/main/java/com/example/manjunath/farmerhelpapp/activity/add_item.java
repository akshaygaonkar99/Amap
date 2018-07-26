package com.example.manjunath.farmerhelpapp.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.manjunath.farmerhelpapp.R;

public class add_item extends AppCompatActivity {

    private EditText inputname, inputcontactno,inputdistrict,inputstate,inputpincode;
    private ImageButton addimage;
    private Button camera,gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        inputname = (EditText) findViewById(R.id.name);
        inputcontactno = (EditText) findViewById(R.id.contactno);
        inputdistrict = (EditText) findViewById(R.id.district);
        inputstate = (EditText) findViewById(R.id.state);
        inputpincode = (EditText) findViewById(R.id.pincode);
        addimage = (ImageButton) findViewById(R.id.addimage);

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(add_item.this);
                View mView = getLayoutInflater().inflate(R.layout.add_image_dialog, null);
                final Button camera = (Button) mView.findViewById(R.id.camera);
                final Button gallery = (Button) mView.findViewById(R.id.gallery);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

    }
}
