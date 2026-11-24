package com.example.holdings;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView web = new WebView(this);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true); // 自动保存依赖 localStorage
        web.setWebViewClient(new WebViewClient());    // 页面内跳转留在 WebView 里
        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
    }
}
