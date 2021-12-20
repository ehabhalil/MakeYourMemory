package com.example.mym.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mym.R;
import com.example.mym.model.user.User;
import com.example.mym.server.Constants;
import com.example.mym.server.Server;
import com.example.mym.session.CreateNewPost;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private static final int PICK_IMAGE_REQUEST = 1;

    private Button register;
    private EditText userName;
     EditText password,password2;
     ImageView ImageView;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

    private String imageUrl;
    private int loaderID = 1236;

    Server server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        register = findViewById(R.id.submitRegistration);
        userName = findViewById(R.id.userNameRegistration);
        password = findViewById(R.id.passwordRegistration);
        password2 = findViewById(R.id.verifyPasswordRegistration);
        ImageView = findViewById(R.id.imageViewRegistration);

        mStorageRef = FirebaseStorage.getInstance().getReference("avatars");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("avatars");

        Picasso.get().load(mImageUri).placeholder(R.mipmap.ic_launcher).into(ImageView);

        ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(
                                !password.getText().toString().equals(password2.getText().toString())||
                                userName.getText().toString().equals("")||
                                password.getText().toString().equals("")
                ){
                    Toast.makeText(SignUpActivity.this, "please fill all fields correctly", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if (mUploadTask != null && mUploadTask.isInProgress()) {
                        Toast.makeText(SignUpActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            uploadFile();
                        } catch (Exception e) {
                            Toast.makeText(SignUpActivity.this, "user could not be created", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(ImageView);
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                            taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imageUrl = uri.toString();
                                    SignUpActivity.this.getSupportLoaderManager().initLoader(loaderID,null,SignUpActivity.this).forceLoad();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });
        } else {
            Toast.makeText(this, "No avatar selected", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        HashMap<String,String> bodyRequest = new HashMap<String,String>();
        bodyRequest.put("userName", userName.getText().toString());
        bodyRequest.put("password", password.getText().toString());
        bodyRequest.put("avatar", imageUrl);
        server = new Server(this, Constants.CREATE_NEW_USER, "POST",bodyRequest);
        return server;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(server.getStatusCode() != 201){
            Toast.makeText(this, "user already exists", Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}