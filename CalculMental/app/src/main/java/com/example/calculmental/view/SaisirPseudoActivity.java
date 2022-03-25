package com.example.calculmental.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import com.example.calculmental.R;

public class SaisirPseudoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisir_pseudo);

        EditText editTextPseudo = findViewById(R.id.editText_nom);

        Button boutonInformation = findViewById(R.id.bouton_voir_score);
        boutonInformation.setOnClickListener(view -> ouvreActiviteScore());

    }

    private void ouvreActiviteScore() {

        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }


}