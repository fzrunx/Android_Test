package com.example.android_test.naver.news

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Suppress("SetJavaScriptEnabled")
@Composable
fun NaverNewsWebView(url: String) {
    AndroidView(factory = {context ->
        WebView(context).apply{
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }

    })
}