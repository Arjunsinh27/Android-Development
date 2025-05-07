package com.example.webview;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Additional not necessarily required
        //****
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        //****


        webView.setWebViewClient(new WebViewClient());//without this line the website will open in external browser and not in our app
        webView.loadUrl("https://www.google.com/");


        //if onBackPressed() method didn't work
//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                if (webView.canGoBack()) {
//                    webView.goBack();
//                } else {
//                    // If no more history, proceed with default back action
//                    setEnabled(false);
//                    getOnBackPressedDispatcher().onBackPressed();
//                }
//            }
//        };
//        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    //This method is depricated
    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }





}