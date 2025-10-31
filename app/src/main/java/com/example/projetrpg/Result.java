package com.example.projetrpg;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView; // Import nécessaire
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {

    private ReponsesQuiz reponses;
    private TextView tvResultatFinal;
    private TextView tvPersonnageNom;
    private ImageView ivResultatImage;

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
        tvPersonnageNom = findViewById(R.id.tv_personnage_nom);
        ivResultatImage = findViewById(R.id.imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            reponses = getIntent().getParcelableExtra(ReponsesQuiz.KEY, ReponsesQuiz.class);
        } else {
            reponses = getIntent().getParcelableExtra(ReponsesQuiz.KEY);
        }

        if (reponses != null) {
            String personnage = analyserReponses(reponses);

            tvResultatFinal.setText("Vous êtes...");
            tvPersonnageNom.setText(personnage + " !");

            afficherImagePersonnage(personnage);

        } else {
            tvResultatFinal.setText("Erreur: Impossible de charger les résultats.");
            tvPersonnageNom.setText("");
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
            return "Kratos";
        }
        if (r.getReponseQ11() == R.id.rb_q11_rep2) {
            return "V (Cyberpunk)";
        }
        if (r.getReponseQ3() == R.id.rb_q3_rep2) {
            return "Flash";
        }
        if (r.getReponseQ10() == R.id.rb_q10_rep4 && r.getReponseQ2() == R.id.rb_q2_rep3) {
            return "Griffith";
        }
        if (r.getReponseQ10() == R.id.rb_q10_rep4 && r.getReponseQ2() == R.id.rb_q2_rep1) {
            return "Sauron";
        }
        if (r.getReponseQ10() == R.id.rb_q10_rep4) {
            return "Joker";
        }
        if (r.getReponseQ7() == R.id.rb_q7_rep3 && r.getReponseQ2() == R.id.rb_q2_rep1) {
            return "Paul Atreides";
        }
        if (r.getReponseQ1() == R.id.rb_q1_rep1 && r.getReponseQ2() == R.id.rb_q2_rep2) {
            return "Pippin";
        }
        return "Un aventurier";
    }

    private void afficherImagePersonnage(String personnage) {
        switch (personnage) {
            case "Batman":
                ivResultatImage.setImageResource(R.drawable.batman);
                break;
            case "Flash":
                ivResultatImage.setImageResource(R.drawable.flash);
                break;
            case "Griffith":
                ivResultatImage.setImageResource(R.drawable.griffith);
                break;
            case "Joker":
                ivResultatImage.setImageResource(R.drawable.joker);
                break;
            case "Kratos":
                ivResultatImage.setImageResource(R.drawable.kratos);
                break;
            case "Muzan Kibutsuji":
                ivResultatImage.setImageResource(R.drawable.muzan_jackson);
                break;
            case "Paul Atreides":
            case "Un aventurier":
                ivResultatImage.setImageResource(R.drawable.paul);
                break;
            case "Pippin":
                ivResultatImage.setImageResource(R.drawable.pinpin);
                break;
            case "Sauron":
                ivResultatImage.setImageResource(R.drawable.sauron);
                break;
            case "V (Cyberpunk)":
                ivResultatImage.setImageResource(R.drawable.cyberpunk);
                break;
            default:
                //       Cache l'image si aucun personnage est trouvé
                ivResultatImage.setVisibility(View.GONE);
                break;
        }
    }

    public void onQuitterClick(View view) {
        finishAffinity();
    }
}