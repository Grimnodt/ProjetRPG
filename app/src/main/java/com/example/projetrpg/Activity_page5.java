package com.example.projetrpg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Activity_page5 extends AppCompatActivity {

    private TextView tvInvalid;
    private RadioGroup rgQuestion10;
    private RadioGroup rgQuestion11;

    private int resultQ1, resultQ2, resultQ3;
    private ArrayList<Integer> resultQ4;
    private String motChoisi;
    private int p4_radio_result;
    private boolean p4_switch_result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_page5), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvInvalid = findViewById(R.id.tv_invalid5);
        rgQuestion10 = findViewById(R.id.rg_question10);
        rgQuestion11 = findViewById(R.id.rg_question11);

        Intent intent = getIntent();
        resultQ1 = intent.getIntExtra("question 1", -1);
        resultQ2 = intent.getIntExtra("question 2", -1);
        resultQ3 = intent.getIntExtra("question 3", -1);
        resultQ4 = intent.getIntegerArrayListExtra("question 4");
        motChoisi = intent.getStringExtra("question_mot_choisi");
        p4_radio_result = intent.getIntExtra("p4_radio_question", -1);
        p4_switch_result = intent.getBooleanExtra("p4_switch_question", false);
    }

    public void onValiderClick(View view) {
        boolean q10Answered = rgQuestion10.getCheckedRadioButtonId() != -1;
        boolean q11Answered = rgQuestion11.getCheckedRadioButtonId() != -1;

        if (!q10Answered || !q11Answered) {
            tvInvalid.setText("Veuillez répondre à toutes les questions.");
            return;
        }

        int resultQ10 = rgQuestion10.getCheckedRadioButtonId();
        int resultQ11 = rgQuestion11.getCheckedRadioButtonId();

        Intent intent = new Intent(this, Result.class);

        intent.putExtra("question 1", resultQ1);
        intent.putExtra("question 2", resultQ2);
        intent.putExtra("question 3", resultQ3);
        intent.putIntegerArrayListExtra("question 4", resultQ4);
        intent.putExtra("question_mot_choisi", motChoisi);
        intent.putExtra("p4_radio_question", p4_radio_result);
        intent.putExtra("p4_switch_question", p4_switch_result);
        intent.putExtra("question 10", resultQ10);
        intent.putExtra("question 11", resultQ11);

        startActivity(intent);
    }
}