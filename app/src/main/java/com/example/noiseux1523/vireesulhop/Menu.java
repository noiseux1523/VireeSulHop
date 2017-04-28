package com.example.noiseux1523.vireesulhop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    // Variables
    private TextView evaluer;
    private TextView bieres;
    private TextView procedure;
    private TextView outils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Set Phenomena font to buttons
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Phenomena-Bold.otf");

        // Evaluation button
        evaluer = (TextView)findViewById(R.id.evaluer);
        evaluer.setTypeface(custom_font);
        evaluer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluation();
            }
        });

        // Beer List button
        bieres = (TextView)findViewById(R.id.bieres);
        bieres.setTypeface(custom_font);
        bieres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beerList();
            }
        });

        // Procedure button
        procedure = (TextView)findViewById(R.id.procedure);
        procedure.setTypeface(custom_font);
        procedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procedure();
            }
        });

        // Tools button
        outils = (TextView)findViewById(R.id.outils);
        outils.setTypeface(custom_font);
        outils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tools();
            }
        });
    }

    public void evaluation() {
        Intent intent = new Intent(this, Evaluation.class);
        startActivity(intent);
    }

    public void beerList() {
        Intent intent = new Intent(this, Biere.class);
        startActivity(intent);
    }

    public void procedure() {
        Intent intent = new Intent(this, Procedure.class);
        startActivity(intent);
    }

    public void tools() {
        Intent intent = new Intent(this, Outils.class);
        startActivity(intent);
    }

}
