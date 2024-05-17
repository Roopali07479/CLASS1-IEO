package com.olympiad.riddhima.class1_ieo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;



/**
 * Created by Ashish on 12-09-2015.
 */
public class ChapterofEnglish_pract extends AppCompatActivity implements RewardedVideoAdListener {
    RewardedVideoAd mRewardedVideoAd;

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

        premiumTV = findViewById(R.id.premiumTV);
        AdView mAdView = (AdView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //  mAdView.loadAd(adRequest);
        mAdView = new AdView(this);
        mAdView.setAdUnitId(getResources().getString(com.olympiad.riddhima.class1_ieo.R.string.banner_ad_unit_id)); //AD_UNIT_ID); //
        mAdView.setAdSize(AdSize.BANNER);

        // mAdView.setAdListener(new ToastAdListener(this));
        LinearLayout layout = (LinearLayout) findViewById(com.olympiad.riddhima.class1_ieo.R.id.Relativelayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mAdView, params);
        mAdView.loadAd(new AdRequest.Builder().build());

        // MobileAds.initialize(this, "ca-app-pub-7816665801360912~8136921808");
        MobileAds.initialize(this,getResources().getString(com.olympiad.riddhima.class1_ieo.R.string.admob_app_id));

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd(getResources().getString(R.string.rewarded_ad_unit_id),
                new AdRequest.Builder().build());
        if(mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
        names = new ArrayList<String>();
        String fullName = null;

        class_subject = getResources().getString(R.string.app_id);
        amount = getResources().getString(R.string.amount);
        loginbutton = (Button) findViewById(R.id.ptm);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int rand_int1 = rand.nextInt(1000);
                SimpleDateFormat s = new SimpleDateFormat("ddMMyyhhmmss");
                String orderId = s.format(new Date());
                Intent intent = new Intent(ChapterofEnglish_pract.this, checksum.class);
                intent.putExtra("orderid", orderId);
                intent.putExtra("custid", "Test"+rand_int1);
                startActivity(intent);

            }
        });


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

        if(test_type.equals("mock"))
        {
            //Toast.makeText(Chaptersofscience.this, "test_type - mock" +test_type.toString(), Toast.LENGTH_LONG).show();

            loginbutton.setVisibility(View.VISIBLE);

        }else if(test_type.equals("practice")|| (test_type.equals("syllabus")))
        {
            //Toast.makeText(Chaptersofscience.this, "test_type not mock" +test_type.toString(), Toast.LENGTH_LONG).show();

            loginbutton.setVisibility(View.INVISIBLE);
        }




        if (test_type.equals("practice")) {
            topics = new String[]{
                    //---new
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



        }
        values = topics;

        settingAdapter();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!billingProcessor.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void settingAdapter()
    {

        adapter = new ArrayAdapter<String>(this, R.layout.list_item_with_lock, R.id.item_text, values) {
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(ChapterofEnglish_pract.this).inflate(R.layout.list_item_with_lock,parent,false);
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(R.id.item_text);
                ImageView iv = (ImageView)view.findViewById(R.id.lock_icon);
                Log.e("tag",isPremiumCustomer.toString());
                if (!isPremiumCustomer && test_type.equals("mock")) {
                    iv.setVisibility(View.VISIBLE);
                }
                else
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

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_1");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 1:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_2");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 2:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_3");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 3:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_4");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 4:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_5");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 5:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_6");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 6:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_7");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 7:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_8");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 8:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_9");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 9:

                        ise = new Intent(ChapterofEnglish_pract.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_10");
                        ise.putExtra("sub_id", "english");
                        startActivity(ise);
                        break;
                    case 10:

                        ise = new Intent(ChapterofEnglish_pract.this, com.olympiad.riddhima.class1_ieo.Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_11");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
                        break;
                    case 11:

                        ise = new Intent(ChapterofEnglish_pract.this, com.olympiad.riddhima.class1_ieo.Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_12");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
                        break;
                    case 12:

                        ise = new Intent(ChapterofEnglish_pract.this, com.olympiad.riddhima.class1_ieo.Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_13");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
                        break;
                    case 13:

                        ise = new Intent(ChapterofEnglish_pract.this, com.olympiad.riddhima.class1_ieo.Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_14");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
                        break;
                    case 14:

                        ise = new Intent(ChapterofEnglish_pract.this, com.olympiad.riddhima.class1_ieo.Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_15");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
                        break;
                    case 15:

                        ise = new Intent(ChapterofEnglish_pract.this, com.olympiad.riddhima.class1_ieo.Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_16");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
                        break;
                }
            }

        });
    }




    @Override
    public void onRewarded(RewardItem reward) {
        //Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
        //       reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //   Toast.makeText(this, "onRewardedVideoAdLeftApplication",
        //     Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        //   Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        if(mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        // Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
        if(mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }

    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //  Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        if(mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }


    }

    @Override
    public void onRewardedVideoAdOpened() {
        // Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        // Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        //Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }




}








