package com.example.shihab.loadwebsite;

/**
 * Created by shihab on 9/25/2017.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewHelper {

    private ProgressDialog mProgressDialog;

    //make it final so it will be accessible to setWebViewClient
    public WebView webview(final Context mContext) {

        // progress dialog
        mProgressDialog = new ProgressDialog(mContext);

        // new webview
        WebView web = new WebView(mContext);

        // web settings
        WebSettings webSettings = web.getSettings();

        // true
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setAppCacheEnabled(true);

        // true
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        // other
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setRenderPriority(RenderPriority.HIGH);

        web.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //show the user progress percentage
                mProgressDialog.setMessage("LOADING ..." + progress + "%");
            }
        });

        web.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                //if there’s an error loading the page, make a toast
                Toast.makeText(mContext, "ERROR LOADING PAGE" + ".", Toast.LENGTH_SHORT).show();

            }

            public void onPageFinished(WebView view, String url) {
                //after loading page, remove loading page
                mProgressDialog.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);

                //on page started, show loading page
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage("Loading…");
                mProgressDialog.show();

            }

        });

        // return the web view
        return web;
    }
}
