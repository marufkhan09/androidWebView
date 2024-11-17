package com.app.webviewtest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        webView.setWebViewClient(new WebViewClient()); // Ensure links open within the WebView
        webView.loadUrl("https://youtube.com/"); // Load the URL


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



