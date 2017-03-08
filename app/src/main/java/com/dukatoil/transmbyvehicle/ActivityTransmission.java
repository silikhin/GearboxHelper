package com.dukatoil.transmbyvehicle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTransmission extends AppCompatActivity {

    TextView tvTransmission, tvOil, tvCapacity, tvFilterGasket, tvNotes;
    String[] components;
    String extra; // extra from ActivityVariants (like "5hp19")
    String res_transmission; //resource array name
    int connector; // resource Id for String array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmission);

        tvTransmission = (TextView) findViewById(R.id.tvTransmission);
        tvOil = (TextView)findViewById(R.id.tvOil);
        tvCapacity = (TextView)findViewById(R.id.tvCapacity);
        tvFilterGasket = (TextView)findViewById(R.id.tvFilterGasket);
        tvNotes = (TextView)findViewById(R.id.tvNotes);

        Intent intent = getIntent();
        extra = intent.getStringExtra("transmission");
        tvTransmission.setText(extra);
        res_transmission = "transmission_" + extra.toLowerCase().split(" ")[0].replaceAll("[.-]", "_");
        connector = getResources().getIdentifier(res_transmission, "array", getPackageName());
        components = getResources().getStringArray(connector);

        tvOil.setText(components[0]);
        tvCapacity.setText(components[1]);
        tvFilterGasket.setText(components[2]);
        tvNotes.setText(components[3]);
    }
}
