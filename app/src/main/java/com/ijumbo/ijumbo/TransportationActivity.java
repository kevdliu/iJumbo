package com.ijumbo.ijumbo;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class TransportationActivity extends FragmentActivity {

    private static final String MAP_URL = "https://m.tufts.edu/transit/route?feed=transit_doublemap&direction=loop&agency=tufts&route=3";

    private WebView mWeb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        mWeb = (WebView) findViewById(R.id.web);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.progress);

        mWeb.setWebViewClient(new MyBrowser());
        mWeb.getSettings().setLoadsImagesAutomatically(true);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWeb.getSettings().setGeolocationDatabasePath(getFilesDir().getPath());
        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && bar.getVisibility() == ProgressBar.GONE) {
                    bar.setVisibility(ProgressBar.VISIBLE);
                }

                bar.setProgress(progress);

                if (progress == 100) {
                    bar.setVisibility(ProgressBar.GONE);
                }
            }
        });
        mWeb.loadUrl(MAP_URL);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (mWeb.canGoBack()) {
            mWeb.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}