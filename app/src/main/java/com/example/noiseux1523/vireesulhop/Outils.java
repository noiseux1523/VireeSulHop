package com.example.noiseux1523.vireesulhop;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.noiseux1523.vireesulhop.R.drawable.edittext_border;
import static com.example.noiseux1523.vireesulhop.R.drawable.toast_border;

public class Outils extends AppCompatActivity {

    // Button Variables
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
        title.setTypeface(Typeface.DEFAULT_BOLD);
        dialog.setCustomTitle(title);

        // Create ScrollView (whole dialog)
        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        // Create LinearLayout (whole dialog, in ScrollView)
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        // COLUMNS HEADERS
        // Create TableRow (rows containing height and volume)
        TableRow Hrow = new TableRow(this);
        TableRow.LayoutParams Hparams1 = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 2f);
        Hrow.setLayoutParams(Hparams1);

        // Create TextView for celsius
        TextView HC = new TextView(this);
        TableRow.LayoutParams Hparams2 = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
        HC.setLayoutParams(Hparams2);
        HC.setGravity(Gravity.CENTER);
        HC.setText("cm");
        HC.setTextSize(20);

        // Create TextView for farenheit
        TextView HF = new TextView(this);
        TableRow.LayoutParams Hparams3 = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);
        HF.setLayoutParams(Hparams3);
        HF.setGravity(Gravity.CENTER);
        HF.setText("L");
        HF.setTextSize(20);

        // Row color
        Hrow.setBackgroundResource(R.color.white);
        Hrow.setBackgroundResource(R.color.background_color);
        HC.setTextColor(Color.parseColor("#FFFFFF"));
        HF.setTextColor(Color.parseColor("#FFFFFF"));

        Hrow.addView(HC);
        Hrow.addView(HF);
        layout.addView(Hrow);

        for (int i = 0; i < 40; i++) {
            // Create TableRow (rows containing height and volume)
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT, 2f);
            row.setLayoutParams(params1);

            // Create TextView for celsius
            TextView C = new TextView(this);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
            C.setLayoutParams(params2);
            C.setGravity(Gravity.CENTER);
            C.setText(Integer.toString(i*5));
            C.setTextSize(20);

            // Create TextView for farenheit
            TextView F = new TextView(this);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);
            F.setLayoutParams(params3);
            F.setGravity(Gravity.CENTER);
            double temp = (i*5*1.8)+32;
            F.setText(String.format("%.0f", temp));
            F.setTextSize(20);

            // To alternate colors
            if ((i & 1) == 0) {
                row.setBackgroundResource(R.color.white);
            } else {
                row.setBackgroundResource(R.color.background_color);
                C.setTextColor(Color.parseColor("#FFFFFF"));
                F.setTextColor(Color.parseColor("#FFFFFF"));
            }

            row.addView(C);
            row.addView(F);
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

        // Create TextView for Gravity
        TextView sg = new TextView(this);
        sg.setLayoutParams(params2);
        sg.setGravity(Gravity.CENTER);
        sg.setText("Specific Gravity");
        sg.setTypeface(Typeface.DEFAULT_BOLD);
        sg.setTextColor(Color.parseColor("#000000"));
        sg.setTextSize(20);

        // Create TextView for Gravity Input
        final EditText sgInput = new EditText(this);
        sgInput.setLayoutParams(params3);
        sgInput.setGravity(Gravity.CENTER);
        sgInput.setBackground(getResources().getDrawable(edittext_border));
        sgInput.setCursorVisible(false);
        sgInput.setHint("1.05");
        sgInput.setTextColor(Color.parseColor("#000000"));
        sgInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        sgInput.setTextSize(20);

        rowVol.addView(sg);
        rowVol.addView(sgInput);
        layout.addView(rowVol);

        // Create TableRow
        TableRow rowOG = new TableRow(this);
        rowOG.setLayoutParams(params1);
        rowOG.setPadding(10, 10, 10, 10);

        // Create TextView for Recorded Temp
        TextView recTemp = new TextView(this);
        recTemp.setLayoutParams(params2);
        recTemp.setGravity(Gravity.CENTER);
        recTemp.setText("Recorded Temp (C)");
        recTemp.setTypeface(Typeface.DEFAULT_BOLD);
        recTemp.setTextColor(Color.parseColor("#000000"));
        recTemp.setTextSize(20);

        // Create TextView for Recorded Temp Input
        final EditText recTempInput = new EditText(this);
        recTempInput.setLayoutParams(params3);
        recTempInput.setGravity(Gravity.CENTER);
        recTempInput.setBackground(getResources().getDrawable(edittext_border));
        recTempInput.setCursorVisible(false);
        recTempInput.setHint("65");
        recTempInput.setTextColor(Color.parseColor("#000000"));
        recTempInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        recTempInput.setTextSize(20);

        rowOG.addView(recTemp);
        rowOG.addView(recTempInput);
        layout.addView(rowOG);

        // Create TableRow
        TableRow rowTG = new TableRow(this);
        rowTG.setLayoutParams(params1);
        rowTG.setPadding(10, 10, 10, 10);

        // Create TextView for Calibrated Temp
        TextView calTemp = new TextView(this);
        calTemp.setLayoutParams(params2);
        calTemp.setGravity(Gravity.CENTER);
        calTemp.setText("Calibrated Temp (C)");
        calTemp.setTypeface(Typeface.DEFAULT_BOLD);
        calTemp.setTextColor(Color.parseColor("#000000"));
        calTemp.setTextSize(20);

        // Create TextView for Calibrated Temp Input
        final EditText calTempInput = new EditText(this);
        calTempInput.setLayoutParams(params3);
        calTempInput.setGravity(Gravity.CENTER);
        calTempInput.setBackground(getResources().getDrawable(edittext_border));
        calTempInput.setCursorVisible(false);
        calTempInput.setText("15");
        calTempInput.setTextColor(Color.parseColor("#000000"));
        calTempInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        calTempInput.setTextSize(20);

        rowTG.addView(calTemp);
        rowTG.addView(calTempInput);
        layout.addView(rowTG);

        // Create TableRow
        TableRow rowAdjusted = new TableRow(this);
        rowAdjusted.setLayoutParams(params1);
        rowAdjusted.setPadding(10, 10, 10, 10);

        dialog.setView(layout);

        dialog.setPositiveButton("Calculate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if ((!sgInput.equals(null)) && (!recTempInput.equals(null)) && (!calTempInput.equals(null))) {
                        // Format values
                        double sg = Double.parseDouble(String.valueOf(sgInput.getText()));
                        double recTemp = Double.parseDouble(String.valueOf(recTempInput.getText()));
                        double calTemp = Double.parseDouble(String.valueOf(calTempInput.getText()));
                        double recTempF = (recTemp*1.8)+32;
                        double calTempF = (calTemp*1.8)+32;

                        // Formula
                        double correctedGravity = sg * ((1.00130346 - 0.000134722124 * recTempF + 0.00000204052596 * Math.pow(recTempF,2) - 0.00000000232820948 * Math.pow(recTempF,3) / (1.00130346 - 0.000134722124 * calTempF + 0.00000204052596 * Math.pow(calTempF,2) - 0.00000000232820948 * Math.pow(calTempF,3))));

                        // Custom toast view
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                        TextView layout = new TextView(Outils.this);
                        layout.setTextColor(Color.parseColor("#000000"));
                        layout.setTypeface(Typeface.DEFAULT_BOLD);
                        layout.setTextSize(22);
                        layout.setPadding(15, 15, 15, 15);
                        layout.setLayoutParams(params);
                        layout.setGravity(Gravity.CENTER);
                        layout.setMaxLines(4);
                        layout.setText("Initial Gravity" + "\n" + String.format("%.4f", sg) + "\n" + "Corrected Gravity" + "\n" + String.format("%.4f", correctedGravity));
                        layout.setBackground(getResources().getDrawable(toast_border));

                        // Create and customize toast
                        Toast toast = new Toast(Outils.this);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);

                        toast.show();

                    } else {
                        Toast.makeText(Outils.this,
                                "You are missing information!", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();

        Button pButton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pButton.setTextColor(Color.parseColor("#FF99CC00"));
        pButton.setGravity(Gravity.CENTER);
        pButton.setTextSize(20);
        pButton.setTypeface(Typeface.DEFAULT_BOLD);
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
        final EditText volumeInput = new EditText(this);
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
        final EditText ogInput = new EditText(this);
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
        final EditText tgInput = new EditText(this);
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

        dialog.setPositiveButton("Calculate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if ((!volumeInput.equals(null)) && (!ogInput.equals(null)) && (!tgInput.equals(null))) {
                        // Format values
                        double volume = Double.parseDouble(String.valueOf(volumeInput.getText()));
                        double og = Double.parseDouble(String.valueOf(ogInput.getText()));
                        double tg = Double.parseDouble(String.valueOf(tgInput.getText()));
                        double volumeGal = volume*0.264172;

                        // Custom toast view
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                        TextView layout = new TextView(Outils.this);
                        layout.setTextColor(Color.parseColor("#000000"));
                        layout.setTypeface(Typeface.DEFAULT_BOLD);
                        layout.setTextSize(22);
                        layout.setPadding(15, 15, 15, 15);
                        layout.setLayoutParams(params);
                        layout.setGravity(Gravity.CENTER);
                        layout.setMaxLines(2);
                        layout.setBackground(getResources().getDrawable(toast_border));

                        // Formula
                        if (og >= tg) {
                            double water = (volume*((og-1)/(tg-1)))-volume;
                            layout.setText("Water To Add" + "\n" +
                                    String.format("%.3f", water) + " L" + "\n");
                        } else {
                            double DMEPounds = 1000*volumeGal*((tg-1)-(og-1))/44;
                            double DME = DMEPounds*453.592;
                            layout.setText("Extract To Add" + "\n" +
                                    String.format("%.0f", DME) + " g" + "\n");
                        }

                        // Create and customize toast
                        Toast toast = new Toast(Outils.this);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);

                        toast.show();

                    } else {
                        Toast.makeText(Outils.this,
                                "You are missing information!", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();

        Button pButton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pButton.setTextColor(Color.parseColor("#FF99CC00"));
        pButton.setGravity(Gravity.CENTER);
        pButton.setTextSize(20);
        pButton.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void sugarPriming() {
        // Create AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("Sugar Priming");
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

        // Create TextView for Beer Temp
        TextView temp = new TextView(this);
        temp.setLayoutParams(params2);
        temp.setGravity(Gravity.CENTER);
        temp.setText("Beer Temperature (F)");
        temp.setTypeface(Typeface.DEFAULT_BOLD);
        temp.setTextColor(Color.parseColor("#000000"));
        temp.setTextSize(20);

        // Create TextView for Temp Input
        EditText tempInput = new EditText(this);
        tempInput.setLayoutParams(params3);
        tempInput.setGravity(Gravity.CENTER);
        tempInput.setBackground(getResources().getDrawable(edittext_border));
        tempInput.setCursorVisible(false);
        tempInput.setHint("24");
        tempInput.setTextColor(Color.parseColor("#000000"));
        tempInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        tempInput.setTextSize(20);

        rowVol.addView(temp);
        rowVol.addView(tempInput);
        layout.addView(rowVol);

        // Create TableRow
        TableRow rowOG = new TableRow(this);
        rowOG.setLayoutParams(params1);
        rowOG.setPadding(10, 10, 10, 10);

        // Create TextView for Volume
        TextView volume = new TextView(this);
        volume.setLayoutParams(params2);
        volume.setGravity(Gravity.CENTER);
        volume.setText("Beer Volume (gal)");
        volume.setTypeface(Typeface.DEFAULT_BOLD);
        volume.setTextColor(Color.parseColor("#000000"));
        volume.setTextSize(20);

        // Create TextView for Volume Input
        EditText volumeInput = new EditText(this);
        volumeInput.setLayoutParams(params3);
        volumeInput.setGravity(Gravity.CENTER);
        volumeInput.setBackground(getResources().getDrawable(edittext_border));
        volumeInput.setCursorVisible(false);
        volumeInput.setHint("5");
        volumeInput.setTextColor(Color.parseColor("#000000"));
        volumeInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        volumeInput.setTextSize(20);

        rowOG.addView(volume);
        rowOG.addView(volumeInput);
        layout.addView(rowOG);

        // Create TableRow
        TableRow rowTG = new TableRow(this);
        rowTG.setLayoutParams(params1);
        rowTG.setPadding(10, 10, 10, 10);

        // Create TextView for Beer Style
        TextView style = new TextView(this);
        style.setLayoutParams(params2);
        style.setGravity(Gravity.CENTER);
        style.setText("Beer Style");
        style.setTypeface(Typeface.DEFAULT_BOLD);
        style.setTextColor(Color.parseColor("#000000"));
        style.setTextSize(20);

        // Create TextView for TG Input
        EditText styleInput = new EditText(this);
        styleInput.setLayoutParams(params3);
        styleInput.setGravity(Gravity.CENTER);
        styleInput.setBackground(getResources().getDrawable(edittext_border));
        styleInput.setCursorVisible(false);
        styleInput.setHint("American IPA");
        styleInput.setTextColor(Color.parseColor("#000000"));
        styleInput.setHintTextColor(Color.parseColor("#FFBCBCBC"));
        styleInput.setTextSize(20);

        rowTG.addView(style);
        rowTG.addView(styleInput);
        layout.addView(rowTG);

        dialog.setView(layout);
        dialog.show();
    }

}
