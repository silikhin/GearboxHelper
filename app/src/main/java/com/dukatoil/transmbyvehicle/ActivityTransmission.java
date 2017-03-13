package com.dukatoil.transmbyvehicle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityTransmission extends AppCompatActivity {

    TextView tvTransmission;
    ListView lvAbout;
    String[] components;
    String extra; // extra from ActivityVariants (like "5hp19")
    String res_transmission; //resource array name
    int connector; // resource Id for String array

//    TODO: change to multiline list
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

//        TODO: change the resource to item_activity_transmission
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_transmission_variants, components);
        lvAbout.setAdapter(adapter);
    }
}
