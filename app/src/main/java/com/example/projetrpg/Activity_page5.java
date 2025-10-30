package com.example.projetrpg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_page5 extends AppCompatActivity {

    private ReponsesQuiz reponses;
    private TextView tvInvalid;
    private RadioGroup rgQuestion10;
    private RadioGroup rgQuestion11;

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

        Intent intent = getIntent();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            reponses = intent.getParcelableExtra(ReponsesQuiz.KEY, ReponsesQuiz.class);
        } else {
            reponses = intent.getParcelableExtra(ReponsesQuiz.KEY); // LE ELSE EST DEPRECATED, ON EST SUR API 36 DONC C'EST NORMAL, ON RENTRE PAS DANS LE ELSE QUAND CA MARCHE.
        }

        if (reponses == null) {
            reponses = new ReponsesQuiz();
        }

        tvInvalid = findViewById(R.id.tv_invalid5);
        rgQuestion10 = findViewById(R.id.rg_question10);
        rgQuestion11 = findViewById(R.id.rg_question11);
    }

    public void onValiderClick(View view) {

        int resultQ10 = rgQuestion10.getCheckedRadioButtonId();
        int resultQ11 = rgQuestion11.getCheckedRadioButtonId();

        if (resultQ10 == -1 || resultQ11 == -1) {
            tvInvalid.setText("Veuillez répondre à toutes les questions.");
            return;
        }
        tvInvalid.setText("");

        reponses.setReponseQ10(resultQ10);
        reponses.setReponseQ11(resultQ11);

        Intent intent = new Intent(this, Result.class);
        intent.putExtra(ReponsesQuiz.KEY, reponses);
        startActivity(intent);
    }
}