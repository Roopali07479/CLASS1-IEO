package com.olympiad.riddhima.class1_ieo;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import okhttp3.internal.Util;

import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * Created by Ashish on 12-09-2015.
 */
public class ChaptersofEnglish extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    ListView listView;
    String[] values;
    String test_type,class_subject,amount,application_id;
    Intent ise;
    BillingProcessor billingProcessor;
    Boolean isPremiumCustomer = false;
    TextView premiumTV;
    ArrayAdapter<String> adapter;
    Button loginbutton;

    URL url = null;
    public static final int CONNECTION_TIMEOUT=10000;

    public static final int READ_TIMEOUT=15000;

    String User_EmailId = null;


    private static final int PERMISSION_REQUEST_CODE = 1111;


    @SuppressLint("MissingPermission")
    ListView listview;
    ArrayList<String> names ;
    String[] topics;


    String ptm_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_topics);
        billingProcessor = new BillingProcessor(this, getResources().getString(R.string.pub_key), this);
        billingProcessor.initialize();
        premiumTV = findViewById(R.id.premiumTV);
        names = new ArrayList<String>();
        getpermissions();

        // COPY Start
        isPremiumCustomer = getSharedPreferences("UserPreferences",Context.MODE_PRIVATE).getBoolean("isPremiumCustomer",Boolean.FALSE);
        // COPY End

        try {
            User_EmailId = getEmailID(getApplicationContext());
            System.out.println("Accounts==" + User_EmailId);


        } catch (Exception e) {
            e.printStackTrace();
        }
        //   User_EmailId="abhawish@gmail.com";


        //End here
        class_subject = getResources().getString(R.string.app_id);
        amount = getResources().getString(R.string.amount);
        loginbutton = (Button) findViewById(R.id.ptm);
        loginbutton.setOnClickListener(view -> {
            openYoutubeChannelLink();
        });

        if (ContextCompat.checkSelfPermission(ChaptersofEnglish.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ChaptersofEnglish.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }

        // Get ListView object from xml

        listView = (ListView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.listView);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                test_type = null;
                ptm_response=null;
            } else {
                test_type = extras.getString("test_type");
                ptm_response = extras.getString("ptm_response");
            }
        } else {
            test_type = (String) savedInstanceState.getSerializable("test_type");
            ptm_response = (String) savedInstanceState.getSerializable("ptm_response");

        }

        // COPY Start
        if(test_type.equals("mock") && !isPremiumCustomer)
        {
            //Toast.makeText(Chaptersofmat.this, "test_type - mock" +test_type.toString(), Toast.LENGTH_LONG).show();

            loginbutton.setVisibility(View.VISIBLE);

        }else if(isPremiumCustomer || test_type.equals("practice") || (test_type.equals("syllabus"))) {
            //Toast.makeText(Chaptersofmat.this, "test_type not mock" +test_type.toString(), Toast.LENGTH_LONG).show();

            loginbutton.setVisibility(View.INVISIBLE);
        }
        // COPY End


        if(ptm_response.equals("SUCCESS")) {
            application_id="paytm";
            // Toast.makeText(Chaptersofmat.this, "User_EmailId -  insert - after paytm payment" +User_EmailId, Toast.LENGTH_LONG).show();
            insert(application_id,class_subject,amount);
            isPremiumCustomer=true;
        }

        if (test_type.equals("practice")) {
            topics = new String[]{
                    "ANIMALS - SOUNDS AND THEIR BABIES           ",
                    "NOUN / PRONOUNS                             ",
                    "VERBS AND ADVERBS                 ",
                    "ADJECTIVES",
                    "PREPOSITIONS AND CONJUNCTIONS               ",
                    "SIMPLE TENSES                                      ",
                    "FEMININE AND MASCULINE",
                    "ODD ONE OUT", //TEST_8
                    "ONE AND MANY",
                    "ARTICLES",


            };

        } else if (test_type.equals("mock")) {
            if (checkPermission()) {
                select_rec(User_EmailId,class_subject);
                topics = new String[]{"Test 1                                                ",
                        "Test 2                                                ",
                        "Test 3                                                ",
                        "Test 4                                                ",
                        "Test 5                                                ",
                };

            }

            else{
                getpermissions();
                // topics = new String[]{"None"};

            }}

        else {
            topics = new String[]{"English Syllabus"};

        }
        values = topics;

        //settingAdapter();
    }

    // COPY Start
    private void openYoutubeChannelLink() {
        Intent intent = new Intent(ChaptersofEnglish.this, WebViewActivity.class);
        startActivity(intent);
        finish();
    }
    // COPY End

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.olympiad.riddhima.class1_ieo.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == com.olympiad.riddhima.class1_ieo.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        isPremiumCustomer = true;
        application_id="googlepay";
        //Toast.makeText(Chaptersofmat.this, "User_EmailId - onProductPurchased -  " +User_EmailId, Toast.LENGTH_LONG).show();

        insert(application_id,class_subject,amount);
        Toast.makeText(ChaptersofEnglish.this,"Successfully Purchased",Toast.LENGTH_SHORT).show();
        premiumTV.setVisibility(View.INVISIBLE);
        Log.e("tag","setting adapter on billing done:"+isPremiumCustomer.toString());
        settingAdapter();
    }

    @Override
    public void onPurchaseHistoryRestored() {
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        isPremiumCustomer = false;
        settingAdapter();
        Log.e("tag","setting adapter on billing error:"+isPremiumCustomer.toString());
      //  Toast.makeText(ChaptersofEnglish.this,"Purchase Failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {
        TransactionDetails transactionDetails = billingProcessor.getPurchaseTransactionDetails(getResources().getString(R.string.product_id));
        if(transactionDetails!=null && transactionDetails.purchaseInfo.purchaseData.purchaseToken != null && !transactionDetails.purchaseInfo.purchaseData.purchaseToken.matches("")){
            Log.e("purchaseToken",transactionDetails.purchaseInfo.purchaseData.orderId);
            // isPremiumCustomer = true;
            premiumTV.setVisibility(View.INVISIBLE);

        }
        else
        {
            // isPremiumCustomer = false;
            if(test_type.equals("mock"))
                premiumTV.setVisibility(View.VISIBLE);
        }
        Log.e("tag","setting adapter on initialization:"+isPremiumCustomer.toString());
        settingAdapter();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!billingProcessor.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void displayPurchaseMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChaptersofEnglish.this);
        builder.setTitle("Purchase It!");
        builder.setMessage("This feature is for premium version only.Please subscribe our youtube channel to become a premium customer.");
        builder.setPositiveButton("Upgrade", (dialog, which) -> {
            // COPY Start
            openYoutubeChannelLink();
//            TransactionDetails transactionDetails = billingProcessor.getPurchaseTransactionDetails(getResources().getString(R.string.product_id));
//            if(transactionDetails!=null && transactionDetails.purchaseInfo.purchaseData.purchaseToken != null && !transactionDetails.purchaseInfo.purchaseData.purchaseToken.matches("")){
//                isPremiumCustomer=true;
//                Toast.makeText(ChaptersofEnglish.this,"You have purchased the package already",Toast.LENGTH_SHORT).show();
//                application_id="googlepay";
//                insert(application_id,class_subject,amount);
//            }
//            else {
//                billingProcessor.purchase(ChaptersofEnglish.this, getResources().getString(R.string.product_id));
//                application_id="googlepay";
//            }
            // COPY End
            dialog.dismiss();
        });
        builder.setNegativeButton("Not Now", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void settingAdapter() {
        select_rec(User_EmailId,class_subject);
        try {
            if(values.length == 0){
                return;
            }
            adapter = new ArrayAdapter<String>(this, R.layout.list_item_with_lock, R.id.item_text, values) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    convertView = LayoutInflater.from(ChaptersofEnglish.this).inflate(R.layout.list_item_with_lock, parent, false);
                    View view = super.getView(position, convertView, parent);
                    TextView tv = (TextView) view.findViewById(R.id.item_text);
                    ImageView iv = (ImageView) view.findViewById(R.id.lock_icon);
                    Log.e("tag", isPremiumCustomer.toString());
                    if (!isPremiumCustomer && test_type.equals("mock")) {
                        iv.setVisibility(View.VISIBLE);
                    } else
                        iv.setVisibility(View.INVISIBLE);
                    return view;
                }
            };
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            //billingProcessor.consumePurchase(getResources().getString(R.string.product_id));
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_1");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 1:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_2");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 2:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_3");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 3:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_4");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 4:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_5");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 5:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_6");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 6:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_7");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 7:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_8");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 8:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_9");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                        case 9:
                            if (!isPremiumCustomer && test_type.matches("mock")) {
                                displayPurchaseMessage();
                                return;
                            }
                            ise = new Intent(ChaptersofEnglish.this, Questions.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("test_id", "test_10");
                            ise.putExtra("sub_id", "english");
                            startActivity(ise);
                            break;
                    }
                }

            });
        }catch (Exception e){
            Log.e("Exception",e.getMessage());
        }
    }

    public void select_rec(final String androidId,final String class_subject){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            HttpURLConnection conn;
            @Override
            protected String doInBackground(String... params) {
                try {
                    url = new URL( "https://www.rar-infosystems.com/paytm/select.php");

                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "exception";
                }
                try {
                    // Setup HttpURLConnection class to send and receive data from php and mysql
                    conn = (HttpURLConnection)url.openConnection();
                    conn.setReadTimeout(READ_TIMEOUT);
                    conn.setConnectTimeout(CONNECTION_TIMEOUT);
                    conn.setRequestMethod("POST");

                    // setDoInput and setDoOutput method depict handling of both send and receive
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    // Append parameters to URL
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("android_id", androidId)
                            .appendQueryParameter("class_subject", class_subject);
                    String query = builder.build().getEncodedQuery();

                    // Open connection for sending data
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    //  Toast.makeText(Chaptersofmat.this, "Query = "+e1.toString(), Toast.LENGTH_LONG).show();

                    return "exception";
                }try {

                    int response_code = conn.getResponseCode();

                    // Check if successful connection made
                    if (response_code == HttpURLConnection.HTTP_OK) {

                        // Read data sent from server
                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        return(result.toString());

                    }else{

                        return("unsuccessful");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    return "exception";
                } finally {
                    conn.disconnect();
                }


            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equalsIgnoreCase("true")) {
                    isPremiumCustomer=true;
                    if(test_type.equals("mock")){
                        loginbutton.setVisibility(View.INVISIBLE);
                    }
                    //Toast.makeText(Chaptersofmat.this, "Data true ---" +isPremiumCustomer, Toast.LENGTH_LONG).show();
                }
                else
                {
                    // COPY Start
//                    isPremiumCustomer=false;
                    // COPY End
                    // loginbutton.setVisibility(View.VISIBLE);
                    // Toast.makeText(Chaptersofmat.this, "Data false --- " +isPremiumCustomer, Toast.LENGTH_LONG).show();
                }
                Log.e("tag","setting adapter on select_rec:"+isPremiumCustomer.toString());

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(androidId);
    }

    public void insert( final String application_id,final String class_subject,final String amount){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            HttpURLConnection conn;
            @Override
            protected String doInBackground(String... params) {
                try {
                    // Enter URL address where your php file resides
                    //    url = new URL("http://192.168.0.101/olympiad/insert.php" );
                    url = new URL( "https://www.rar-infosystems.com/paytm/insert.php");


//192.185.129.60
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "exception";
                }
                try {
                    // Setup HttpURLConnection class to send and receive data from php and mysql
                    conn = (HttpURLConnection)url.openConnection();
                    conn.setReadTimeout(READ_TIMEOUT);
                    conn.setConnectTimeout(CONNECTION_TIMEOUT);
                    conn.setRequestMethod("POST");

                    // setDoInput and setDoOutput method depict handling of both send and receive
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    // final String email = UserEmail.getEmail();
                    // Append parameters to URL
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("android_id", User_EmailId)
                            .appendQueryParameter("application_id", application_id)
                            .appendQueryParameter("class_subject", class_subject)
                            .appendQueryParameter("amount", amount);


                    String query = builder.build().getEncodedQuery();

                    // Open connection for sending data
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    //  Toast.makeText(Chaptersofmat.this, "Query = "+e1.toString(), Toast.LENGTH_LONG).show();

                    return "exception";
                }try {

                    int response_code = conn.getResponseCode();

                    // Check if successful connection made
                    if (response_code == HttpURLConnection.HTTP_OK) {

                        // Read data sent from server
                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        // Pass data to onPostExecute method
                        return(result.toString());

                    }else{

                        return("unsuccessful");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    return "exception";
                } finally {
                    conn.disconnect();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                //  Toast.makeText(Chaptersofmat.this, "User_EmailId - Select - after insert " +User_EmailId, Toast.LENGTH_LONG).show();

                select_rec(User_EmailId,class_subject);
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(User_EmailId,application_id,class_subject,amount);
    }





    private String getEmailID(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);
        if (account == null) {
            return null;
        } else {
            return account.name;
        }

    }

    public static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }


    //Requesting device permissions and Handling
    private void getpermissions() {
        if (!checkPermission()) {
            requestPermission();
        } else {
        }
    }

    //To check mobile device permissions
    private boolean checkPermission() {
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), GET_ACCOUNTS);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CONTACTS);

        return result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED ;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{GET_ACCOUNTS,READ_CONTACTS}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean account = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (account ){
                        Toast.makeText(this, "Permission Granted, Now you can access all the features of app.", Toast.LENGTH_SHORT).show();
                        // email.setText(getEmailID(getApplicationContext()));
                        User_EmailId =getEmailID(getApplicationContext());
                        select_rec(User_EmailId,class_subject);
                    }else {
                        User_EmailId="---";
                        Toast.makeText(this, "Permission Denied,Please restart the app", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
                                showMessageOKCancel("\"Please provide permission to get your email id, else you will not be able to access mock tests.",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{GET_ACCOUNTS,READ_CONTACTS},
                                                    PERMISSION_REQUEST_CODE);
                                        }
                                    }});return; } } } }
                break; } }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(ChaptersofEnglish.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNeutralButton("Cancel", null)
                .setCancelable(false)
                // .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}






