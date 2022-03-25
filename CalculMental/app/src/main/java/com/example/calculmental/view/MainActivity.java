package com.example.calculmental.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.calculmental.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutonNouvellePartie = findViewById(R.id.bouton_nouvelle_partie);
        boutonNouvellePartie.setOnClickListener(view-> ouvreActiviteCalculMental());

        Button boutonInformation = findViewById(R.id.bouton_propos);
        boutonInformation.setOnClickListener(view -> ouvreActivitePropos());

        Button boutonScore = findViewById(R.id.bouton_highscore);
        boutonScore.setOnClickListener(view -> ouvreActiviteScore());
    }

    private void ouvreActiviteScore() {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    private void ouvreActivitePropos() {
        Intent intent = new Intent(this, ProposActivity.class);
        startActivity(intent);
    }

    private void ouvreActiviteCalculMental(){
        Intent intent = new Intent(this, CalculMentalActivity.class);
        startActivity(intent);
    }
}