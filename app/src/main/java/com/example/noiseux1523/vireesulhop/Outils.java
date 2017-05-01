package com.example.noiseux1523.vireesulhop;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public class Outils extends AppCompatActivity {

    // Variables
    private TextView FtoC;
    private TextView HtoV;
    private TextView gravity;
    private TextView hydrometer;
    private TextView sugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils);

        // Evaluation button
        FtoC = (TextView)findViewById(R.id.FtoC);
        FtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farenheitToCelsius();
            }
        });

        // Beer List button
        HtoV = (TextView)findViewById(R.id.HtoV);
        HtoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightToVolume();
            }
        });

        // Procedure button
        gravity = (TextView)findViewById(R.id.gravity);
        gravity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravityAdjust();
            }
        });

        // Tools button
        hydrometer = (TextView)findViewById(R.id.hydrometer);
        hydrometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hydrometerAdjust();
            }
        });

        // Tools button
        sugar = (TextView)findViewById(R.id.sugar);
        sugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sugarPriming();
            }
        });

    }

    public void farenheitToCelsius() {
        // Create table window
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Farenheit to Celsius");
        ScrollView scroll = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
        scroll.addView(layout);
        dialog.setView(scroll);
        dialog.show();
    }

    public void heightToVolume() {
        // Create table window
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Height to Volume");

        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);

        TableLayout layout = new TableLayout(this);
        layout.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT));
        layout.setOrientation(TableLayout.VERTICAL);

        for (int i = 0; i < 40; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(tableParams);

            TextView H = new TextView(this);
            H.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
            H.setText(Integer.toString(i+1));
            H.setLayoutParams(rowParams);

            TextView V = new TextView(this);
            V.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
            double volume = (Math.pow((Math.PI*((15/2)*2.54)), 2))*(i+1)/1000;
            V.setText(String.format("%.2f", volume));
            V.setLayoutParams(rowParams);

            row.addView(H);
            row.addView(V);
            layout.addView(row);
        }

        scroll.addView(layout);
        dialog.setView(scroll);
        dialog.show();
    }

    public void gravityAdjust() {
        // Create table window
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Gravity Adjust");
        ScrollView scroll = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        scroll.addView(layout);
        dialog.setView(scroll);
        dialog.show();
    }

    public void hydrometerAdjust() {
        // Create table window
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Hydrometer Adjust");
        ScrollView scroll = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        scroll.addView(layout);
        dialog.setView(scroll);
        dialog.show();
    }

    public void sugarPriming() {
        // Create table window
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Sugar Priming");
        ScrollView scroll = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        scroll.addView(layout);
        dialog.setView(scroll);
        dialog.show();
    }

}
