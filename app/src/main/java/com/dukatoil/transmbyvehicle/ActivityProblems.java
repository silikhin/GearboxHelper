package com.dukatoil.transmbyvehicle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ActivityProblems extends AppCompatActivity {

    String[] problems, descriptions;
    ListView lvProblems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);

        problems = getResources().getStringArray(R.array.problems);
        descriptions = getResources().getStringArray(R.array.descriptions);

        lvProblems = (ListView) findViewById(R.id.lvProblems);

        ArrayAdapterInfo adapterInfo = new ArrayAdapterInfo(this, R.layout.item_activity_transmission,
                problems, descriptions);
        lvProblems.setAdapter(adapterInfo);
    }
}
