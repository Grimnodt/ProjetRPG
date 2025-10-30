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

    // Variables pour stocker les réponses de toutes les pages précédentes
    private int resultQ1, resultQ2, resultQ3;
    private ArrayList<Integer> resultQ4;

    // Variables de vue renommées
    private Switch swMajeur;
    private TextView tvInvalid;
    private Spinner spQuestion5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page3);

        // ID du layout principal mis à jour
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_page3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Références aux vues mises à jour avec les nouveaux ID
        tvInvalid = findViewById(R.id.tv_invalid3);
        swMajeur = findViewById(R.id.sw_majeur);

        // --- Étape cruciale : Récupérer les données envoyées par Page2 ---
        Intent intent = getIntent();
        resultQ1 = intent.getIntExtra("question 1", -1);
        resultQ2 = intent.getIntExtra("question 2", -1);
        resultQ3 = intent.getIntExtra("question 3", -1);
        resultQ4 = intent.getIntegerArrayListExtra("question 4");

        // ... (votre code de test)

        // Référence au Spinner mise à jour
        spQuestion5 = findViewById(R.id.sp_question5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.liste_de_mots, // Votre liste de mots dans strings.xml
                android.R.layout.simple_spinner_item // Layout standard pour l'élément affiché
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuestion5.setAdapter(adapter);
    }

    public void onValiderClick(View view) {
        //boolean isQ5Answered = radioGroupQ3.getCheckedRadioButtonId() != -1; // Code original commenté

        // Utilisation des variables renommées
        if (!swMajeur.isChecked()) {
            tvInvalid.setText("Veuillez cocher que le formulaire vous plaît. Merci. ");
            return;
        }

        /*invalid.setText("Félicitations, vous avez fini le questionnaire, " +
                "nous allons maintenant déterminer votre personnage.");*/

        /* ... (votre code commenté pour Q4) ... */

        // Utilisation de la variable Spinner renommée
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