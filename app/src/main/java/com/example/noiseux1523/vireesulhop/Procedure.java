package com.example.noiseux1523.vireesulhop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.OpenFileActivityBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static com.example.noiseux1523.vireesulhop.R.drawable.edittext_border;
import static com.example.noiseux1523.vireesulhop.R.drawable.toast_border;

public class Procedure extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    // EditTexts to save
    private EditText mashVolume;
    private EditText preBoilGravity;
    private EditText preBoilVolume;
    private EditText originalGravity;
    private EditText wortCollected;
    private EditText finalGravity;
    private EditText finalVolume;

    // Option TextViews
    private ImageButton tools;
    private ImageButton save;
    private ImageButton edit;
    private String comments = "Commentaires";
    private String saveFileName = "";
    private String currentFile = "";

    private static final String TAGREAD = "Read internal file";
    private static final String TAG = "Google Drive Activity";
    private static final int REQUEST_CODE_RESOLUTION = 1;
    private static final  int REQUEST_CODE_OPENER = 2;
    private GoogleApiClient mGoogleApiClient;
    private boolean fileOperation = false;
    private DriveId mFileId;
    public DriveFile file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure);

        tools = (ImageButton)findViewById(R.id.tools);
        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionTools();
            }
        });

        save = (ImageButton)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFileToSave();
            }
        });

        edit = (ImageButton)findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFileToEdit();
            }
        });

        mashVolume = (EditText) findViewById(R.id.editTextMashVolume);
        preBoilGravity = (EditText) findViewById(R.id.editTextPreBoilGravity);
        preBoilVolume = (EditText) findViewById(R.id.editTextPreBoilVolume);
        originalGravity = (EditText) findViewById(R.id.editTextOriginalGravity);
        wortCollected = (EditText) findViewById(R.id.editTextWortCollected);
        finalGravity = (EditText) findViewById(R.id.editTextFinalGravity);
        finalVolume = (EditText) findViewById(R.id.editTextFinalVolume);

        loadSavedPreferences();

        /**
         * Create the API client and bind it to an instance variable.
         * We use this instance as the callback for connection and connection failures.
         * Since no account name is passed, the user is prompted to choose.
         */
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                .build();
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        mashVolume.setText(sharedPreferences.getString("string_mashVolume", ""));
        preBoilGravity.setText(sharedPreferences.getString("string_preBoilGravity", ""));
        preBoilVolume.setText(sharedPreferences.getString("string_preBoilVolume", ""));
        originalGravity.setText(sharedPreferences.getString("string_originalGravity", ""));
        wortCollected.setText(sharedPreferences.getString("string_wortCollected", ""));
        finalGravity.setText(sharedPreferences.getString("string_finalGravity", ""));
        finalVolume.setText(sharedPreferences.getString("string_finalVolume", ""));
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveData(){
        savePreferences("string_mashVolume", mashVolume.getText().toString());
        savePreferences("string_preBoilGravity", preBoilGravity.getText().toString());
        savePreferences("string_preBoilVolume", preBoilVolume.getText().toString());
        savePreferences("string_originalGravity", originalGravity.getText().toString());
        savePreferences("string_wortCollected", wortCollected.getText().toString());
        savePreferences("string_finalGravity", finalGravity.getText().toString());
        savePreferences("string_finalVolume", finalVolume.getText().toString());
    }

    public void clearData(){
        savePreferences("string_mashVolume", "");
        savePreferences("string_preBoilGravity", "");
        savePreferences("string_preBoilVolume", "");
        savePreferences("string_originalGravity", "");
        savePreferences("string_wortCollected", "");
        savePreferences("string_finalGravity", "");
        savePreferences("string_finalVolume", "");
    }

    public void clearAll() {
        clearData();
        mashVolume.setText("");
        preBoilGravity.setText("");
        preBoilVolume.setText("");
        originalGravity.setText("");
        wortCollected.setText("");
        finalGravity.setText("");
        finalVolume.setText("");
    }

    public void chooseFileToEdit() {
        // Create dialog window
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Files");

        // Get all files in directory
        String path = String.valueOf(getFilesDir());
        File directory = new File(path);
        final File[] files = directory.listFiles();
        List<String> allFilesTmp = new ArrayList<String>();
        for (int i = 0; i < files.length; i++)
        {
            allFilesTmp.add(i, files[i].getName());
        }
        CharSequence[] allFiles = allFilesTmp.toArray(new CharSequence[allFilesTmp.size()]);

        // Decide which file to edit
        builder.setItems(allFiles,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String filename = files[which].getName();
                        optionEdit(filename);
                    }
                });
        builder.setPositiveButton("New File", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createNewEditFile();
            }
        });


        builder.create().show();
    }

    public void optionEdit(final String filename) {
        // Initialize reader and file to read
        BufferedReader input = null;
        File file = null;
        try {
            file = new File(getFilesDir(), filename);

            // Read line by line the file. Add a new line after each line read,
            // otherwise they aren't added.
            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }

            // Update the comments string and log
            comments = buffer.toString();
            Log.d(TAGREAD, buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Commentaires");

        // Set up the input
        final EditText inputBox = new EditText(this);
        inputBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        inputBox.setText(comments);
        inputBox.setWidth(391);
        inputBox.setHeight(523);
        inputBox.setGravity(Gravity.TOP);
        inputBox.setPadding(10, 10, 10, 10);
        builder.setView(inputBox);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Read the text box and update the comments string
                comments = inputBox.getText().toString();

                // Save the edit to the file
                try {
                    saveToFile(filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Save nothing and exit
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void createNewEditFile() {
        // Create save window
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Save Name
        TextView fileNameTitle = new TextView(this);
        fileNameTitle.setText("File Name");
        fileNameTitle.setTextColor(Color.BLACK);
        fileNameTitle.setTextSize(16);
        fileNameTitle.setPadding(0, 15, 0, 0);
        fileNameTitle.setGravity(Gravity.CENTER);
        layout.addView(fileNameTitle);

        final EditText saveName = new EditText(this);
        saveName.setTextSize(16);
        layout.addView(saveName);
        dialog1.setView(layout);

        dialog1.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Create a file in the Internal Storage
                String content = "";

                FileOutputStream outputStream = null;
                try {
                    outputStream = openFileOutput(saveName.getText().toString(), Context.MODE_PRIVATE);
                    outputStream.write(content.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        dialog1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss window
            }
        });

        dialog1.show();
    }

    public void chooseFileToSave() {
        // Decide which file to edit
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Files");

        // Get all files in directory
        String path = String.valueOf(getFilesDir());
        File directory = new File(path);
        final File[] files = directory.listFiles();
        List<String> allFilesTmp = new ArrayList<String>();
        for (int i = 0; i < files.length; i++)
        {
            allFilesTmp.add(i, files[i].getName());
        }
        CharSequence[] allFiles = allFilesTmp.toArray(new CharSequence[allFilesTmp.size()]);
        builder.setItems(allFiles,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String filename = files[which].getName();
                        optionSave(filename);
                    }
                });
        builder.create().show();
    }

    public void saveToFile(String filename) throws IOException {
        // Assign file name and string to save
        String string = comments;

        // Open file and write the comments
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = String.valueOf(getFilesDir());
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
        }
    }

    //
    // ADD OPTION TO DECIDE THE NAME OF THE FILE TO SAVE IF NEW FILE
    // ADD OPTION TO DECIDE WHICH FILE TO SAVE AND OVERWRITE IF EXISTING FILE
    // DO THIS FOR THE FILES IN THE DRIVE, NOT THE LOCAL PHONE FILE
    //

    public void optionSave(final String filename) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menu");
        builder.setItems(new CharSequence[]{
                        "Create New File",
                        "Overwrite Existing File"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                currentFile = filename;
                                onClickCreateFile();
                                break;
                            case 1:
                                onClickOpenFile();
                                break;
                        }
                    }
                });
        builder.create().show();

    }

    public void optionTools() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tools");
        builder.setItems(new CharSequence[]{"Celsius to Farenheit",
                        "Height to Volume",
                        "Gallon to Liter",
                        "Gravity Adjust",
                        "Hydrometer Adjust",
                        "Sugar Priming",
                        "Clear All"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                celsiusToFarenheit();
                                break;
                            case 1:
                                heightToVolume();
                                break;
                            case 2:
                                gallonToLiter();
                                break;
                            case 3:
                                gravityAdjust();
                                break;
                            case 4:
                                hydrometerAdjust();
                                break;
                            case 5:
                                sugarPriming();
                                break;
                            case 6:
                                clearAll();
                        }
                    }
                });
        builder.create().show();
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
        scroll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Create LinearLayout (whole dialog, in ScrollView)
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 40; i++) {
            // Create TableRow (rows containing height and volume)
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
            row.setLayoutParams(params1);

            // Create TextView for celsius
            TextView C = new TextView(this);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            C.setLayoutParams(params2);
            C.setGravity(Gravity.CENTER);
            C.setText(Integer.toString(i*5));
            C.setTextSize(20);

            // Create TextView for farenheit
            TextView F = new TextView(this);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
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
        scroll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Create LinearLayout (whole dialog, in ScrollView)
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 40; i++) {
            // Create TableRow (rows containing height and volume)
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
            row.setLayoutParams(params1);

            // Create TextView for height
            TextView H = new TextView(this);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            H.setLayoutParams(params2);
            H.setGravity(Gravity.CENTER);
            H.setText(Integer.toString(i+1));
            H.setTextSize(20);

            // Create TextView for volume
            TextView V = new TextView(this);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
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

    public void gallonToLiter() {
        // Create AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("Gallon to Liter");
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);
        dialog.setCustomTitle(title);

        // Create ScrollView (whole dialog)
        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Create LinearLayout (whole dialog, in ScrollView)
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 40; i++) {
            // Create TableRow (rows containing height and volume)
            TableRow row = new TableRow(this);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
            row.setLayoutParams(params1);

            // Create TextView for height
            TextView G = new TextView(this);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            G.setLayoutParams(params2);
            G.setGravity(Gravity.CENTER);
            double gallon = ((double) i)/4+0.25;
            G.setText(String.format("%.2f", gallon));
            G.setTextSize(20);

            // Create TextView for volume
            TextView L = new TextView(this);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
            L.setLayoutParams(params3);
            L.setGravity(Gravity.CENTER);
            double liter = 3.78541*gallon;
            L.setText(String.format("%.2f", liter));
            L.setTextSize(20);

            // To alternate colors
            if ((i & 1) == 0) {
                row.setBackgroundResource(R.color.white);
            } else {
                row.setBackgroundResource(R.color.background_color);
                G.setTextColor(Color.parseColor("#FFFFFF"));
                L.setTextColor(Color.parseColor("#FFFFFF"));
            }

            row.addView(G);
            row.addView(L);
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);

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
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        TextView layout = new TextView(Procedure.this);
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
                        Toast toast = new Toast(Procedure.this);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);

                        toast.show();

                    } else {
                        Toast.makeText(Procedure.this,
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);

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
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        TextView layout = new TextView(Procedure.this);
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
                        Toast toast = new Toast(Procedure.this);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);

                        toast.show();

                    } else {
                        Toast.makeText(Procedure.this,
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f);
        TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);

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

    @Override
    public void onBackPressed(){
        saveData();
        super.onBackPressed();
    }

    /**
     * Called when the activity will start interacting with the user.
     * At this point your activity is at the top of the activity stack,
     * with user input going to it.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient == null) {

        }

        mGoogleApiClient.connect();
    }

    @Override
    protected void onStart() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            // disconnect Google Android Drive API connection.
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {

        // Called whenever the API client fails to connect.
        Log.i(TAG, "GoogleApiClient connection failed: " + result.toString());

        if (!result.hasResolution()) {

            // show the localized error dialog.
            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show();
            return;
        }

        /**
         *  The failure has a resolution. Resolve it.
         *  Called typically when the app is not yet authorized, and an  authorization
         *  dialog is displayed to the user.
         */

        try {

            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);

        } catch (IntentSender.SendIntentException e) {

            Log.e(TAG, "Exception while starting resolution activity", e);
        }
    }

    /**
     * It invoked when Google API client connected
     * @param connectionHint
     */
    @Override
    public void onConnected(Bundle connectionHint) {

        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
    }

    /**
     * It invoked when connection suspended
     * @param cause
     */
    @Override
    public void onConnectionSuspended(int cause) {

        Log.i(TAG, "GoogleApiClient connection suspended");
    }

    public void onClickCreateFile(){
        fileOperation = true;

        // Create save window
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Save Name
        TextView fileNameTitle = new TextView(this);
        fileNameTitle.setText("File Name");
        fileNameTitle.setTextColor(Color.BLACK);
        fileNameTitle.setTextSize(16);
        fileNameTitle.setPadding(0, 15, 0, 0);
        fileNameTitle.setGravity(Gravity.CENTER);
        layout.addView(fileNameTitle);

        final EditText saveName = new EditText(this);
        saveName.setTextSize(16);
        layout.addView(saveName);
        dialog.setView(layout);

        dialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get file name
                saveFileName = saveName.getText().toString();
                if (saveFileName != null && saveFileName.length() != 0) {
                    // create new file
                    Drive.DriveApi.newDriveContents(mGoogleApiClient)
                            .setResultCallback(driveContentsCallback);
                } else {
                    Toast toast = Toast.makeText(Procedure.this,
                            "Enter a valid name", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss window
            }
        });

        dialog.show();

    }

    public void onClickOpenFile(){
        fileOperation = false;

        // create new contents resource
        Drive.DriveApi.newDriveContents(mGoogleApiClient)
                .setResultCallback(driveContentsCallback);
    }

    /**
     *  Open list of folder and file of the Google Drive
     */
    public void OpenFileFromGoogleDrive(){

        IntentSender intentSender = Drive.DriveApi
                .newOpenFileActivityBuilder()
                .setMimeType(new String[] { "text/plain", "text/html" })
                .build(mGoogleApiClient);
        try {
            startIntentSenderForResult(

                    intentSender, REQUEST_CODE_OPENER, null, 0, 0, 0);

        } catch (IntentSender.SendIntentException e) {

            Log.w(TAG, "Unable to send intent", e);
        }

    }

    /**
     * This is Result result handler of Drive contents.
     * this callback method call CreateFileOnGoogleDrive() method
     * and also call OpenFileFromGoogleDrive() method,
     * send intent onActivityResult() method to handle result.
     */
    final ResultCallback<DriveApi.DriveContentsResult> driveContentsCallback =
            new ResultCallback<DriveApi.DriveContentsResult>() {
                @Override
                public void onResult(DriveApi.DriveContentsResult result) {

                    if (result.getStatus().isSuccess()) {

                        if (fileOperation == true) {

                            CreateFileOnGoogleDrive(result);

                        } else {

                            OpenFileFromGoogleDrive();

                        }
                    }

                }
            };

    /**
     * Create a file in root folder using MetadataChangeSet object.
     * @param result
     */
    public void CreateFileOnGoogleDrive(DriveApi.DriveContentsResult result){

        final DriveContents driveContents = result.getDriveContents();

        // Perform I/O off the UI thread.
        new Thread() {
            @Override
            public void run() {
                // Write content to DriveContents
                BufferedReader input = null;
                File file = null;
                try {
                    file = new File(getFilesDir(), currentFile);

                    input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String line;
                    OutputStream outputStream = driveContents.getOutputStream();
                    Writer writer = new OutputStreamWriter(outputStream);

                    // Add brewing measures
                    writer.write("### MESURES ###\n");
                    writer.write(String.format("Mash Volume: %s\n", mashVolume.getText().toString()));
                    writer.write(String.format("Pre Boil Gravity: %s\n", preBoilGravity.getText().toString()));
                    writer.write(String.format("Pre Boil Volume: %s\n", preBoilVolume.getText().toString()));
                    writer.write(String.format("Original Gravity: %s\n", originalGravity.getText().toString()));
                    writer.write(String.format("Wort Collected: %s\n", wortCollected.getText().toString()));
                    writer.write(String.format("Final Gravity: %s\n", finalGravity.getText().toString()));
                    writer.write(String.format("Final Volume: %s\n", finalVolume.getText().toString()));
                    writer.write("\n");

                    // Read line by line the file. Add a new line after each line read,
                    // otherwise they aren't added.
                    writer.write("### COMMENTAIRES ###\n");
                    while ((line = input.readLine()) != null) {
                        writer.write(line);
                        writer.write("\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                        .setTitle(saveFileName)
                        .setMimeType("text/plain")
                        .setStarred(true).build();

                // create a file in root folder
                Drive.DriveApi.getRootFolder(mGoogleApiClient)
                        .createFile(mGoogleApiClient, changeSet, driveContents)
                        .setResultCallback(fileCallback);
            }
        }.start();
    }

    /**
     * Handle result of Created file
     */
    final private ResultCallback<DriveFolder.DriveFileResult> fileCallback = new
            ResultCallback<DriveFolder.DriveFileResult>() {
                @Override
                public void onResult(DriveFolder.DriveFileResult result) {
                    if (result.getStatus().isSuccess()) {

                        Toast.makeText(getApplicationContext(), "file created: "+""+
                                result.getDriveFile().getDriveId(), Toast.LENGTH_LONG).show();

                    }

                    return;

                }
            };

    /**
     *  Handle Response of selected file
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(final int requestCode,
                                    final int resultCode, final Intent data) {
        switch (requestCode) {

            case REQUEST_CODE_OPENER:

                if (resultCode == RESULT_OK) {

                    mFileId = (DriveId) data.getParcelableExtra(
                            OpenFileActivityBuilder.EXTRA_RESPONSE_DRIVE_ID);

                    Log.e("file id", mFileId.getResourceId() + "");

                    String url = "https://drive.google.com/open?id="+ mFileId.getResourceId();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }

                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

}

