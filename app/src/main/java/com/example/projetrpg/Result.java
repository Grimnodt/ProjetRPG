package com.example.projetrpg;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {

    private ReponsesQuiz reponses;
    private TextView tvResultatFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.result); // Charge result.xml

        // Utiliser le bon ID du layout principal (celui du XML)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Trouver le TextView
        tvResultatFinal = findViewById(R.id.tv_resultat_final);

        // 1. RECEVOIR le sac-à-dos FINAL
        reponses = getIntent().getParcelableExtra(ReponsesQuiz.KEY);

        if (reponses != null) {
            // 2. Analyser les réponses
            String personnage = analyserReponses(reponses);

            // 3. Afficher le résultat
            tvResultatFinal.setText("Vous êtes...\n" + personnage + " !");
        } else {
            // Gérer l'erreur si les réponses n'ont pas été passées
            tvResultatFinal.setText("Erreur: Impossible de charger les résultats.");
        }
    }

    /**
     * C'est ici que vous mettez votre logique pour
     * déterminer le personnage en fonction des réponses.
     */
    private String analyserReponses(ReponsesQuiz r) {
        // Mettez votre logique d'analyse ici
        // (Ceci est un exemple, R.id... doit correspondre à vos fichiers)

        // Exemple 1
        if (r.getReponseQ8() == R.id.rb_q8_rep5 && r.getReponseQ10() == R.id.rb_q10_rep4) {
            return "Muzan Kibutsuji (Le mal à l'état pur)";
        }

        // Exemple 2
        if (r.getReponseQ2() == R.id.rb_q2_rep1 && r.getReponseQ10() == R.id.rb_q10_rep1) {
            return "Batman (Intelligent et Incorruptible)";
        }

        // Exemple 3
        if (r.getReponseQ1() == R.id.rb_q1_rep2 && r.getReponseQ3() == R.id.rb_q3_rep4) {
            return "All Might (Fonceur et Super Puissant)";
        }

        return "Un aventurier"; // Personnage par défaut
    }
}