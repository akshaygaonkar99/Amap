package com.example.manjunath.farmerhelpapp.activity;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.manjunath.farmerhelpapp.R;
import com.example.manjunath.farmerhelpapp.others.ImageAdapter;
import com.example.manjunath.farmerhelpapp.others.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class imagesActivity extends AppCompatActivity {

    private RecyclerView mrecyclerview;
    private ProgressBar mprogressbar;
    private ImageAdapter mAdapter;
    private DatabaseReference mDatabaseRef;
    private List<Upload> muploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mrecyclerview = findViewById(R.id.recycleview);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        muploads = new ArrayList<>();
        mprogressbar = findViewById(R.id.progress_circle);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    muploads.add(upload);
                }
                mAdapter = new ImageAdapter(imagesActivity.this,muploads);
                mrecyclerview.setAdapter(mAdapter);
                mprogressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(imagesActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mprogressbar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
