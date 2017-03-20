package com.dukatoil.transmbyvehicle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class ActivityTransmission extends AppCompatActivity {

    TextView tvTransmission;
    ListView lvAbout;
    String[] title, components;
    String extra; // extra from ActivityVariants (like "5hp19")
    String res_transmission; //resource array name
    int connector; // resource Id for String array

//    TODO: add ImageView for Transmission
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmission);

        tvTransmission = (TextView) findViewById(R.id.tvTransmission);
        lvAbout = (ListView) findViewById(R.id.lvAbout);

        Intent intent = getIntent();
        extra = intent.getStringExtra("transmission");
        tvTransmission.setText(extra);
        res_transmission = "transmission_" + extra.toLowerCase().split(" ")[0].replaceAll("[.-]", "_");
        connector = getResources().getIdentifier(res_transmission, "array", getPackageName());
        components = getResources().getStringArray(connector);
        title = getResources().getStringArray(R.array.title_act_transm);
        if (components.length<4){
            title = Arrays.copyOfRange(title, 0, 3);
        }

        ArrayAdapterInfo adapter = new ArrayAdapterInfo(this, R.layout.item_activity_transmission, title, components);
        lvAbout.setAdapter(adapter);

        logMemory();
    }

    private void logMemory() {
        Log.d("myLogs", String.format("Total memory = %s",
                (int) (Runtime.getRuntime().totalMemory() / 1024)));
    }
}
