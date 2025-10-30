package com.example.projetrpg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class P4 extends AppCompatActivity {

    // 1. Variable pour le sac-à-dos
    private ReponsesQuiz reponses;

    // Vues de la page
    private TextView tvInvalid;
    private RadioGroup rgQuestion8;
    private Switch swConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_p4);

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
        tvInvalid = findViewById(R.id.tv_invalid4);
        rgQuestion8 = findViewById(R.id.rg_question8);
        swConfirmation = findViewById(R.id.sw_confirmation);
    }

    public void onValiderClick(View view) {

        int resultQ8 = rgQuestion8.getCheckedRadioButtonId();
        boolean resultQ9 = swConfirmation.isChecked();

        if (resultQ8 == -1 || !resultQ9) {
            tvInvalid.setText("Veuillez sélectionner une réponse et confirmer.");
            return;
        }
        tvInvalid.setText(""); // Effacer l'erreur

        // 3. AJOUTER les réponses au sac
        reponses.setReponseQ8(resultQ8);
        reponses.setReponseQ9(resultQ9);

        // 4. REPASSER le sac MIS À JOUR
        Intent intent = new Intent(this, Activity_page5.class);
        intent.putExtra(ReponsesQuiz.KEY, reponses);
        startActivity(intent);
    }
}