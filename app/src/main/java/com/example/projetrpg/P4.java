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

    private TextView invalid;
    private RadioGroup radioGroup;
    private Switch _switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_p4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        invalid = findViewById(R.id.tx_Invalid4);
        radioGroup = findViewById(R.id.radioGroup);
        _switch = findViewById(R.id.switch3);
    }

    public void onValiderClick(View view) {

        if ((radioGroup.getCheckedRadioButtonId() != -1) || (!_switch.isChecked())) {
            invalid.setText("Veuillez sélectionner une réponse. ");
            return;
        }

        invalid.setText("Félicitations, vous avez fini le questionnaire, " +
                "nous allons maintenant déterminer votre personnage.");

     //   Intent intent = new Intent(this, activity_page5.class);
     //   startActivity(intent);
    }
}