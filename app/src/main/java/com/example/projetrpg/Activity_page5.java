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

public class Activity_page5 extends AppCompatActivity {

    // 1. Variable pour le sac-à-dos
    private ReponsesQuiz reponses;

    // Vues de la page
    private TextView tvInvalid;
    private RadioGroup rgQuestion10;
    private RadioGroup rgQuestion11;

    // Plus besoin des variables pour les Q1, Q2, Q3...

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page5);

        // Correction de l'ID du layout principal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. RECEVOIR le sac
        Intent intent = getIntent();
        reponses = intent.getParcelableExtra(ReponsesQuiz.KEY);
        if (reponses == null) {
            reponses = new ReponsesQuiz(); // Sécurité
        }

        // Initialisation des vues
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
        tvInvalid.setText(""); // Effacer l'erreur

        // 3. AJOUTER les dernières réponses au sac
        reponses.setReponseQ10(resultQ10);
        reponses.setReponseQ11(resultQ11);

        // 4. ENVOYER le sac FINAL à la page de Résultat
        Intent intent = new Intent(this, Result.class);
        intent.putExtra(ReponsesQuiz.KEY, reponses);
        startActivity(intent);
    }
}