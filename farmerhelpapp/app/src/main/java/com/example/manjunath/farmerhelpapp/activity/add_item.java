package com.example.manjunath.farmerhelpapp.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.manjunath.farmerhelpapp.R;
import com.example.manjunath.farmerhelpapp.others.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class add_item extends AppCompatActivity {

    private EditText inputname, inputcontactno,inputdistrict,inputstate,inputpincode;
    private ImageButton addimage;
    private Button camera,gallery;
    private Button upload;
    private ImageView mimageview;
    private Uri mimageuri;

    private DatabaseReference mdatabaseref;
    private StorageReference mstorageref;

    private StorageTask muploadtask;

    private static final int PICK_IMAGE_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        inputname = (EditText) findViewById(R.id.name);
        inputcontactno = (EditText) findViewById(R.id.contactno);
        inputdistrict = (EditText) findViewById(R.id.district);
        inputpincode = (EditText) findViewById(R.id.pincode);
        addimage = (ImageButton) findViewById(R.id.addimage);
        gallery = (Button)findViewById(R.id.gallery);
        upload = (Button)findViewById(R.id.submit);
        mimageview = (ImageView)findViewById(R.id.image_view);

        mdatabaseref = FirebaseDatabase.getInstance().getReference("uploads");
        mstorageref = FirebaseStorage.getInstance().getReference("uploads");

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(add_item.this);
                View mView = getLayoutInflater().inflate(R.layout.add_image_dialog, null);
                final Button camera = (Button) mView.findViewById(R.id.camera);
                final Button gallery = (Button) mView.findViewById(R.id.gallery);

                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(muploadtask!=null && muploadtask.isInProgress()){
                            Toast.makeText(add_item.this,"Upload in progress",Toast.LENGTH_SHORT).show();
                        }else {
                            uploadfile();
                            OpenImageActivity();
                        }

                    }
                });



                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openfilechooser();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });



    }

    private void OpenImageActivity() {
        Intent intent = new Intent(this,imagesActivity.class);
        startActivity(intent);
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr =getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadfile() {
        if(mimageuri!=null && inputname!=null && inputcontactno!=null && inputdistrict!=null && inputpincode!=null){
            StorageReference fileReference = mstorageref.child(System.currentTimeMillis()+"."+getFileExtension(mimageuri));
            muploadtask=fileReference.putFile(mimageuri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String uploadId = mdatabaseref.push().getKey();
                    Upload upload = new Upload(inputname.getText().toString(),inputcontactno.getText().toString(),
                            inputdistrict.getText().toString(),inputpincode.getText().toString(),taskSnapshot.getDownloadUrl().toString());
                    mdatabaseref.child(uploadId).setValue(upload);
                    Toast.makeText(add_item.this,"Upload Successful",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
               Toast.makeText(add_item.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(this, "Blank fields present", Toast.LENGTH_SHORT).show();
        }
    }

    private void openfilechooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            mimageuri = data.getData();
            Picasso.with(this).load(mimageuri).into(mimageview);
        }
    }
}
