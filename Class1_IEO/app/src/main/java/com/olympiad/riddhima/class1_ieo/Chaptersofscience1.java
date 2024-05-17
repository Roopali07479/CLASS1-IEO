package com.olympiad.riddhima.class1_ieo;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;
import android.content.DialogInterface;

import android.support.annotation.NonNull;

import com.android.vending.billing.IInAppBillingService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ashish on 12-09-2015.
 */
public class Chaptersofscience1 extends AppCompatActivity {
    ListView listView;
    String[] values;
    String ssubj;
    String section,subject,test_type,sframe;
    Intent ise;
	private PaymentsClient mPaymentsClient;
	private View mGooglePayButton;
	private View mtest1,mtest2,mtest3,mtest4,mtest5;

	private ItemInfo mBikeItem = new ItemInfo("Simple Bike", 300 * 1000000, R.drawable.bike);
	private long mShippingCost = 90 * 1000000;
	private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 991;
	private TextView mGooglePayStatusText;
  ServiceConnection mServiceConn;
    IInAppBillingService mService;
    PendingIntent pending;
    Intent intent;
    Bundle bundle;

  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_topics_bkp);
        bindService(new Intent("com.android.vending.billing.InAppBillingService.BIND"), mServiceConn, Context.BIND_AUTO_CREATE);

        // Get ListView object from xml


        ServiceConnection mServiceConn = new ServiceConnection()
        {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service)
            {
                mService = IInAppBillingService.Stub.asInterface(service);
                Log.d("TEST", "mService ready to go!");
                }

            @Override
            public void onServiceDisconnected(ComponentName name)
            {
                mService = null;
            }
        };







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


       


        String[] topics;
        if (test_type.equals("practice")) {
            topics = new String[]{
                    "HEAT, MOTION & TIME                             ", // TEST1.HTML
                    "ELECTRIC CURRENT AND ITS EFFECTS               " ,// TEST2.HTML
                    "WINDS, STORMS AND CYCLONES                     ", //TEST3.HTML
                    "LIGHT & ACIDS                                  ", //test4.HTML
                    "PHYSICAL & CHEMICAL CHANGES                    ", //TEST5.HTML
                   "WEATHER, CLIMATE & ADAPTATIONS                  ", //TEST6.HTML
				   "FIBRE TO FABRIC                                 ", //TEST 7
				   "NUTRITION IN PLANTS AND ANIMALS                 ", //TEST 8
				   "RESPIRATION IN ORGANISMS                        ",  //TEST 9
				   "TRANSPORTATION IN PLANTS & ANIMALS              ", //TEST 10
				   "REPRODUCTION IN PLANTS                          " //TEST 11

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
            topics = new String[]{"Science Syllabus"};

        }
        char section_char= '0';//;sframe.charAt(1);


        if(section_char=='0') {
            values=topics;
        }

		
    mGooglePayButton = findViewById(R.id.googlepay_button);
	mtest1= findViewById(R.id.mtest1);
	mtest2= findViewById(R.id.mtest2);
	mtest3= findViewById(R.id.mtest3);
	mtest4= findViewById(R.id.mtest4);
	mtest5= findViewById(R.id.mtest5);

    mGooglePayStatusText = findViewById(R.id.googlepay_status);
    // Initialize a Google Pay API client for an environment suitable for testing.
    // It's recommended to create the PaymentsClient object inside of the onCreate method.
    mPaymentsClient = PaymentsUtil.createPaymentsClient(this);
    possiblyShowGooglePayButton();

    mGooglePayButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            requestPayment(view);
          }
        });


		

        mtest1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ise = new Intent(Chaptersofscience1.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_1");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);

                    }
                });
        mtest2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickPopup(2);
                      //  ise = new Intent(Chaptersofscience1.this, Questions.class);
                       // ise.putExtra("test_type", test_type);
                        //ise.putExtra("test_id", "test_2");
                      //  ise.putExtra("sub_id", "science");
                        //startActivity(ise);

                    }
                });
        mtest3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickPopup(3);
                 /*       ise = new Intent(Chaptersofscience1.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_3");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
*/
                    }
                });
        mtest4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickPopup(4);
               /*         ise = new Intent(Chaptersofscience1.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_4");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
*/
                    }
                });
        mtest5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickPopup(5);

           /*             ise = new Intent(Chaptersofscience1.this, Questions.class);
                        ise.putExtra("test_type", test_type);
                        ise.putExtra("test_id", "test_5");
                        ise.putExtra("sub_id", "science");
                        startActivity(ise);
*/
                    }
                });



    }
	/**
   * If isReadyToPay returned {@code true}, show the button and hide the "checking" text. Otherwise,
   * notify the user that Google Pay is not available. Please adjust to fit in with your current
   * user flow. You are not required to explicitly let the user know if isReadyToPay returns {@code
   * false}.
   *
   * @param available isReadyToPay API response.
   */
  private void setGooglePayAvailable(boolean available) {
    if (available) {
      mGooglePayStatusText.setVisibility(View.GONE);
      mGooglePayButton.setVisibility(View.VISIBLE);
    } else {
      mGooglePayStatusText.setText(R.string.googlepay_status_unavailable);
    }
  }
  /**
   * PaymentData response object contains the payment information, as well as any additional
   * requested information, such as billing and shipping address.
   *
   * @param paymentData A response object returned by Google after a payer approves payment.
   * @see <a
   *     href="https://developers.google.com/pay/api/android/reference/object#PaymentData">Payment
   *     Data</a>
   */
  private void handlePaymentSuccess(PaymentData paymentData) {
    String paymentInformation = paymentData.toJson();

    // Token will be null if PaymentDataRequest was not constructed using fromJson(String).
    if (paymentInformation == null) {
      return;
    }
    JSONObject paymentMethodData;

    try {
      paymentMethodData = new JSONObject(paymentInformation).getJSONObject("paymentMethodData");
      // If the gateway is set to "example", no payment information is returned - instead, the
      // token will only consist of "examplePaymentMethodToken".
      if (paymentMethodData
              .getJSONObject("tokenizationData")
              .getString("type")
              .equals("PAYMENT_GATEWAY")
          && paymentMethodData
              .getJSONObject("tokenizationData")
              .getString("token")
              .equals("examplePaymentMethodToken")) {
        AlertDialog alertDialog =
            new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage(
                    "Gateway name set to \"example\" - please modify "
                        + "Constants.java and replace it with your own gateway.")
                .setPositiveButton("OK", null)
                .create();
        alertDialog.show();


      }

      String billingName =
          paymentMethodData.getJSONObject("info").getJSONObject("billingAddress").getString("name");
      Log.d("BillingName", billingName);
      Toast.makeText(this, getString(R.string.payments_show_name, billingName), Toast.LENGTH_LONG)
          .show();

      // Logging token string.
      Log.d("GooglePaymentToken", paymentMethodData.getJSONObject("tokenizationData").getString("token"));
    } catch (JSONException e) {
      Log.e("handlePaymentSuccess", "Error: " + e.toString());
      return;
    }
  }
    /**
   * At this stage, the user has already seen a popup informing them an error occurred. Normally,
   * only logging is required.
   *
   * @param statusCode will hold the value of any constant from CommonStatusCode or one of the
   *     WalletConstants.ERROR_CODE_* constants.
   * @see <a
   *     href="https://developers.google.com/android/reference/com/google/android/gms/wallet/WalletConstants#constant-summary">
   *     Wallet Constants Library</a>
   */
  private void handleError(int statusCode) {
    Log.w("loadPaymentData failed", String.format("Error code: %d", statusCode));
  }
    public void requestPayment(View view) {
    // Disables the button to prevent multiple clicks.
    mGooglePayButton.setClickable(false);

    // The price provided to the API should include taxes and shipping.
    // This price is not displayed to the user.
    String price = PaymentsUtil.microsToString(mBikeItem.getPriceMicros() + mShippingCost);

    // TransactionInfo transaction = PaymentsUtil.createTransaction(price);
    Optional<JSONObject> paymentDataRequestJson = PaymentsUtil.getPaymentDataRequest(price);
    if (!paymentDataRequestJson.isPresent()) {
      return;
    }
    PaymentDataRequest request =
        PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString());

    // Since loadPaymentData may show the UI asking the user to select a payment method, we use
    // AutoResolveHelper to wait for the user interacting with it. Once completed,
    // onActivityResult will be called with the result.
    if (request != null) {
      AutoResolveHelper.resolveTask(
          mPaymentsClient.loadPaymentData(request), this, LOAD_PAYMENT_DATA_REQUEST_CODE);
    }
  }
  
    @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
        // value passed in AutoResolveHelper
      case LOAD_PAYMENT_DATA_REQUEST_CODE:
        switch (resultCode) {
          case Activity.RESULT_OK:
            PaymentData paymentData = PaymentData.getFromIntent(data);
            handlePaymentSuccess(paymentData);
            break;
          case Activity.RESULT_CANCELED:
            // Nothing to here normally - the user simply cancelled without selecting a
            // payment method.
            break;
          case AutoResolveHelper.RESULT_ERROR:
            Status status = AutoResolveHelper.getStatusFromIntent(data);
            handleError(status.getStatusCode());
            break;
          default:
            // Do nothing.
        }

        // Re-enables the Google Pay payment button.
        mGooglePayButton.setClickable(true);
        break;
    }
  }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.olympiad.riddhima.class1_ieo.R.menu.menu_main, menu);
        return true;
    }

	/**
   * Determine the viewer's ability to pay with a payment method supported by your app and display a
   * Google Pay payment button.
   *
   * @see <a href=
   *     "https://developers.google.com/android/reference/com/google/android/gms/wallet/PaymentsClient.html#isReadyToPay(com.google.android.gms.wallet.IsReadyToPayRequest)">PaymentsClient#IsReadyToPay</a>
   */
  private void possiblyShowGooglePayButton() {
    final Optional<JSONObject> isReadyToPayJson = PaymentsUtil.getIsReadyToPayRequest();
    if (!isReadyToPayJson.isPresent()) {
      return;
    }
    IsReadyToPayRequest request = IsReadyToPayRequest.fromJson(isReadyToPayJson.get().toString());
    if (request == null) {
      return;
    }

    // The call to isReadyToPay is asynchronous and returns a Task. We need to provide an
    // OnCompleteListener to be triggered when the result of the call is known.
    Task<Boolean> task = mPaymentsClient.isReadyToPay(request);
    task.addOnCompleteListener(this,
        new OnCompleteListener<Boolean>() {
          @Override
          public void onComplete(@NonNull Task<Boolean> task) {
            if (task.isSuccessful()) {
              setGooglePayAvailable(task.getResult());
            } else {
              Log.w("isReadyToPay failed", task.getException());
            }
          }
        });
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
@Override
    public void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            //getActivity().unbindService(mServiceConn);
        }   
    }
    public void clickPopup(int testCode)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(Chaptersofscience1.this);
        // Set a title for alert dialog
        builder.setTitle("Premium upgrade required");
        // Ask the final question
        builder.setMessage("Premium software is required to access Mock Tests");
        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Upgrade", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE
                Toast.makeText(getApplicationContext(),
                        "Please pay through GPay",Toast.LENGTH_SHORT).show();                    }
        });
        // Set the alert dialog no button click listener
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }


}
