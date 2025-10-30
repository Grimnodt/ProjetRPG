package com.example.projetrpg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch; // Reste Switch, mais la variable change
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Page3 extends AppCompatActivity {

    private int resultQ1, resultQ2, resultQ3;
    private ArrayList<Integer> resultQ4;

    private Switch swMajeur;
    private TextView tvInvalid;
    private Spinner spQuestion5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_page3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvInvalid = findViewById(R.id.tv_invalid3);
        swMajeur = findViewById(R.id.sw_majeur);

        Intent intent = getIntent();
        resultQ1 = intent.getIntExtra("question 1", -1);
        resultQ2 = intent.getIntExtra("question 2", -1);
        resultQ3 = intent.getIntExtra("question 3", -1);
        resultQ4 = intent.getIntegerArrayListExtra("question 4");

        spQuestion5 = findViewById(R.id.sp_question5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.liste_de_mots,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuestion5.setAdapter(adapter);
    }

    public void onValiderClick(View view) {
        if (!swMajeur.isChecked()) {
            tvInvalid.setText("Veuillez cocher que le formulaire vous plaît. Merci. ");
            return;
        }

        String motChoisi = spQuestion5.getSelectedItem().toString();

        Intent intent = new Intent(this, P4.class);

        intent.putExtra("question 1", resultQ1);
        intent.putExtra("question 2", resultQ2);
        intent.putExtra("question 3", resultQ3);
        intent.putIntegerArrayListExtra("question 4", resultQ4);
        intent.putExtra("question_mot_choisi", motChoisi);

        startActivity(intent);
    }
}