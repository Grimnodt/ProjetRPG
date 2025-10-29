package com.example.projetrpg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Page4 extends AppCompatActivity {

    private TextView invalid = findViewById(R.id.tx_Invalid3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onValiderClick(View view) {

       /* if (!tanjiro_red.isPressed() || !tanjiro_blue.isPressed()) {
            invalid.setText("Veuillez cocher que le formulaire vous plaît. Merci. ");
            return;
        }*/

        invalid.setText("Félicitations, vous avez fini le questionnaire, " +
                "nous allons maintenant déterminer votre personnage.");

        Intent intent = new Intent(this, Page5.class);
    }
}