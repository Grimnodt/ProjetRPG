package com.example.projetrpg;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Page3 extends AppCompatActivity {

    private ReponsesQuiz reponses;
    private Switch swMajeur;
    private TextView tvInvalid;
    private Spinner spQuestion5;
    private RadioGroup rgQuestion7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_page3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            reponses = intent.getParcelableExtra(ReponsesQuiz.KEY, ReponsesQuiz.class);
        } else {
            reponses = intent.getParcelableExtra(ReponsesQuiz.KEY);
        }

        if (reponses == null) {
            reponses = new ReponsesQuiz();
        }

        tvInvalid = findViewById(R.id.tv_invalid3);
        swMajeur = findViewById(R.id.sw_majeur);
        spQuestion5 = findViewById(R.id.sp_question5);
        rgQuestion7 = findViewById(R.id.rg_question7);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.liste_de_mots,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuestion5.setAdapter(adapter);
    }

    public void onValiderClick(View view) {
        int resultQ7 = rgQuestion7.getCheckedRadioButtonId();

        if (!swMajeur.isChecked()) {
            tvInvalid.setText("Veuillez cocher que le formulaire vous plaît. Merci. ");
            return;
        }
        if (resultQ7 == -1) {
            tvInvalid.setText("Veuillez répondre à la question sur la prophétie.");
            return;
        }
        tvInvalid.setText("");

        String motChoisi = spQuestion5.getSelectedItem().toString();
        boolean estMajeur = swMajeur.isChecked();

        reponses.setReponseMajeur(estMajeur);
        reponses.setReponseQ5(motChoisi);
        reponses.setReponseQ7(resultQ7);

        Intent intent = new Intent(this, P4.class);
        intent.putExtra(ReponsesQuiz.KEY, reponses);
        startActivity(intent);
    }
}