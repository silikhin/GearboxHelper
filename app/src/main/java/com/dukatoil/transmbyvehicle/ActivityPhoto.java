package com.dukatoil.transmbyvehicle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class ActivityPhoto extends AppCompatActivity {

    String extra;
    ImageView imageView;
    int photoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Intent intent = getIntent();
        extra = intent.getStringExtra("photo");
        Log.d("myLogs", "extra = " + extra);
        photoID = getResources().getIdentifier("ic_" + extra, "drawable", getPackageName());
        imageView = (ImageView) findViewById(R.id.imageViewFilter);
        Log.d("myLogs", "photoID = " + photoID);

        ImageDecodeTask idt = new ImageDecodeTask(this);
        idt.execute(photoID);
        try {
            imageView.setImageBitmap(idt.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
