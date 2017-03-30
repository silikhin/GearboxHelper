package com.dukatoil.transmbyvehicle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ActivityFaq extends AppCompatActivity {

    String[] questions, answers;
    ListView lvFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);
        lvFAQ = (ListView) findViewById(R.id.lvFAQ);

        ArrayAdapterInfo adapterInfo = new ArrayAdapterInfo(this, R.layout.item_activity_transmission,
                questions, answers);
        lvFAQ.setAdapter(adapterInfo);
    }

}
