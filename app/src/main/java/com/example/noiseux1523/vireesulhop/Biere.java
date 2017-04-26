package com.example.noiseux1523.vireesulhop;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.noiseux1523.vireesulhop.R.id.bieres;
import static com.example.noiseux1523.vireesulhop.R.id.evaluer;
import static com.example.noiseux1523.vireesulhop.R.id.outils;
import static com.example.noiseux1523.vireesulhop.R.id.procedure;

public class Biere extends Activity {

    // Variables
    private TextView version;
    private TextView abv;
    private TextView ibu;
    private TextView description;
    private TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biere);

        // Set Phenomena font to buttons
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Phenomena-Bold.otf");

        // Version text
        version = (TextView)findViewById(R.id.version);
        version.setTypeface(custom_font);

        // ABV text
        abv = (TextView)findViewById(R.id.abv);
        abv.setTypeface(custom_font);

        // IBU text
        ibu = (TextView)findViewById(R.id.ibu);
        ibu.setTypeface(custom_font);

        // Description text
        description = (TextView)findViewById(R.id.description);
        description.setTypeface(custom_font);

        // Ingredients text
        ingredients = (TextView)findViewById(R.id.ingredients);
        ingredients.setTypeface(custom_font);


    }

}
