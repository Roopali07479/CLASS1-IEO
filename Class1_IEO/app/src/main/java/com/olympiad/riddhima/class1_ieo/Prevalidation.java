package com.olympiad.riddhima.class1_ieo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;

import java.util.ArrayList;


/**
 * Created by Ashish on 12-09-2015.
 */
public class Prevalidation extends Activity
{
    IInAppBillingService mService;
    static final String ITEM_SKU = "android.test.purchased";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent("com.android.vending.billing.InAppBillingService.BIND"), mServiceConn, Context.BIND_AUTO_CREATE);
       //// Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
// This is the key line that fixed everything for me
        //intent.setPackage("com.android.vending");
        //getContext().bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(com.olympiad.riddhima.class1_ieo.R.menu.menu_main, menu);
        return true;
    }

    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IInAppBillingService.Stub.asInterface(service);
            Log.d("TEST", "mService ready to go!");
            checkownedItems();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };
    private void checkownedItems()
    {
        try
        {
            Bundle ownedItems = mService.getPurchases(3, getPackageName(), "inapp", null);

            if(ownedItems.getInt("RESPONSE_CODE") == 0)
            {
                ArrayList<String> ownedSkus = ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> purchaseDataList = ownedItems.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> signatureList = ownedItems.getStringArrayList("INAPP_DATA_SIGNATURE");
                String continuationToken = ownedItems.getString("INAPP_CONTINUATION_TOKEN");

                if(purchaseDataList.size() > 0)
                {
                    //Item(s) owned

                    for(int i=0; i<purchaseDataList.size(); ++i)
                    {
                        String purchaseData = purchaseDataList.get(i);
                        String signature = signatureList.get(i); //Note signatures do not appear to work with android.test.purchased (silly google)
                        String sku = ownedSkus.get(i);

                            Toast.makeText(getApplication(), "user have this item", Toast.LENGTH_LONG).show();

                    }
                }
                else
                {
                    //Item(s) not owned

                    String base64EncodedPublicKey = "public_key";

                    IabHelper  mHelper = new IabHelper(Prevalidation.this, base64EncodedPublicKey);

                    mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener()
                    {
                        @Override
                        public void onIabSetupFinished(IabResult result)
                        {
                            if(!result.isSuccess())
                            {
                                Log.d("TEST", "In-app Billing setup failed: " + result);
                            }
                            else
                            {
                                Log.d("TEST", "In-app Billing is set up OK");
                            }
                        }
                    });
                }
            }
            else
            {
                //Error checking owned items
            }
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }




    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if(mServiceConn != null)
        {
            unbindService(mServiceConn);
        }
    }
}