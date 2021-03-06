package com.example.noiseux1523.vireesulhop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Evaluation extends AppCompatActivity
        // implements GoogleApiClient.ConnectionCallbacks,
        // GoogleApiClient.OnConnectionFailedListener
        {

//    private static final String TAG = "Google Drive Activity";
//    private static final int REQUEST_CODE_RESOLUTION = 1;
//    private static final  int REQUEST_CODE_OPENER = 2;
//    private GoogleApiClient mGoogleApiClient;
//    private boolean fileOperation = false;
//    private DriveId mFileId;
//    public DriveFile file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

//        /**
//         * Create the API client and bind it to an instance variable.
//         * We use this instance as the callback for connection and connection failures.
//         * Since no account name is passed, the user is prompted to choose.
//         */
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Drive.API)
//                .addScope(Drive.SCOPE_FILE)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
    }

//    /**
//     * Called when the activity will start interacting with the user.
//     * At this point your activity is at the top of the activity stack,
//     * with user input going to it.
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mGoogleApiClient == null) {
//
//        }
//
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    protected void onStart() {
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.connect();
//        }
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        if (mGoogleApiClient != null) {
//            // disconnect Google Android Drive API connection.
//            mGoogleApiClient.disconnect();
//        }
//        super.onStop();
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//
//        // Called whenever the API client fails to connect.
//        Log.i(TAG, "GoogleApiClient connection failed: " + result.toString());
//
//        if (!result.hasResolution()) {
//
//            // show the localized error dialog.
//            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show();
//            return;
//        }
//
//        /**
//         *  The failure has a resolution. Resolve it.
//         *  Called typically when the app is not yet authorized, and an  authorization
//         *  dialog is displayed to the user.
//         */
//
//        try {
//
//            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);
//
//        } catch (IntentSender.SendIntentException e) {
//
//            Log.e(TAG, "Exception while starting resolution activity", e);
//        }
//    }
//
//    /**
//     * It invoked when Google API client connected
//     * @param connectionHint
//     */
//    @Override
//    public void onConnected(Bundle connectionHint) {
//
//        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
//    }
//
//    /**
//     * It invoked when connection suspended
//     * @param cause
//     */
//    @Override
//    public void onConnectionSuspended(int cause) {
//
//        Log.i(TAG, "GoogleApiClient connection suspended");
//    }
//
//    public void onClickCreateFile(View view){
//        fileOperation = true;
//
//        // create new contents resource
//        Drive.DriveApi.newDriveContents(mGoogleApiClient)
//                .setResultCallback(driveContentsCallback);
//
//    }
//
//    public void onClickOpenFile(View view){
//        fileOperation = false;
//
//        // create new contents resource
//        Drive.DriveApi.newDriveContents(mGoogleApiClient)
//                .setResultCallback(driveContentsCallback);
//    }
//
//    /**
//     *  Open list of folder and file of the Google Drive
//     */
//    public void OpenFileFromGoogleDrive(){
//
//        IntentSender intentSender = Drive.DriveApi
//                .newOpenFileActivityBuilder()
//                .setMimeType(new String[] { "text/plain", "text/html" })
//        .build(mGoogleApiClient);
//        try {
//            startIntentSenderForResult(
//
//                    intentSender, REQUEST_CODE_OPENER, null, 0, 0, 0);
//
//        } catch (IntentSender.SendIntentException e) {
//
//            Log.w(TAG, "Unable to send intent", e);
//        }
//
//    }
//
//    /**
//     * This is Result result handler of Drive contents.
//     * this callback method call CreateFileOnGoogleDrive() method
//     * and also call OpenFileFromGoogleDrive() method,
//     * send intent onActivityResult() method to handle result.
//     */
//    final ResultCallback<DriveApi.DriveContentsResult> driveContentsCallback =
//            new ResultCallback<DriveApi.DriveContentsResult>() {
//        @Override
//        public void onResult(DriveApi.DriveContentsResult result) {
//
//            if (result.getStatus().isSuccess()) {
//
//                if (fileOperation == true) {
//
//                    CreateFileOnGoogleDrive(result);
//
//                } else {
//
//                    OpenFileFromGoogleDrive();
//
//                }
//            }
//
//        }
//    };
//
//    /**
//     * Create a file in root folder using MetadataChangeSet object.
//     * @param result
//     */
//    public void CreateFileOnGoogleDrive(DriveApi.DriveContentsResult result){
//
//        final DriveContents driveContents = result.getDriveContents();
//
//        // Perform I/O off the UI thread.
//        new Thread() {
//            @Override
//            public void run() {
//                // write content to DriveContents
//                OutputStream outputStream = driveContents.getOutputStream();
//                Writer writer = new OutputStreamWriter(outputStream);
//                try {
//                    writer.write("Hello abhay!");
//                    writer.close();
//                } catch (IOException e) {
//                    Log.e(TAG, e.getMessage());
//                }
//
//                MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
//                        .setTitle("abhaytest2")
//                .setMimeType("text/plain")
//                .setStarred(true).build();
//
//                // create a file in root folder
//                Drive.DriveApi.getRootFolder(mGoogleApiClient)
//                        .createFile(mGoogleApiClient, changeSet, driveContents)
//                        .setResultCallback(fileCallback);
//            }
//        }.start();
//    }
//
//    /**
//     * Handle result of Created file
//     */
//    final private ResultCallback<DriveFolder.DriveFileResult> fileCallback = new
//    ResultCallback<DriveFolder.DriveFileResult>() {
//        @Override
//        public void onResult(DriveFolder.DriveFileResult result) {
//            if (result.getStatus().isSuccess()) {
//
//                Toast.makeText(getApplicationContext(), "file created: "+""+
//                        result.getDriveFile().getDriveId(), Toast.LENGTH_LONG).show();
//
//            }
//
//            return;
//
//        }
//    };
//
//    /**
//     *  Handle Response of selected file
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    protected void onActivityResult(final int requestCode,
//                                    final int resultCode, final Intent data) {
//        switch (requestCode) {
//
//            case REQUEST_CODE_OPENER:
//
//                if (resultCode == RESULT_OK) {
//
//                    mFileId = (DriveId) data.getParcelableExtra(
//                            OpenFileActivityBuilder.EXTRA_RESPONSE_DRIVE_ID);
//
//                    Log.e("file id", mFileId.getResourceId() + "");
//
//                    String url = "https://drive.google.com/open?id="+ mFileId.getResourceId();
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    startActivity(i);
//                }
//
//                break;
//
//            default:
//                super.onActivityResult(requestCode, resultCode, data);
//                break;
//        }
//    }
//
}


