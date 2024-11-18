package com.app.webviewtest;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize WebView properly
        webView = findViewById(R.id.webView); // Fix: You need to initialize the WebView with findViewById

        // Configure WebView settings for optimal performance
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript if required by YouTube
        webView.getSettings().setDomStorageEnabled(true); // Enable DOM storage
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient()); // Ensure links open within the WebView
        webView.loadUrl("https://google.com/"); // Load the URL
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(s));
                request.setMimeType(s3);
                String cookies = CookieManager.getInstance().getCookie(s);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", webView.getSettings().getUserAgentString());
                request.setDescription("Downloading file...");
                request.setTitle(Uri.parse(s).getLastPathSegment());
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalFilesDir(MainActivity.this, "/downloads", Uri.parse(s).getLastPathSegment());
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);

                Toast.makeText(MainActivity.this, "Downloading Started", Toast.LENGTH_SHORT).show();
            }
        });



        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Custom back button behavior
                // For example, close the app or navigate to a specific screen
                if (webView.canGoBack()) {
                   //go back the app
                    webView.goBack();
                } else {
                    // Perform other actions
                    finish();
                }
            }
        });
    }
    private boolean shouldCloseApp() {
        // Add your logic here
        return true;
    }

    }



