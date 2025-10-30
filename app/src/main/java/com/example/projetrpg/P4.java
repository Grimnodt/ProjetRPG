package com.example.projetrpg;

import android.content.Intent;
import android.os.Build;
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

    private ReponsesQuiz reponses;
    private TextView tvInvalid;
    private RadioGroup rgQuestion8;
    private Switch swConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_p4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_page4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            reponses = intent.getParcelableExtra(ReponsesQuiz.KEY, ReponsesQuiz.class);
        } else {
            reponses = intent.getParcelableExtra(ReponsesQuiz.KEY); // LE ELSE EST DEPRECATED, ON EST SUR API 36 DONC C'EST NORMAL, ON RENTRE PAS DANS LE ELSE QUAND CA MARCHE.
        }

        if (reponses == null) {
            reponses = new ReponsesQuiz();
        }

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
        tvInvalid.setText("");

        reponses.setReponseQ8(resultQ8);
        reponses.setReponseQ9(resultQ9);

        Intent intent = new Intent(this, Activity_page5.class);
        intent.putExtra(ReponsesQuiz.KEY, reponses);
        startActivity(intent);
    }
}