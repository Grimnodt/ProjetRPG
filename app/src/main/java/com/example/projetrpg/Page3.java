package com.example.projetrpg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView; // Pour l'exemple

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Lie le fichier Java à son layout XML
        setContentView(R.layout.activity_page3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Q5), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- Étape cruciale : Récupérer les données envoyées par Page2 ---
        Intent intent = getIntent();
        resultQ1 = intent.getIntExtra("question 1", -1);
        resultQ2 = intent.getIntExtra("question 2", -1);
        resultQ3 = intent.getIntExtra("question 3", -1);
        resultQ4 = intent.getIntegerArrayListExtra("question 4");

        // Exemple : Afficher une des réponses pour vérifier que ça marche
        // Assurez-vous d'avoir un TextView avec l'id "test_affichage" dans activity_page3.xml
        // TextView testTextView = findViewById(R.id.test_affichage);
        // testTextView.setText("ID de la réponse Q1 : " + resultQ1);
    }
}