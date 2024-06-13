package com.olympiad.riddhima.class1_ieo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.lang.String;

/**
 * Created by Ashish on 24-08-2015.
 */
public class Questions extends AppCompatActivity
        //implements  View.OnClickListener{
{

    Button bnext,bprev;
    RadioButton Rdques1,Rdques2,Rdques3,Rdques4;
    TextView tquestion;
    String sciencestring;
    String section,subject,sframe,item_name,test_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.question_paper);
           setContentView(com.olympiad.riddhima.class1_ieo.R.layout.quest_web);

        WebView myWebView = (WebView) findViewById(com.olympiad.riddhima.class1_ieo.R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setAllowContentAccess(true);
       // myWebView.loadUrl("file:///android_asset/science_ques_4.html");
       // myWebView.loadUrl("file:///android_asset/Bhimashankar.html");

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                item_name= null;
            } else {
                test_type= extras.getString("test_type");
                item_name=extras.getString("test_id");
                subject=extras.getString("sub_id");
            }
        } else {
            test_type= (String) savedInstanceState.getSerializable("test_type");
            item_name= (String) savedInstanceState.getSerializable("test_id");
            subject= (String) savedInstanceState.getSerializable("sub_id");

        }
       sciencestring = "file:///android_asset/english" + "/" + test_type + "/" + item_name + ".html";
       /* Toast.makeText(getApplicationContext(),
                "sciencestring :"+sciencestring , Toast.LENGTH_LONG)
                .show();*/
       if (test_type.equals("syllabus")) {
            switch (subject) {
                case "maths":
                    myWebView.loadUrl("file:///android_asset/SYLLABUS_maths.html");
                    break;
                case "science":
                    myWebView.loadUrl("file:///android_asset/SYLLABUS_science.html");
                    break;
                case "english":
                    myWebView.loadUrl("file:///android_asset/SYLLABUS_english.html");
                    break;
                case "cyber":
                    myWebView.loadUrl("file:///android_asset/SYLLABUS_cyber.html");
                    break;
            }
        }
        else {
            myWebView.loadUrl(sciencestring);
       }
       // Indexclass myclass=new Indexclass();
        //String rtrnstring= myclass.getStrin(subject_char,section_char,chapt_char);
        //myWebView.loadUrl("file:///android_asset/science_ques_4.html");


        //myWebView.loadUrl("javascript:alert(checkAnswer)");
    }

    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("LogTag", message);
            result.confirm();
            return true;
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