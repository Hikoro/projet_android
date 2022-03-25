package com.example.calculmental.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculmental.R;

public class ScoreActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // on cache la toolbar pour l'esthétisme
        getSupportActionBar().hide();

        Button boutonInformation = findViewById(R.id.bouton_revenir_menu);
        boutonInformation.setOnClickListener(view -> ouvreActiviteMain());

    }

    private void ouvreActiviteMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}