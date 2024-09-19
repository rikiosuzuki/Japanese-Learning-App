package com.example.suzuki_r_finalproject;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JapanesePronunciationActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japanese_pronunciation);
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        String frameVideo = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/4Irzvrcpf4Q?si=Gkfqm8CHBBX5xkSA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView.loadData(frameVideo, "text/html", "utf-8");
        webView.setWebChromeClient(new WebChromeClient());
    }

    public void handleGoBack(View view) {
        finish();
    }
}