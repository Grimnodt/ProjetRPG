package com.example.projetrpg;

import android.content.Intent;
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

    // Variables de vue renommées
    private TextView tvInvalid;
    private RadioGroup rgQuestion8;
    private Switch swConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_p4);

        // ID du layout principal mis à jour
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main_page4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Références aux vues mises à jour
        tvInvalid = findViewById(R.id.tv_invalid4);
        rgQuestion8 = findViewById(R.id.rg_question8);
        swConfirmation = findViewById(R.id.sw_confirmation);
    }

    public void onValiderClick(View view) {

        // Logique utilisant les variables renommées
        if ((rgQuestion8.getCheckedRadioButtonId() == -1) || (!swConfirmation.isChecked())) {
            tvInvalid.setText("Veuillez sélectionner une réponse.");
            return;
        }

        // Correction de la classe cible (supposant que la prochaine classe est Page5.class)
        Intent intent = new Intent(this, activity_page5.class);
        startActivity(intent);
    }
}