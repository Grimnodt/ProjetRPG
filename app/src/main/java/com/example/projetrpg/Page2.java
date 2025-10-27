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

    private RadioGroup radioGroupQ3;
    private CheckBox cb1Q4, cb2Q4, cb3Q4, cb4Q4;
    private TextView invalid;

    private int resultQ1, resultQ2;

    // Constante pour limiter le choix de la Q4
    private static final int MAX_CHOICES_Q4 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        resultQ1 = intent.getIntExtra("question 1", -1);
        resultQ2 = intent.getIntExtra("question 2", -1);

        invalid = findViewById(R.id.tx_Invalid2);

        radioGroupQ3 = findViewById(R.id.radioGroup3);

        // Initialisation des CheckBox pour la Q4
        cb1Q4 = findViewById(R.id.cb1Q4);
        cb2Q4 = findViewById(R.id.cb2Q4);
        cb3Q4 = findViewById(R.id.cb3Q4);
        cb4Q4 = findViewById(R.id.cb4Q4);

        View.OnClickListener limitListenerQ4 = v -> {
            if (getCheckedCountQ4() > MAX_CHOICES_Q4) {
                ((CheckBox) v).setChecked(false);
                Toast.makeText(this, "Vous ne pouvez choisir que " + MAX_CHOICES_Q4 + " options.", Toast.LENGTH_SHORT).show();
            }
        };
        cb1Q4.setOnClickListener(limitListenerQ4);
        cb2Q4.setOnClickListener(limitListenerQ4);
        cb3Q4.setOnClickListener(limitListenerQ4);
        cb4Q4.setOnClickListener(limitListenerQ4);
    }
    public void onValiderClick(View view) {
        boolean isQ3Answered = radioGroupQ3.getCheckedRadioButtonId() != -1;
        boolean isQ4Answered = isAnyCheckedQ(cb1Q4, cb2Q4, cb3Q4, cb4Q4);

        if (!isQ3Answered || !isQ4Answered) {
            invalid.setText("Veuillez répondre à toutes les questions.");
            return;
        }

        int resultQ3 = radioGroupQ3.getCheckedRadioButtonId();

        ArrayList<Integer> resultQ4 = new ArrayList<>();
        if (cb1Q4.isChecked()) resultQ4.add(R.id.cb1Q4);
        if (cb2Q4.isChecked()) resultQ4.add(R.id.cb2Q4);
        if (cb3Q4.isChecked()) resultQ4.add(R.id.cb3Q4);
        if (cb4Q4.isChecked()) resultQ4.add(R.id.cb4Q4);


        Intent intent = new Intent(this, Page3.class);

        intent.putExtra("question 1", resultQ1);
        intent.putExtra("question 2", resultQ2);
        intent.putExtra("question 3", resultQ3); // CORRIGÉ : On passe un simple 'int'
        intent.putIntegerArrayListExtra("question 4", resultQ4);

        startActivity(intent);
    }

    private int getCheckedCountQ4() {
        int count = 0;
        if (cb1Q4.isChecked()) count++;
        if (cb2Q4.isChecked()) count++;
        if (cb3Q4.isChecked()) count++;
        if (cb4Q4.isChecked()) count++;
        return count;
    }

    private boolean isAnyCheckedQ(CheckBox... checkBoxes) {
        for (CheckBox cb : checkBoxes) {
            if (cb.isChecked()) return true;
        }
        return false;
    }
}