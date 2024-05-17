package com.olympiad.riddhima.class1_ieo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


/**
 * Created by Ashish on 23-08-2015.
 */
public class TestOption extends AppCompatActivity {
    ListView listView;
    String newString,test_type;
    //string subject_char;
   // private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.olympiad.riddhima.class1_ieo.R.layout.test_option);

        AdView mAdView = (AdView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        mAdView = new AdView(this);
        mAdView.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id)); //AD_UNIT_ID); //
        mAdView.setAdSize(AdSize.BANNER);
        // mAdView.setAdListener(new ToastAdListener(this));
        LinearLayout layout = (LinearLayout) findViewById(com.olympiad.riddhima.class1_ieo.R.id.Relativelayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mAdView, params);

        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // .addTestDevice("LG-F180K-bca647c")
                .build();


        mAdView.loadAd(new AdRequest.Builder().build());
        // Get ListView object from xml
        listView = (ListView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.listView);
        listView.setMinimumHeight(500);
        // Defined Array values to show in ListView
        String[] values = new String[] {"SYLLABUS                                                                ", "PRACTICE PAPER                                                                ","MOCK TEST                                                                ",};
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("sframe_id");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("sframe_id");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values) {
            // Assign adapter to ListView+-*+
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);
                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);

              //  tv.setWidth(1);
                // Generate ListView Item using TextView
                return view;
            }
        };
        listView.setAdapter(adapter);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;
                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
               // Toast.makeText(getApplicationContext(), "This is test type - " + test_type, Toast.LENGTH_LONG).show();

                switch (itemPosition) {
                    case 0:
                        test_type = "syllabus";
                        break;
                    case 1:
                        test_type = "practice";
                        break;
                    case 2:
                        test_type = "mock";
                        break;
                }

                if(newString.compareTo("science")==0) {
                            Intent ise = new Intent(TestOption.this, Chaptersofscience1.class);
                            ise.putExtra("test_type", test_type);
                            ise.putExtra("sframe_id", "0");
                            startActivity(ise);
                        }
                if(newString.compareTo("maths")==0) {
                            Intent ise = new Intent(TestOption.this, Chaptersofmat.class);
                            ise.putExtra("test_type", test_type);
                             ise.putExtra("sframe_id", "0");

                    startActivity(ise);
                        }
                if(newString.compareTo("english")==0) {
                            Intent ise = new Intent(TestOption.this, ChaptersofEnglish.class);
                            ise.putExtra("test_type", test_type);
                    ise.putExtra("sframe_id", "0");

                    startActivity(ise);
                        }
                if(newString.compareTo("cyber")==0) {
                            Intent ise = new Intent(TestOption.this, ChaptersofCyber.class);
                            ise.putExtra("test_type", test_type);
                    ise.putExtra("sframe_id", "0");

                    startActivity(ise);
                        }



            }
        });
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
