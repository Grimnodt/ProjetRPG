package com.example.projetrpg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Page2 extends AppCompatActivity {

    // Variables de classe mises à jour
    private RadioGroup rgQuestion3;
    private CheckBox cbQ4Rep1, cbQ4Rep2, cbQ4Rep3, cbQ4Rep4;
    private TextView tvInvalid;

    private int resultQ1, resultQ2;

    // Constante pour limiter le choix de la Q4
    private static final int MAX_CHOICES_Q4 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page2);

        // ID du layout principal mis à jour
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        resultQ1 = intent.getIntExtra("question 1", -1);
        resultQ2 = intent.getIntExtra("question 2", -1);

        // Références aux vues mises à jour
        tvInvalid = findViewById(R.id.tv_invalid2);
        rgQuestion3 = findViewById(R.id.rg_question3);

        // Initialisation des CheckBox pour la Q4
        cbQ4Rep1 = findViewById(R.id.cb_q4_rep1);
        cbQ4Rep2 = findViewById(R.id.cb_q4_rep2);
        cbQ4Rep3 = findViewById(R.id.cb_q4_rep3);
        cbQ4Rep4 = findViewById(R.id.cb_q4_rep4);

        View.OnClickListener limitListenerQ4 = v -> {
            if (getCheckedCountQ4() > MAX_CHOICES_Q4) {
                ((CheckBox) v).setChecked(false);
                Toast.makeText(this, "Vous ne pouvez choisir que " + MAX_CHOICES_Q4 + " options.", Toast.LENGTH_SHORT).show();
            }
        };
        cbQ4Rep1.setOnClickListener(limitListenerQ4);
        cbQ4Rep2.setOnClickListener(limitListenerQ4);
        cbQ4Rep3.setOnClickListener(limitListenerQ4);
        cbQ4Rep4.setOnClickListener(limitListenerQ4);
    }

    public void onValiderClick(View view) {
        // Logique de validation utilisant les variables de classe mises à jour
        boolean isQ3Answered = rgQuestion3.getCheckedRadioButtonId() != -1;
        boolean isQ4Answered = isAnyCheckedQ(cbQ4Rep1, cbQ4Rep2, cbQ4Rep3, cbQ4Rep4);

        if (!isQ3Answered || !isQ4Answered) {
            tvInvalid.setText("Veuillez répondre à toutes les questions.");
            return;
        }

        int resultQ3 = rgQuestion3.getCheckedRadioButtonId();

        // Ajout des ID mis à jour à l'ArrayList
        ArrayList<Integer> resultQ4 = new ArrayList<>();
        if (cbQ4Rep1.isChecked()) resultQ4.add(R.id.cb_q4_rep1);
        if (cbQ4Rep2.isChecked()) resultQ4.add(R.id.cb_q4_rep2);
        if (cbQ4Rep3.isChecked()) resultQ4.add(R.id.cb_q4_rep3);
        if (cbQ4Rep4.isChecked()) resultQ4.add(R.id.cb_q4_rep4);


        Intent intent = new Intent(this, Page3.class);

        intent.putExtra("question 1", resultQ1);
        intent.putExtra("question 2", resultQ2);
        intent.putExtra("question 3", resultQ3);
        intent.putIntegerArrayListExtra("question 4", resultQ4);

        startActivity(intent);
    }

    private int getCheckedCountQ4() {
        int count = 0;
        if (cbQ4Rep1.isChecked()) count++;
        if (cbQ4Rep2.isChecked()) count++;
        if (cbQ4Rep3.isChecked()) count++;
        if (cbQ4Rep4.isChecked()) count++;
        return count;
    }

    private boolean isAnyCheckedQ(CheckBox... checkBoxes) {
        for (CheckBox cb : checkBoxes) {
            if (cb.isChecked()) return true;
        }
        return false;
    }
}