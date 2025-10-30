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

public class activity_page5 extends AppCompatActivity {

    // --- 1. Déclaration des vues de CETTE page (Q10 & Q11) ---
    private TextView invalid;
    private RadioGroup radioGroupQ10; // Pour la question de l'artefact
    private RadioGroup radioGroupQ11; // Pour la question du paysage

    // --- 2. Variables pour stocker les réponses de TOUTES les pages précédentes ---
    private int resultQ1, resultQ2, resultQ3;
    private ArrayList<Integer> resultQ4;
    private String motChoisi;
    private int p4_radio_result; // Supposons que P4 avait une question radio
    private boolean p4_switch_result; // Supposons que P4 avait un switch

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page5); // Lien vers ton fichier XML
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- 3. Initialisation des vues de la Page 5 ---
        // Assure-toi que ces IDs correspondent à ton fichier activity_page5.xml
        invalid = findViewById(R.id.tx_Invalid5); // Tu dois ajouter ce TextView dans ton XML
        radioGroupQ10 = findViewById(R.id.radioGroup5); // L'ID de ta première question
        radioGroupQ11 = findViewById(R.id.radioGroup6); // L'ID pour la question des paysages

        // --- 4. Récupération de TOUTES les données envoyées par P4.java ---
        Intent intent = getIntent();
        resultQ1 = intent.getIntExtra("question 1", -1);
        resultQ2 = intent.getIntExtra("question 2", -1);
        resultQ3 = intent.getIntExtra("question 3", -1);
        resultQ4 = intent.getIntegerArrayListExtra("question 4");
        motChoisi = intent.getStringExtra("question_mot_choisi");
        // Récupère ici les réponses de la page 4
        p4_radio_result = intent.getIntExtra("p4_radio_question", -1);
        p4_switch_result = intent.getBooleanExtra("p4_switch_question", false);
    }

    public void onValiderClick(View view) {
        // --- 5. Validation : On vérifie si les deux questions ont une réponse ---
        boolean q10Answered = radioGroupQ10.getCheckedRadioButtonId() != -1;
        boolean q11Answered = radioGroupQ11.getCheckedRadioButtonId() != -1;

        if (!q10Answered || !q11Answered) {
            invalid.setText("Veuillez répondre à toutes les questions.");
            return;
        }

        // --- 6. On récupère les réponses des questions 10 et 11 ---
        int resultQ10 = radioGroupQ10.getCheckedRadioButtonId();
        int resultQ11 = radioGroupQ11.getCheckedRadioButtonId();

        // --- 7. On prépare l'envoi vers la page de résultats finale ---
        // Remplace ResultatActivity.class par le nom de ta page de résultats
        Intent intent = new Intent(this, result.class);

        // --- 8. On met TOUTES les réponses (anciennes et nouvelles) dans l'Intent ---
        intent.putExtra("question 1", resultQ1);
        intent.putExtra("question 2", resultQ2);
        intent.putExtra("question 3", resultQ3);
        intent.putIntegerArrayListExtra("question 4", resultQ4);
        intent.putExtra("question_mot_choisi", motChoisi);
        intent.putExtra("p4_radio_question", p4_radio_result);
        intent.putExtra("p4_switch_question", p4_switch_result);
        intent.putExtra("question 10", resultQ10); // On ajoute la réponse de la Q10
        intent.putExtra("question 11", resultQ11); // On ajoute la réponse de la Q11

        // --- 9. On lance la page de résultats ---
        startActivity(intent);
    }
}