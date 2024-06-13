package com.olympiad.riddhima.class1_ieo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.olympiad.riddhima.class1_ieo.ChaptersofEnglish;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class checksum extends AppCompatActivity implements PaytmPaymentTransactionCallback {

    String custid="", orderId="", mid="";
    String TempName, TempEmail ;
    String androidId;
    String class_subject,amount;
    InputStream is=null;
    String result=null;
    String line=null;
    int code;
    URL url = null;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //initOrderId();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        orderId = intent.getExtras().getString("orderid");
        custid = intent.getExtras().getString("custid");

        mid = "CxVDfM83747270141427"; /// your marchant id - production
        //   mid ="mZblPi96416939843005";
        sendUserDetailTOServerdd dl = new sendUserDetailTOServerdd();
        dl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
// vollye , retrofit, asynch

    }

    public class sendUserDetailTOServerdd extends AsyncTask<ArrayList<String>, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(checksum.this);

        //private String orderId , mid, custid, amt;
        String url ="https://www.rar-infosystems.com/paytm/generateChecksum.php";
        //String varifyurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
        String varifyurl  =  "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID="+orderId; //production
        //String varifyurl  =  "https://securegw.paytm.in/order/status";
        //  String varifyurl  =  "https://pguat.paytm.com/oltp/HANDLER_INTERNAL/getTxnStatus";
        String CHECKSUMHASH ="";

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        protected String doInBackground(ArrayList<String>... alldata) {
            JSONParser jsonParser = new JSONParser(checksum.this);
            String param=
                    "MID="+mid+
                            "&ORDER_ID=" + orderId+
                            "&CUST_ID="+custid+
                            "&CHANNEL_ID=WAP&TXN_AMOUNT="+ getResources().getString(R.string.amount) +"&WEBSITE=DEFAULT"+
                            "&CALLBACK_URL="+ varifyurl+"&INDUSTRY_TYPE_ID=Retail";

            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",param);
            Log.e("CheckSum result >>",jsonObject.toString());
            if(jsonObject != null){
                Log.e("CheckSum result >>",jsonObject.toString());
                try {

                    CHECKSUMHASH=jsonObject.has("CHECKSUMHASH")?jsonObject.getString("CHECKSUMHASH"):"";
                    Log.e("CheckSum result >>",CHECKSUMHASH);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return CHECKSUMHASH;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ","  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            //  PaytmPGService Service = PaytmPGService.getStagingService();
            // when app is ready to publish use production service
            PaytmPGService  Service = PaytmPGService.getProductionService();
            // now call paytm service here
            //below parameter map is required to construct PaytmOrder object, Merchant should replace below map values with his own values
            HashMap<String, String> paramMap = new HashMap<String, String>();
            //these are mandatory parameters
            paramMap.put("MID", mid); //MID provided by paytm
            paramMap.put("ORDER_ID", orderId);
            paramMap.put("CUST_ID",custid);
            paramMap.put("CHANNEL_ID", "WAP");
            paramMap.put("TXN_AMOUNT", getResources().getString(R.string.amount));
            paramMap.put("WEBSITE", "DEFAULT");
            paramMap.put("CALLBACK_URL" ,varifyurl);
            paramMap.put("INDUSTRY_TYPE_ID", "Retail");

            //paramMap.put( "EMAIL" , "abc@gmail.com");   // no need
            // paramMap.put( "MOBILE_NO" , "9667226916");  // no need*/
            paramMap.put("CHECKSUMHASH" ,CHECKSUMHASH);
            //paramMap.put("PAYMENT_TYPE_ID" ,"CC");    // no need

            PaytmOrder Order = new PaytmOrder(paramMap);

            Log.e("checksum ", "param "+ paramMap.toString());
            Service.initialize(Order,null);
            // start payment service call here
            Service.startPaymentTransaction(checksum.this, true, true,
                    checksum.this  );

        }
    }
    @Override
    public void onTransactionResponse(Bundle bundle) {
        Log.e("checksum ", " respon true " + bundle.toString());
        //Toast.makeText(getApplicationContext(), "Payment Transaction response - onTransactionResponse" +bundle.toString() , Toast.LENGTH_LONG).show();
        String paytm_status = bundle.toString();
        if (paytm_status.contains("TXN_SUCCESS")) {
            // Toast.makeText(getApplicationContext(), "Payment Success"  , Toast.LENGTH_LONG).show();
            Intent intent = new Intent(checksum.this,  ChaptersofEnglish.class);
            intent.putExtra("test_type", "mock");
            intent.putExtra("ptm_response", "SUCCESS");
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(checksum.this,  ChaptersofEnglish.class);
            intent.putExtra("test_type", "mock");
            intent.putExtra("ptm_response", "Failure");
            startActivity(intent);
        }

    }

    @Override
    public void networkNotAvailable() {
        Intent intent = new Intent(checksum.this,  ChaptersofEnglish.class);
        intent.putExtra("test_type", "mock");
        intent.putExtra("ptm_response", "networkNotAvailable");
        startActivity(intent);
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        Log.e("checksum ", " ui fail clientAuthenticationFailed  "+ s );
        Intent intent = new Intent(checksum.this,  ChaptersofEnglish.class);
        intent.putExtra("test_type", "mock");
        intent.putExtra("ptm_response", "onErrorLoadingWebPage");
        startActivity(intent);

    }

    @Override
    public void someUIErrorOccurred(String s) {
        Log.e("checksum ", " ui fail response  "+ s );
        Intent intent = new Intent(checksum.this,  ChaptersofEnglish.class);
        intent.putExtra("test_type", "mock");
        intent.putExtra("ptm_response", "onErrorLoadingWebPage");
        startActivity(intent);
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Log.e("checksum ", " error loading pagerespon true "+ s + "  s1 " + s1);
        Intent intent = new Intent(checksum.this,  ChaptersofEnglish.class);
        intent.putExtra("test_type", "mock");
        intent.putExtra("ptm_response", "onErrorLoadingWebPage");
        startActivity(intent);
    }
    @Override
    public void onBackPressedCancelTransaction() {
        Log.e("checksum ", " cancel call back response " );
        // Toast.makeText(getApplicationContext(), "Payment Transaction response - onBackPressedCancelTransaction" , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(checksum.this,  ChaptersofEnglish.class);
        intent.putExtra("test_type", "mock");
        intent.putExtra("ptm_response", "onBackPressedCancelTransaction");
        startActivity(intent);
    }
    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Log.e("checksum ", "  transaction cancel " );
        // Toast.makeText(getApplicationContext(), "Payment Transaction response - onTransactionResponse" +bundle.toString() , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(checksum.this,  ChaptersofEnglish.class);
        intent.putExtra("test_type", "mock");
        intent.putExtra("ptm_response", "onTransactionCancel");
        startActivity(intent);
    }
}
