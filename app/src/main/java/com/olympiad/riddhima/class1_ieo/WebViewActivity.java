package com.olympiad.riddhima.class1_ieo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        webView.addJavascriptInterface(new SubscribeCheckInterface(), "SubscribeCheck");
        webView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageFinished(WebView view, String url) {
                // Inject JavaScript to check for "Subscribe" or "Subscribed" text
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    String javascript = "javascript:(function() {\n" +
                            "    function handleTextChange(mutationsList, observer) {\n" +
                            "        for (var mutation of mutationsList) {\n" +
                            "            if (mutation.type === 'childList' || mutation.type === 'characterData') {\n" +
                            "                var newTextContent = subscribeButton.textContent || subscribeButton.innerText;\n" +
                            "                console.log('Text content changed: ' + newTextContent);\n" +
                            "                SubscribeCheck.showButtonText(newTextContent);\n" +
                            "                break; // Exit the loop once we handle the mutation\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }\n" +
                            "\n" +
                            "    var subscribeButton = document.querySelector('div.yt-spec-button-shape-next__button-text-content');\n" +
                            "    if (subscribeButton) {\n" +
                            "        var buttonText = subscribeButton.textContent || subscribeButton.innerText;\n" +
                            "        SubscribeCheck.showButtonText(buttonText);\n" +
                            "        \n" +
                            "        var observerConfig = { subtree: true, characterData: true, childList: true };\n" +
                            "        var observer = new MutationObserver(handleTextChange);\n" +
                            "        observer.observe(subscribeButton, observerConfig);\n" +
                            "    } else {\n" +
                            "        SubscribeCheck.showButtonText('Subscribe button not found');\n" +
                            "    }\n" +
                            "})();\n";
                    webView.evaluateJavascript(javascript, null);
                }
            }
        });

        webView.loadUrl("https://www.youtube.com/@LearnMathTricks");
    }

    private class SubscribeCheckInterface {
        boolean isSubscribed = false;
        @JavascriptInterface
        public void showButtonText(String buttonText) {
            boolean isSubscribedLocal = buttonText.contains("Subscribed");
            if(isSubscribedLocal != isSubscribed){
                isSubscribed = isSubscribedLocal;
                getSharedPreferences("UserPreferences", Context.MODE_PRIVATE).edit().putBoolean("isPremiumCustomer",isSubscribedLocal).apply();
            }
        }
    }
}
