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
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Ashish on 12-09-2015.
 */
public class ChaptersofCyber extends AppCompatActivity {
    ListView listView;
    String[] values;
    String ssubj;
    String section,subject,test_type,sframe;
    Intent ise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_topics);
        // Get ListView object from xml
        listView = (ListView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.listView);
// Defined Array values to show in ListView
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                test_type= null;
            } else {
                test_type= extras.getString("test_type");
            }
        } else {
            test_type= (String) savedInstanceState.getSerializable("test_type");
        }
        //Toast.makeText(getApplicationContext(), "This is test_type on topics page - " + test_type, Toast.LENGTH_LONG).show();

        String[] topics;
        if (test_type.equals("practice")) {
            topics = new String[]{
                    "INTERNET                                                 ",
                    "NETWORKS                                                 ",
                    "POWERPOINT                                                ",
                    "MULTIMEDIA                                               ",

                    "COMPUTER - GENERAL INFORMATION                            ", // TEST1.HTML
                    "PARTS AND USAGE OF COMPUTER                              ", //TEST3.HTML
                    "MS WORD                                                " ,// TEST4.HTML
                    "LATEST DEVELOPMENTS IN IT                               " //TEST5.HTML
            };
        }
        else if(test_type.equals("mock")) {
            topics = new String[]{"Test 1                                                ",
                    "Test 2                                                ",
                    "Test 3                                                ",
                    "Test 4                                                ",
                    "Test 5                                                ",
            };
        }
        else
        {
            topics = new String[]{"Cyber Syllabus"};

        }
        char section_char= '0';//;sframe.charAt(1);


        if(section_char=='0') {
            values=topics;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values){
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);

                // Generate ListView Item using TextView
                return view;
            }
        };

        // Assign adapter to ListView
        listView.setAdapter(adapter);


        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), "This is English - " + test_type, Toast.LENGTH_LONG).show();
                switch (itemPosition) {
                    case 0:
                        ise = new Intent(ChaptersofCyber.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_6");
                        ise.putExtra("sub_id", "cyber");
                        startActivity(ise);
                        break;
                    case 1:
                        ise = new Intent(ChaptersofCyber.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_3");
                        ise.putExtra("sub_id", "cyber");
                        startActivity(ise);
                        break;
                    case 2:
                        ise = new Intent(ChaptersofCyber.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_4");
                        ise.putExtra("sub_id", "cyber");
                        startActivity(ise);
                        break;

                    case 3:
                        ise = new Intent(ChaptersofCyber.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_7");
                        ise.putExtra("sub_id", "cyber");
                        startActivity(ise);
                        break;
                    case 4:
                        ise = new Intent(ChaptersofCyber.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_1");
                        ise.putExtra("sub_id", "cyber");
                        startActivity(ise);
                        break;
                    case 5:
                        ise = new Intent(ChaptersofCyber.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_2");
                        ise.putExtra("sub_id", "cyber");
                        startActivity(ise);
                        break;
                    case 6:
                        ise = new Intent(ChaptersofCyber.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_8");
                        ise.putExtra("sub_id", "cyber");
                        startActivity(ise);
                        break;
                    case 7:
                        ise = new Intent(ChaptersofCyber.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_5");
                        ise.putExtra("sub_id", "cyber");
                        startActivity(ise);
                        break;
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
