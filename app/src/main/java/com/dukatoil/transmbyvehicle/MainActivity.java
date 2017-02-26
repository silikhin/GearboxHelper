package com.dukatoil.transmbyvehicle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    LinearLayout llPrimary1, llPrimary2, llPrimary3, llPrimary4, llCars, llModels, llVersions;
    ListView lvCars, lvModels, lvVersions;
    String[] cars_list, models_list, versions_list;
    final Context firstActivity = this;
    LinearLayout.LayoutParams params_show = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    LinearLayout.LayoutParams params_hide = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        llPrimary1 = (LinearLayout) findViewById(R.id.llPrimary1);
        llPrimary2 = (LinearLayout) findViewById(R.id.llPrimary2);
        llPrimary3 = (LinearLayout) findViewById(R.id.llPrimary3);
        llPrimary4 = (LinearLayout) findViewById(R.id.llPrimary4);
        llCars = (LinearLayout) findViewById(R.id.llCars);
        llModels = (LinearLayout) findViewById(R.id.llModels);
        llVersions = (LinearLayout) findViewById(R.id.llVersions);

        lvCars = (ListView) findViewById(R.id.lvCars);
        lvModels = (ListView) findViewById(R.id.lvModels);
        lvVersions = (ListView) findViewById(R.id.lvVersions);

        cars_list = getResources().getStringArray(R.array.cars);


        final FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_arrow_forward_white_24px))
                .withButtonColor(Color.RED)
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();
        fabButton.hideFloatingActionButton();
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(firstActivity, ActivityVariants.class);
                intent.putExtra("version", tv3.getText().toString().split(" ")[0]);
                startActivity(intent);
            }
        });

        // создаем адаптер для списку марок авто
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.item_child, cars_list);

        // присваиваем адаптер списку
        lvCars.setAdapter(adapter1);

        // які дії виконуються після вибору марки автомобіля
        lvCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String car_selected = lvCars.getItemAtPosition(position).toString();
                // зміна тексту "виберіть марку авто" на "марка авто"
                tv1.setText(car_selected);
                // ховаю ListView з марками авто
                llCars.setLayoutParams(params_hide);
                llPrimary1.setBackgroundColor(getResources().getColor(R.color.colorLayout300));
                llPrimary2.setBackgroundColor(getResources().getColor(R.color.colorLayout100));
                tv2.setTextColor(getResources().getColor(R.color.textColor90a));

                // наповнення ListView з моделями по вибору марки
                String array_name_for_lv_models = car_selected.toLowerCase().replaceAll("[ -]", "_") + "_models";
                int intForLVModels = getResources().getIdentifier(array_name_for_lv_models, "array", getPackageName());
                models_list = getResources().getStringArray(intForLVModels);
                // адаптер для ListView з моделями
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(firstActivity,
                        R.layout.item_child, models_list);
                // присвоєння адаптера
                lvModels.setAdapter(adapter2);
                // показую ListView з вибором моделі авто
                llModels.setLayoutParams(params_show);

            }
        });

        lvModels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String model_selected = lvModels.getItemAtPosition(i).toString();
                // зміна тексту "виберіть марку" на "назва марки"
                tv2.setText(model_selected);
                // ховаю ListView з моделями
                llModels.setLayoutParams(params_hide);
                llPrimary2.setBackgroundColor(getResources().getColor(R.color.colorLayout300));
                llPrimary3.setBackgroundColor(getResources().getColor(R.color.colorLayout100));
                tv3.setTextColor(getResources().getColor(R.color.textColor90a));

                // наповнення ListView з варіантами (кузовами)
                String array_name_for_lv_versions = model_selected.toLowerCase().replaceAll("[ -]", "_") + "_versions";
                int intForLVVersions = getResources().getIdentifier(array_name_for_lv_versions, "array", getPackageName());
                versions_list = getResources().getStringArray(intForLVVersions);

                // адаптер для ListView з варіантами
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(firstActivity,
                        R.layout.item_child, versions_list);
                // присвоєння адаптера
                lvVersions.setAdapter(adapter3);
                // показую ListView з вибором варіанта
                llVersions.setLayoutParams(params_show);
            }
        });

        lvVersions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String version_selected = lvVersions.getItemAtPosition(i).toString();
                // зміна тексту "виберіть версію" на "назва версії/кузова"
                tv3.setText(version_selected);
                // ховаю ListView з версіями
                llVersions.setLayoutParams(params_hide);
                llPrimary4.setLayoutParams(params_show);
                llPrimary3.setBackgroundColor(getResources().getColor(R.color.colorLayout300));
                // показую кнопку "Далі"
                fabButton.showFloatingActionButton();
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText(R.string.text_view_1);
                tv2.setText(R.string.text_view_2);
                tv3.setText(R.string.text_view_3);
                llModels.setLayoutParams(params_hide);
                llCars.setLayoutParams(params_show);
                llPrimary1.setBackgroundColor(getResources().getColor(R.color.colorLayout100));
                llPrimary2.setBackgroundColor(Color.WHITE);
                llPrimary3.setBackgroundColor(Color.WHITE);
                fabButton.hideFloatingActionButton();
                llPrimary4.setLayoutParams(params_hide);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv1.getText().toString().equals("Виберіть марку автомобіля")) {
                    Toast.makeText(firstActivity, "Спочатку виберіть марку автомобіля", Toast.LENGTH_SHORT).show();
                } else {
                    tv2.setText(R.string.text_view_2);
                    tv3.setText(R.string.text_view_3);
                    llCars.setLayoutParams(params_hide);
                    llVersions.setLayoutParams(params_hide);
                    llModels.setLayoutParams(params_show);
                    llPrimary2.setBackgroundColor(getResources().getColor(R.color.colorLayout100));
                    llPrimary3.setBackgroundColor(Color.WHITE);
                    fabButton.hideFloatingActionButton();
                }
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv1.getText().toString().equals("Виберіть марку автомобіля")) {
                    Toast.makeText(firstActivity, "Спочатку виберіть марку автомобіля", Toast.LENGTH_SHORT).show();
                } else if (tv2.getText().toString().equals("Виберіть модель автомобіля")) {
                    Toast.makeText(firstActivity, "Спочатку виберіть модель автомобіля", Toast.LENGTH_SHORT).show();
                } else {
                    tv3.setText(R.string.text_view_3);
                    llVersions.setLayoutParams(params_show);
                    llPrimary3.setBackgroundColor(getResources().getColor(R.color.colorLayout100));
                    fabButton.hideFloatingActionButton();
                }
            }
        });
    }
}