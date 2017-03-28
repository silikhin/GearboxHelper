package com.dukatoil.transmbyvehicle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class ActivityTransmission extends AppCompatActivity {

    TextView tvTransmission;
    ListView lvAbout;
    Toolbar toolbar;
    String[] title, components;
    String extra; // extra from ActivityVariants (like "5hp19")
    String res_transmission; //resource array name
    int connector; // resource Id for String array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmission);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.transm_menu, menu);
        return true;
    }
}
