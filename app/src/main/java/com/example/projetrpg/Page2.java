package com.example.projetrpg;

import android.content.Intent;
import android.os.Build;
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

    private RadioGroup rgQuestion3;
    private CheckBox cbQ4Rep1, cbQ4Rep2, cbQ4Rep3, cbQ4Rep4;
    private TextView tvInvalid;
    private ReponsesQuiz reponses;
    private static final int MAX_CHOICES_Q4 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_page2), (v, insets) -> {
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

        tvInvalid = findViewById(R.id.tv_invalid2);
        rgQuestion3 = findViewById(R.id.rg_question3);

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
        boolean isQ3Answered = rgQuestion3.getCheckedRadioButtonId() != -1;
        boolean isQ4Answered = isAnyCheckedQ(cbQ4Rep1, cbQ4Rep2, cbQ4Rep3, cbQ4Rep4);

        if (!isQ3Answered || !isQ4Answered) {
            tvInvalid.setText("Veuillez répondre à toutes les questions.");
            return;
        }

        int resultQ3 = rgQuestion3.getCheckedRadioButtonId();
        ArrayList<Integer> resultQ4 = new ArrayList<>();
        if (cbQ4Rep1.isChecked()) resultQ4.add(R.id.cb_q4_rep1);
        if (cbQ4Rep2.isChecked()) resultQ4.add(R.id.cb_q4_rep2);
        if (cbQ4Rep3.isChecked()) resultQ4.add(R.id.cb_q4_rep3);
        if (cbQ4Rep4.isChecked()) resultQ4.add(R.id.cb_q4_rep4);

        reponses.setReponseQ3(resultQ3);
        reponses.setReponseQ4(resultQ4);

        Intent intent = new Intent(this, Page3.class);
        intent.putExtra(ReponsesQuiz.KEY, reponses);
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