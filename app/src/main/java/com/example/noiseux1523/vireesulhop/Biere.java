package com.example.noiseux1523.vireesulhop;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Biere extends AppCompatActivity {

    // Variables
    private TextView nom;
    private TextView version;
    private TextView version_value;
    private TextView abv;
    private TextView abv_value;
    private TextView ibu;
    private TextView ibu_value;
    private TextView description;
    private TextView ingredients;
    private TextView ingredients_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biere);

        // Set Phenomena font to buttons
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Phenomena-Bold.otf");

        // Nom
        nom = (TextView)findViewById(R.id.nom);
        nom.setTypeface(custom_font);

        // Version
        version = (TextView)findViewById(R.id.version);
        version.setTypeface(custom_font);

        // ABV
        abv = (TextView)findViewById(R.id.abv);
        abv.setTypeface(custom_font);

        // IBU
        ibu = (TextView)findViewById(R.id.ibu);
        ibu.setTypeface(custom_font);

        // Description
        description = (TextView)findViewById(R.id.description);
        description.setTypeface(custom_font);

        // Ingredients
        ingredients = (TextView)findViewById(R.id.ingredients);
        ingredients.setTypeface(custom_font);


    }

}
