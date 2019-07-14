package com.andela.alc40;

import android.app.ProgressDialog;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

    }

    private void init() {
        progDailog = ProgressDialog.show(AboutActivity.this, "Loading","Please wait...", true);
        progDailog.setCancelable(false);



        webView= findViewById(R.id.webviewAbout);
        WebSettings wbs = webView.getSettings();
        wbs.setJavaScriptEnabled(true);

        wbs.setLoadWithOverviewMode(true);
        wbs.setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // ignore ssl error
                if (handler != null){
                    handler.proceed();
                } else {
                    super.onReceivedSslError(view, null, error);
                }
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            wbs.setMixedContentMode( WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
        }


        webView.loadUrl("https://andela.com/alc/");

    }

}
