package com.example.projetrpg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
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

    private Switch above18;

    private TextView invalid;
    private Spinner monSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        invalid = findViewById(R.id.tx_Invalid);
        above18 = findViewById(R.id.switch1);

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

        monSpinner = findViewById(R.id.spQ5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.liste_de_mots, // Votre liste de mots dans strings.xml
                android.R.layout.simple_spinner_item // Layout standard pour l'élément affiché
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(adapter);
    }

    public void onValiderClick(View view) {
        //boolean isQ5Answered = radioGroupQ3.getCheckedRadioButtonId() != -1;

        if (!above18.isChecked()) {
            invalid.setText("Veuillez cocher que le formulaire vous plaît. Merci. ");
            return;
        }

        invalid.setText("Félicitations, vous avez fini le questionnaire, " +
                "nous allons maintenant déterminer votre personnage.");

        /*ArrayList<Integer> resultQ4 = new ArrayList<>();
        if (cb1Q4.isChecked()) resultQ4.add(R.id.cb1Q4);
        if (cb2Q4.isChecked()) resultQ4.add(R.id.cb2Q4);
        if (cb3Q4.isChecked()) resultQ4.add(R.id.cb3Q4);
        if (cb4Q4.isChecked()) resultQ4.add(R.id.cb4Q4);*/

        String motChoisi = monSpinner.getSelectedItem().toString();

        Intent intent = new Intent(this, Page4.class);

        intent.putExtra("question 1", resultQ1);
        intent.putExtra("question 2", resultQ2);
        intent.putExtra("question 3", resultQ3);
        intent.putIntegerArrayListExtra("question 4", resultQ4);
        intent.putExtra("question_mot_choisi", motChoisi);

        startActivity(intent);
    }
}