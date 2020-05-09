package com.anddev.docviewer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    TextView filepath;
    ImageView imageView;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filepath = findViewById(R.id.path);
        imageView = findViewById(R.id.add_file);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 10);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 10:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String returnUri = uri.toString();
                    Log.d("Uri", returnUri);
                    String mimeType = getContentResolver().getType(uri);
                    Log.d("Mime Type", mimeType);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri,mimeType);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if(intent.resolveActivity(getPackageManager()) != null)
                        startActivity(Intent.createChooser(intent,"Open File with"));
                    else
                        Toast.makeText(this, "No app found for opening this document", Toast.LENGTH_SHORT).show();

                    break;
                }

        }

    }

}

