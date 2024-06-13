package com.olympiad.riddhima.class1_ieo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Ashish on 23-08-2015.
 */
public class Gradeoption extends AppCompatActivity {
    ListView listView;
    String section;
    String ssubj;
   // private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.olympiad.riddhima.class1_ieo.R.layout.test_option);


       // AdView mAdView = (AdView) findViewById(R.id.adView);
       // AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);


        // Get ListView object from xml

        listView = (ListView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.listView);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Class 4",
                "Class 5",
                "Class 6",
                "Class 7",
                "Class 8",
                "Class 9",
                "Class 10",

        };
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ssubj= null;
            } else {
                ssubj= extras.getString("subject_id");
            }

        } else {
            ssubj= (String) savedInstanceState.getSerializable("subject_id");
        }

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
     // Assign adapter to ListView
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


                switch(itemPosition)
                {
                    case 0:
                        Intent ise=new Intent(Gradeoption.this,TestOption.class);
                         section="0";
                        String sfrme4=ssubj+section;
                        ise.putExtra("sframe_id",sfrme4);
                        startActivity(ise);

                        break;
                    case 1:
                        Intent ise_5=new Intent(Gradeoption.this,TestOption.class);
                         section="1";
                        String sfrme5=ssubj+section;
                        ise_5.putExtra("sframe_id", sfrme5);
                        startActivity(ise_5);
                        break;
                    case 2:

                        Intent ise_6=new Intent(Gradeoption.this,TestOption.class);
                         section="2";
                        String sfrme6=ssubj+section;
                        Toast.makeText(getApplicationContext(),
                                "ssubj :"+ssubj+"  sfrme6 : " +sfrme6 , Toast.LENGTH_LONG)
                                .show();
                        ise_6.putExtra("sframe_id", sfrme6);
                        startActivity(ise_6);
                        break;
                    case 3:
                        Intent ise_7=new Intent(Gradeoption.this,TestOption.class);
                         section="3";
                        String sfrme7=ssubj+section;
                        ise_7.putExtra("sframe_id", sfrme7);
                        startActivity(ise_7);
                        break;
                    case 4:
                        Intent ise_8=new Intent(Gradeoption.this,TestOption.class);
                         section="4";
                        String sfrme8=ssubj+section;
                        ise_8.putExtra("sframe_id", sfrme8);
                        startActivity(ise_8);
                        break;
                    case 5:
                        Intent ise_9=new Intent(Gradeoption.this,TestOption.class);
                         section="5";
                        String sfrme9=ssubj+section;
                        ise_9.putExtra("sframe_id", sfrme9);
                        startActivity(ise_9);
                        break;
                }
                // Show Alert
                /*
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
                        */

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
