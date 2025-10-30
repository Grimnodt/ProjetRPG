package com.example.projetrpg;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Correction de l'ID du layout principal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onValiderClick(View view) {
        RadioGroup rgQuestion1 = findViewById(R.id.rg_question1);
        RadioGroup rgQuestion2 = findViewById(R.id.rg_question2);
        TextView tvInvalid = findViewById(R.id.tv_invalid);

        int resultQ1 = rgQuestion1.getCheckedRadioButtonId();
        int resultQ2 = rgQuestion2.getCheckedRadioButtonId();

        if (resultQ1 == -1 || resultQ2 == -1){
            tvInvalid.setText("Une question n'a pas été répondu");
            return;
        }

        // 1. CRÉER le sac-à-dos
        ReponsesQuiz reponses = new ReponsesQuiz();

        // 2. AJOUTER les réponses de la page 1
        reponses.setReponseQ1(resultQ1);
        reponses.setReponseQ2(resultQ2);

        // 3. PASSER le sac à la page 2
        Intent intent = new Intent(this, Page2.class);
        intent.putExtra(ReponsesQuiz.KEY, reponses);
        startActivity(intent);
    }

    public void onExitClick(View view) {
        this.finish();
    }
}