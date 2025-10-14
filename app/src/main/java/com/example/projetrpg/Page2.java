package com.example.projetrpg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Page2 extends AppCompatActivity {
    CheckBox cb1, cb2, cb3, cb4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int resultQ3 = intent.getIntExtra("question 3", -1);
        int resultQ4 = intent.getIntExtra("question 4", -1);

        CheckBox cb1 = findViewById(R.id.cb1Q3);
        CheckBox cb2 = findViewById(R.id.cb2Q3);
        CheckBox cb3 = findViewById(R.id.cb3Q3);
        CheckBox cb4 = findViewById(R.id.cb4Q3);

        View.OnClickListener listener = v -> {
            cb1.setChecked(v == cb1);
            cb2.setChecked(v == cb2);
            cb3.setChecked(v == cb3);
            cb4.setChecked(v == cb4);
        };

        cb1.setOnClickListener(listener);
        cb2.setOnClickListener(listener);
        cb3.setOnClickListener(listener);
        cb4.setOnClickListener(listener);
    }

    public void onValiderClick(View view) {
        RadioGroup group3 = findViewById(R.id.radioGroup3);
        RadioGroup group4 = findViewById(R.id.radioGroup4);
        CheckBox check1Q3 = findViewById(R.id.cb1Q3);
        CheckBox check2Q3 = findViewById(R.id.cb2Q3);
        CheckBox check3Q3 = findViewById(R.id.cb3Q3);
        CheckBox check4Q3 = findViewById(R.id.cb4Q3);
        CheckBox check1Q4 = findViewById(R.id.cb1Q4);
        CheckBox check2Q4 = findViewById(R.id.cb2Q4);
        CheckBox check3Q4 = findViewById(R.id.cb3Q4);
        CheckBox check4Q4 = findViewById(R.id.cb4Q4);

        TextView invalid = findViewById(R.id.tx_Invalid1);

        if ((!check1Q3.isChecked() || !check2Q3.isChecked() || !check3Q3.isChecked() || !check4Q3.isChecked()) ||
                (!check1Q4.isChecked() || !check2Q4.isChecked() || !check3Q4.isChecked() || !check4Q4.isChecked())){
            invalid.setText("Une question n'a pas été répondu");
            return;
        }

        int resultQ3 = group3.getCheckedRadioButtonId();
        int resultQ4 = group4.getCheckedRadioButtonId();

        if (group3.getCheckedRadioButtonId() == -1 || group4.getCheckedRadioButtonId() == -1){
            invalid.setText("Une question n'a pas été répondu");
            return;
        }

        ArrayList<Integer> resultQ03 = new ArrayList<>();
        if (cb1.isChecked()) resultQ3.add(1);
        if (cb2.isChecked()) resultQ3.add(2);
        if (cb3.isChecked()) resultQ3.add(3);
        if (cb4.isChecked()) resultQ3.add(4);

        ArrayList<Integer> resultQ04 = new ArrayList<>();
        if (check1Q4.isChecked()) resultQ3.add(1);
        if (check2Q4.isChecked()) resultQ3.add(2);
        if (check3Q4.isChecked()) resultQ3.add(3);
        if (check4Q4.isChecked()) resultQ3.add(4);

        Intent intent = new Intent(this, Page2.class);
        intent.putExtra("question 1", resultQ3);
        intent.putExtra("question 2", resultQ4);
        startActivity(intent);
    }
}