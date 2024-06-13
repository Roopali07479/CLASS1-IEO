package com.olympiad.riddhima.class1_ieo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bpractice, bsyllabus, bmock, bcyber;
    String subject;
    String section;
    String ssubj = "";
    String sfrme;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.olympiad.riddhima.class1_ieo.R.layout.activity_main);
        bpractice = (Button) findViewById(R.id.bpractice);
        bsyllabus = (Button) findViewById(com.olympiad.riddhima.class1_ieo.R.id.bsyllabus);
        bmock = (Button) findViewById(com.olympiad.riddhima.class1_ieo.R.id.bmock);

        bpractice.setOnClickListener(this);//new View.OnClickListener());
        bsyllabus.setOnClickListener(this);//new View.OnClickListener());
        bmock.setOnClickListener(this);//new View.OnClickListener());

        AdView mAdView = (AdView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //        mAdView.loadAd(adRequest);

        mAdView = new AdView(this);
        mAdView.setAdUnitId(getResources().getString(com.olympiad.riddhima.class1_ieo.R.string.banner_ad_unit_id)); //AD_UNIT_ID); //
        mAdView.setAdSize(AdSize.BANNER);
        // mAdView.setAdListener(new ToastAdListener(this));
        LinearLayout layout = (LinearLayout) findViewById(com.olympiad.riddhima.class1_ieo.R.id.Relativelayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mAdView, params);

        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // .addTestDev ice("LG-F180K-bca647c")
                .build();


        mAdView.loadAd(new AdRequest.Builder().build());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.olympiad.riddhima.class1_ieo.R.id.bmock:
                Intent ise = new Intent(this, ChaptersofEnglish.class);
                ise.putExtra("test_type", "mock");
                ise.putExtra("ptm_response", "NONE");
                startActivity(ise);
                break;
            case com.olympiad.riddhima.class1_ieo.R.id.bsyllabus:
                ise = new Intent(this, ChaptersofEnglish.class);
                ise.putExtra("test_type", "syllabus");
                ise.putExtra("ptm_response", "NONE");
                startActivity(ise);
                break;
            case R.id.bpractice:
                ise = new Intent(this, ChapterofEnglish_pract.class);
                ise.putExtra("test_type", "practice");
                ise.putExtra("ptm_response", "NONE");
                startActivity(ise);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.olympiad.riddhima.class1_ieo.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.olympiad.riddhima.class1_ieo.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
