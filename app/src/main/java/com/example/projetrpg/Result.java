package com.example.projetrpg;

import android.os.Build;
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
        setContentView(R.layout.result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_result), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvResultatFinal = findViewById(R.id.tv_resultat_final);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            reponses = getIntent().getParcelableExtra(ReponsesQuiz.KEY, ReponsesQuiz.class);
        } else {
            reponses = getIntent().getParcelableExtra(ReponsesQuiz.KEY);
        }

        if (reponses != null) {
            String personnage = analyserReponses(reponses);
            tvResultatFinal.setText("Vous êtes...\n" + personnage + " !");
        } else {
            tvResultatFinal.setText("Erreur: Impossible de charger les résultats.");
        }
    }

    private String analyserReponses(ReponsesQuiz r) {

        if (r.getReponseQ8() == R.id.rb_q8_rep5) {
            return "Muzan Kibutsuji";
        }

        if (r.getReponseQ2() == R.id.rb_q2_rep1 && r.getReponseQ10() == R.id.rb_q10_rep1) {
            return "Batman";
        }

        if (r.getReponseQ1() == R.id.rb_q1_rep2) {
            return "Un Fonceur !";
        }

        return "Un aventurier";
    }
}