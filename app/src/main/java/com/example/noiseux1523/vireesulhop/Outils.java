package com.example.noiseux1523.vireesulhop;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import static com.example.noiseux1523.vireesulhop.R.drawable.edittext_border;

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
                celsiusToFarenheit();
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

    public void celsiusToFarenheit() {
        // Create AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("Celsius to Farenheit");
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);
        dialog.setCustomTitle(title);

        // Create ScrollView (whole dialog)
        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        // Create LinearLayout (whole dialog, in ScrollView)
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 40; i++) {
            // Create TableRow (rows containing height and volume)
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 2f);
            row.setLayoutParams(params1);

            // Create TextView for celsius
            TextView H = new TextView(this);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
            H.setLayoutParams(params2);
            H.setGravity(Gravity.CENTER);
            H.setText(Integer.toString(i*5));
            H.setTextSize(20);

            // Create TextView for farenheit
            TextView V = new TextView(this);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);
            V.setLayoutParams(params3);
            V.setGravity(Gravity.CENTER);
            double volume = ((i*5) * (9/5)) + 32;
            V.setText(String.format("%.0f", volume));
            V.setTextSize(20);

            // To alternate colors
            if ((i & 1) == 0) {
                row.setBackgroundResource(R.color.white);
            } else {
                row.setBackgroundResource(R.color.background_color);
                H.setTextColor(Color.parseColor("#FFFFFF"));
                V.setTextColor(Color.parseColor("#FFFFFF"));
            }

            row.addView(H);
            row.addView(V);
            layout.addView(row);
        }

        scroll.addView(layout);
        dialog.setView(scroll);
        dialog.show();
    }

    public void heightToVolume() {
        // Create AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("Height to Volume");
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);
        dialog.setCustomTitle(title);

        // Create ScrollView (whole dialog)
        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        // Create LinearLayout (whole dialog, in ScrollView)
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 40; i++) {
            // Create TableRow (rows containing height and volume)
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 2f);
            row.setLayoutParams(params1);

            // Create TextView for height
            TextView H = new TextView(this);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
            H.setLayoutParams(params2);
            H.setGravity(Gravity.CENTER);
            H.setText(Integer.toString(i+1));
            H.setTextSize(20);

            // Create TextView for volume
            TextView V = new TextView(this);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);
            V.setLayoutParams(params3);
            V.setGravity(Gravity.CENTER);
            double volume = (Math.PI * (19.05) * (19.05) * (i+1)) / 1000;
            V.setText(String.format("%.2f", volume));
            V.setTextSize(20);

            // To alternate colors
            if ((i & 1) == 0) {
                row.setBackgroundResource(R.color.white);
            } else {
                row.setBackgroundResource(R.color.background_color);
                H.setTextColor(Color.parseColor("#FFFFFF"));
                V.setTextColor(Color.parseColor("#FFFFFF"));
            }

            row.addView(H);
            row.addView(V);
            layout.addView(row);
        }

        scroll.addView(layout);
        dialog.setView(scroll);
        dialog.show();
    }

    public void hydrometerAdjust() {
        // Create AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("Hydrometer Adjust");
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);
        dialog.setCustomTitle(title);

        // Set layout parameters
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 3f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 2f);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);

        // Create LinearLayout
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Create TableRow
        TableRow rowVol = new TableRow(this);
        rowVol.setLayoutParams(params1);
        rowVol.setPadding(10, 10, 10, 10);

        // Create TextView for Start Volume
        TextView volume = new TextView(this);
        volume.setLayoutParams(params2);
        volume.setGravity(Gravity.CENTER);
        volume.setText("Specific Gravity");
        volume.setTypeface(Typeface.DEFAULT_BOLD);
        volume.setTextColor(Color.parseColor("#000000"));
        volume.setTextSize(20);

        // Create TextView for Volume Input
        EditText volumeInput = new EditText(this);
        volumeInput.setLayoutParams(params3);
        volumeInput.setGravity(Gravity.CENTER);
        volumeInput.setBackground(getResources().getDrawable(edittext_border));
        volumeInput.setCursorVisible(false);
        volumeInput.setHint("1.05");
        volumeInput.setTextColor(Color.parseColor("#000000"));
        volumeInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        volumeInput.setTextSize(20);

        rowVol.addView(volume);
        rowVol.addView(volumeInput);
        layout.addView(rowVol);

        // Create TableRow
        TableRow rowOG = new TableRow(this);
        rowOG.setLayoutParams(params1);
        rowOG.setPadding(10, 10, 10, 10);

        // Create TextView for OG
        TextView og = new TextView(this);
        og.setLayoutParams(params2);
        og.setGravity(Gravity.CENTER);
        og.setText("Recorded Temp (C)");
        og.setTypeface(Typeface.DEFAULT_BOLD);
        og.setTextColor(Color.parseColor("#000000"));
        og.setTextSize(20);

        // Create TextView for OG Input
        EditText ogInput = new EditText(this);
        ogInput.setLayoutParams(params3);
        ogInput.setGravity(Gravity.CENTER);
        ogInput.setBackground(getResources().getDrawable(edittext_border));
        ogInput.setCursorVisible(false);
        ogInput.setHint("65");
        ogInput.setTextColor(Color.parseColor("#000000"));
        ogInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        ogInput.setTextSize(20);

        rowOG.addView(og);
        rowOG.addView(ogInput);
        layout.addView(rowOG);

        // Create TableRow
        TableRow rowTG = new TableRow(this);
        rowTG.setLayoutParams(params1);
        rowTG.setPadding(10, 10, 10, 10);

        // Create TextView for TG
        TextView tg = new TextView(this);
        tg.setLayoutParams(params2);
        tg.setGravity(Gravity.CENTER);
        tg.setText("Calibrated Temp (C)");
        tg.setTypeface(Typeface.DEFAULT_BOLD);
        tg.setTextColor(Color.parseColor("#000000"));
        tg.setTextSize(20);

        // Create TextView for TG Input
        EditText tgInput = new EditText(this);
        tgInput.setLayoutParams(params3);
        tgInput.setGravity(Gravity.CENTER);
        tgInput.setBackground(getResources().getDrawable(edittext_border));
        tgInput.setCursorVisible(false);
        tgInput.setText("15");
        tgInput.setTextColor(Color.parseColor("#000000"));
        tgInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        tgInput.setTextSize(20);

        rowTG.addView(tg);
        rowTG.addView(tgInput);
        layout.addView(rowTG);

        dialog.setView(layout);
        dialog.show();

    }

    public void gravityAdjust() {
        // Create AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("Gravity Adjust");
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);
        dialog.setCustomTitle(title);

        // Set layout parameters
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 3f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 2f);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);

        // Create LinearLayout
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Create TableRow
        TableRow rowVol = new TableRow(this);
        rowVol.setLayoutParams(params1);
        rowVol.setPadding(10, 10, 10, 10);

        // Create TextView for Start Volume
        TextView volume = new TextView(this);
        volume.setLayoutParams(params2);
        volume.setGravity(Gravity.CENTER);
        volume.setText("Start Volume (L)");
        volume.setTypeface(Typeface.DEFAULT_BOLD);
        volume.setTextColor(Color.parseColor("#000000"));
        volume.setTextSize(20);

        // Create TextView for Volume Input
        EditText volumeInput = new EditText(this);
        volumeInput.setLayoutParams(params3);
        volumeInput.setGravity(Gravity.CENTER);
        volumeInput.setBackground(getResources().getDrawable(edittext_border));
        volumeInput.setCursorVisible(false);
        volumeInput.setHint("20");
        volumeInput.setTextColor(Color.parseColor("#000000"));
        volumeInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        volumeInput.setTextSize(20);

        rowVol.addView(volume);
        rowVol.addView(volumeInput);
        layout.addView(rowVol);

        // Create TableRow
        TableRow rowOG = new TableRow(this);
        rowOG.setLayoutParams(params1);
        rowOG.setPadding(10, 10, 10, 10);

        // Create TextView for OG
        TextView og = new TextView(this);
        og.setLayoutParams(params2);
        og.setGravity(Gravity.CENTER);
        og.setText("Original Gravity");
        og.setTypeface(Typeface.DEFAULT_BOLD);
        og.setTextColor(Color.parseColor("#000000"));
        og.setTextSize(20);

        // Create TextView for OG Input
        EditText ogInput = new EditText(this);
        ogInput.setLayoutParams(params3);
        ogInput.setGravity(Gravity.CENTER);
        ogInput.setBackground(getResources().getDrawable(edittext_border));
        ogInput.setCursorVisible(false);
        ogInput.setHint("1.05");
        ogInput.setTextColor(Color.parseColor("#000000"));
        ogInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        ogInput.setTextSize(20);

        rowOG.addView(og);
        rowOG.addView(ogInput);
        layout.addView(rowOG);

        // Create TableRow
        TableRow rowTG = new TableRow(this);
        rowTG.setLayoutParams(params1);
        rowTG.setPadding(10, 10, 10, 10);

        // Create TextView for TG
        TextView tg = new TextView(this);
        tg.setLayoutParams(params2);
        tg.setGravity(Gravity.CENTER);
        tg.setText("Target Gravity");
        tg.setTypeface(Typeface.DEFAULT_BOLD);
        tg.setTextColor(Color.parseColor("#000000"));
        tg.setTextSize(20);

        // Create TextView for TG Input
        EditText tgInput = new EditText(this);
        tgInput.setLayoutParams(params3);
        tgInput.setGravity(Gravity.CENTER);
        tgInput.setBackground(getResources().getDrawable(edittext_border));
        tgInput.setCursorVisible(false);
        tgInput.setHint("1.03");
        tgInput.setTextColor(Color.parseColor("#000000"));
        tgInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        tgInput.setTextSize(20);

        rowTG.addView(tg);
        rowTG.addView(tgInput);
        layout.addView(rowTG);

        dialog.setView(layout);
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
