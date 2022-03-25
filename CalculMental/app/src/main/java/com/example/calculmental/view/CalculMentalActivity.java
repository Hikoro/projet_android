package com.example.calculmental.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.calculmental.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CalculMentalActivity extends AppCompatActivity {
    private  int min = 1;
    private int max = 200;
    private int premierElementAleatoire =0;
    private int deuxiemeElementAleatoire =0;
    private double resultatCalculQuestion = 0;
    private int resultatCalculUtilisateur = 0;
    private int vie = 3;
    private int score = 0;
    private TextView textViewCalcul;
    private TextView textViewReponseCalcul;
    private int BORNE_HAUTE = 999999;

    private TextView textViewScore;
    private TextView textViewVie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_calcul_mental);

        // textView pour la question
        textViewCalcul = findViewById(R.id.textView_calcul);

        // textView pour la réponse de l'utilisateur
        textViewReponseCalcul = findViewById(R.id.textView_reponse_utilisateur);

        // boutons pour la réponse de l'utilisateur
        Button bouton1 = findViewById(R.id.bouton_1);
        bouton1.setOnClickListener(view -> ajouterNombre(1));
        Button bouton2 = findViewById(R.id.bouton_2);
        bouton2.setOnClickListener(view -> ajouterNombre(2));
        Button bouton3 = findViewById(R.id.bouton_3);
        bouton3.setOnClickListener(view -> ajouterNombre(3));
        Button bouton4 = findViewById(R.id.bouton_4);
        bouton4.setOnClickListener(view -> ajouterNombre(4));
        Button bouton5 = findViewById(R.id.bouton_5);
        bouton5.setOnClickListener(view -> ajouterNombre(5));
        Button bouton6 = findViewById(R.id.bouton_6);
        bouton6.setOnClickListener(view -> ajouterNombre(6));
        Button bouton7 = findViewById(R.id.bouton_7);
        bouton7.setOnClickListener(view -> ajouterNombre(7));
        Button bouton8 = findViewById(R.id.bouton_8);
        bouton8.setOnClickListener(view -> ajouterNombre(8));
        Button bouton9 = findViewById(R.id.bouton_9);
        bouton9.setOnClickListener(view -> ajouterNombre(9));
        Button bouton0 = findViewById(R.id.bouton_0);
        bouton0.setOnClickListener(view -> ajouterNombre(0));

        // bouton effacer un chiffre
        Button boutonEffacer = findViewById(R.id.bouton_effacer);
        boutonEffacer.setOnClickListener(view -> supprimerNombre());

        // bouton pour que le resultat du calcul soit négatif
        Button boutonNegatif = findViewById(R.id.bouton_negatif);
        boutonNegatif.setOnClickListener(view -> signeResultat());

        // on rédige le calcul pour l'utilisateur
        calculPourUtilisateur();

        // Dès que l'utilisateur appuie sur valider on compare la valeur de la text view de l'utilisateur et le résultat
            // si identique : l'utilisateur gagne +1
            // si différent : l'utilisateur perd une vie
        Button validation = findViewById(R.id.bouton_valider_calcul_mental);
        validation.setOnClickListener(view -> compareQuestionReponseResultat());

        // affichage score et vie
        textViewScore = findViewById(R.id.textViewCompteurScore);
        textViewVie = findViewById(R.id.textViewCompteurVie);

        // on cache la toolbar pour l'esthétisme
        getSupportActionBar().hide();

    }


    private void signeResultat() {
        resultatCalculUtilisateur = - resultatCalculUtilisateur;
        majTextView();
    }

    private void supprimerNombre() {
        if (resultatCalculUtilisateur != 0){
            resultatCalculUtilisateur = (int)resultatCalculUtilisateur/10;
            majTextView();
        }
    }

    // fonction qui donne un calcul pour l'utilisateur
    private void calculPourUtilisateur(){
        // on donne un nombre aléatoire aux deux variables
        Random random = new Random();
        premierElementAleatoire = random.nextInt(max+min)+min;
        deuxiemeElementAleatoire = random.nextInt(max+min)+min;

        // on tire au hasard d'une liste un indice contenant un type d'opération
        List<String> listeOperationEnum = new ArrayList<String>();
        listeOperationEnum.add("+");
        listeOperationEnum.add("-");
        listeOperationEnum.add("x");


        int indiceAuHasard = (int) (Math.random() * (listeOperationEnum.size()));



        // on calcule le vrai résultat
        if (indiceAuHasard==0){
            resultatCalculQuestion = premierElementAleatoire+deuxiemeElementAleatoire;
        }

        if (indiceAuHasard == 1){
            resultatCalculQuestion = premierElementAleatoire-deuxiemeElementAleatoire;
        }

        if (indiceAuHasard == 2){
            resultatCalculQuestion = premierElementAleatoire*deuxiemeElementAleatoire;
        }



        // on affiche le calcul dans la textView
        String valeurAAfficher = premierElementAleatoire + " "+ listeOperationEnum.get(indiceAuHasard)+" "+ deuxiemeElementAleatoire;
        textViewCalcul.setText(valeurAAfficher);
    }


    // fonction qui ajoute le nombre dans la textView de la réponse de l'utilisateur
    private void ajouterNombre(Integer valeur){


        if (10*resultatCalculUtilisateur>BORNE_HAUTE){
            Toast.makeText(this,getString(R.string.message_valeur_trop_grande),Toast.LENGTH_LONG).show();
        }
        else
            resultatCalculUtilisateur = 10 * resultatCalculUtilisateur+valeur;

        majTextView();
    }

    // fonction qui affiche le résultat de l'utilisateur
    private void majTextView() {
        String valeurAAfficher = "" + resultatCalculUtilisateur;
        textViewReponseCalcul.setText(valeurAAfficher);
    }

    // fonction qui compare le résultat entre la question et la réponse de l'utilisateur
    private void compareQuestionReponseResultat(){
        if (resultatCalculQuestion == resultatCalculUtilisateur){
            // Bonne réponse
            score += 1;
            textViewScore.setText("Score: "+score);
            // son erreur
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.reussi);
            mediaPlayer.start();

            Toast.makeText(this,getString(R.string.message_bonne_reponse),Toast.LENGTH_LONG).show();
            // Changement de question
            calculPourUtilisateur();
            // On efface la réponse précédente de l'utilisateur
            resultatCalculUtilisateur = 0;
            majTextView();

        }
        else{
            // Mauvaise réponse
            vie = vie - 1;
            Toast.makeText(this,getString(R.string.message_mauvaise_reponse),Toast.LENGTH_LONG).show();

            // son erreur
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.erreur);
            mediaPlayer.start();

            // perdu?
            majVieRestante();

        }
    }

    private void majVieRestante(){
        textViewVie.setText(getString(R.string.viesRestantes)+vie);
        if (vie <= 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(CalculMentalActivity.this);
            builder.setTitle(R.string.VousAvezPerdu);
            builder.setMessage(getString(R.string.votreScoreEstDe) + score);

            builder.create().show();

            ouvreSaisirPseudoActivity();
        }
    }


    private void ouvreSaisirPseudoActivity() {
        Intent intent = new Intent(this, SaisirPseudoActivity.class);
        startActivity(intent);
    }


}