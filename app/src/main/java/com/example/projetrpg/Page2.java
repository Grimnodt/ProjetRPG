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
        int resultQ1 = intent.getIntExtra("question 1", -1);
        int resultQ2 = intent.getIntExtra("question 2", -1);
    }

    public void onValiderClick(View view) {
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

        ArrayList<Integer> resultQ3 = new ArrayList<>();
        if (check1Q3.isChecked()) resultQ3.add(1);
        if (check2Q3.isChecked()) resultQ3.add(2);
        if (check3Q3.isChecked()) resultQ3.add(3);
        if (check4Q3.isChecked()) resultQ3.add(4);

        ArrayList<Integer> resultQ4 = new ArrayList<>();
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