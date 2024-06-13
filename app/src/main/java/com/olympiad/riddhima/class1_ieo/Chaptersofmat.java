package com.olympiad.riddhima.class1_ieo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;


/**
 * Created by Ashish on 12-09-2015.
 */
public class Chaptersofmat extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    ListView listView;
    String[] values;
    String test_type;
    Intent ise;
    BillingProcessor billingProcessor;
    Boolean isPremiumCustomer = false;
    TextView premiumTV;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_topics);

        billingProcessor = new BillingProcessor(this, getResources().getString(R.string.pub_key), this);
        billingProcessor.initialize();
        premiumTV = findViewById(R.id.premiumTV);

        // Get ListView object from xml

        listView = (ListView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.listView);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                test_type = null;
            } else {
                test_type = extras.getString("test_type");
            }
        } else {
            test_type = (String) savedInstanceState.getSerializable("test_type");
        }

        String[] topics;
        if (test_type.equals("practice")) {
            topics = new String[]{
                    "ADDITION                             ",
                    "SUBTRACTION                           ",
                    "SHAPES                                ",
                    "BEFORE/AFTER/BETWEEN                  ",
                    "INCREASING / DECREASING ORDER         ",
                    "PATTERNS                             ",
                    "WEIGHT, CAPACITY, TIME, TEMPERATURE "

            };
        } else if (test_type.equals("mock")) {
            //premiumTV.setVisibility(View.VISIBLE);
            topics = new String[]{"Test 1                                                ",
                    "Test 2                                                ",
                    "Test 3                                                ",
                    "Test 4                                                ",
                    "Test 5                                                ",
            };
        } else {
            topics = new String[]{"Maths Syllabus"};
        }
        values = topics;

        //settingAdapter();


    }

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
        Toast.makeText(Chaptersofmat.this, "Successfully Purchased", Toast.LENGTH_SHORT).show();
        premiumTV.setVisibility(View.INVISIBLE);
        Log.e("tag", "setting adapter on billing done:" + isPremiumCustomer.toString());
        settingAdapter();
    }

    @Override
    public void onPurchaseHistoryRestored() {
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        isPremiumCustomer = false;
        settingAdapter();
        Log.e("tag", "setting adapter on billing error:" + isPremiumCustomer.toString());
        Toast.makeText(Chaptersofmat.this, "Purchase Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {
        TransactionDetails transactionDetails = billingProcessor.getPurchaseTransactionDetails(getResources().getString(R.string.product_id));
        if (transactionDetails != null && transactionDetails.purchaseInfo.purchaseData.purchaseToken != null && !transactionDetails.purchaseInfo.purchaseData.purchaseToken.matches("")) {
            Log.e("purchaseToken", transactionDetails.purchaseInfo.purchaseData.orderId);
            isPremiumCustomer = true;
            premiumTV.setVisibility(View.INVISIBLE);
        } else {
            isPremiumCustomer = false;
            if (test_type.equals("mock"))
                premiumTV.setVisibility(View.VISIBLE);
        }
        Log.e("tag", "setting adapter on initialization:" + isPremiumCustomer.toString());
        settingAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!billingProcessor.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void displayPurchaseMessage() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Chaptersofmat.this);
        builder.setTitle("Purchase It!");
        builder.setMessage("This feature is for premium version only.Please upgrade yourself to a premium customer.");
        builder.setPositiveButton("Upgrade", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TransactionDetails transactionDetails = billingProcessor.getPurchaseTransactionDetails(getResources().getString(R.string.product_id));
                if (transactionDetails != null && transactionDetails.purchaseInfo.purchaseData.purchaseToken != null && !transactionDetails.purchaseInfo.purchaseData.purchaseToken.matches("")) {
                    isPremiumCustomer = true;
                    Toast.makeText(Chaptersofmat.this, "You have purchased the package already", Toast.LENGTH_SHORT).show();
                } else {
                    billingProcessor.purchase(Chaptersofmat.this, getResources().getString(R.string.product_id));
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void settingAdapter() {
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_with_lock, R.id.item_text, values) {
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(Chaptersofmat.this).inflate(R.layout.list_item_with_lock, parent, false);
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(R.id.item_text);
                ImageView iv = (ImageView) view.findViewById(R.id.lock_icon);
                Log.e("tag", isPremiumCustomer.toString());
                if (!isPremiumCustomer && test_type.equals("mock") && !values[position].matches("Test 1                                                ")) {
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
                        ise = new Intent(Chaptersofmat.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_1");
                        ise.putExtra("sub_id", "maths");
                        startActivity(ise);
                        break;
                    case 1:
                        if (!isPremiumCustomer && test_type.matches("mock")) {
                            displayPurchaseMessage();
                            return;
                        }
                        ise = new Intent(Chaptersofmat.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_2");
                        ise.putExtra("sub_id", "maths");
                        startActivity(ise);
                        break;
                    case 2:
                        if (!isPremiumCustomer && test_type.matches("mock")) {
                            displayPurchaseMessage();
                            return;
                        }
                        ise = new Intent(Chaptersofmat.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_3");
                        ise.putExtra("sub_id", "maths");
                        startActivity(ise);
                        break;
                    case 3:
                        if (!isPremiumCustomer && test_type.matches("mock")) {
                            displayPurchaseMessage();
                            return;
                        }
                        ise = new Intent(Chaptersofmat.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_4");
                        ise.putExtra("sub_id", "maths");
                        startActivity(ise);
                        break;
                    case 4:
                        if (!isPremiumCustomer && test_type.matches("mock")) {
                            displayPurchaseMessage();
                            return;
                        }
                        ise = new Intent(Chaptersofmat.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_5");
                        ise.putExtra("sub_id", "maths");
                        startActivity(ise);
                        break;
                    case 5:
                        if (!isPremiumCustomer && test_type.matches("mock")) {
                            displayPurchaseMessage();
                            return;
                        }
                        ise = new Intent(Chaptersofmat.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_6");
                        ise.putExtra("sub_id", "maths");
                        startActivity(ise);
                        break;
                    case 6:
                        if (!isPremiumCustomer && test_type.matches("mock")) {
                            displayPurchaseMessage();
                            return;
                        }
                        ise = new Intent(Chaptersofmat.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_7");
                        ise.putExtra("sub_id", "maths");
                        startActivity(ise);
                        break;


                }
            }
        });
    }


}