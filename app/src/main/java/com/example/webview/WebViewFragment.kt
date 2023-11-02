package com.example.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment

class WebViewFragment : Fragment() {

    private lateinit var webView: WebView
    private val url = "https://www.bbc.co.uk/sport/football"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment, container, false)

        webView = view.findViewById(R.id.webViewInFragment)
        initializeWebView()

        val backButton = view.findViewById<Button>(R.id.backButton)

        backButton.setOnClickListener {
            /*
            If you comment out this line then you will not be able to pinch to zoom
            */
            webView.destroy()
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    private fun initializeWebView() {
        /*
        zoom is disabled by default so the commented out settings do not need to be configured.
        You may uncomment the settings below but it won't make a difference when the webview is destroyed.
        */
        val webSettings: WebSettings = webView.settings
        // webSettings.setSupportZoom(false)
        // webSettings.builtInZoomControls = false
        // webSettings.displayZoomControls = false
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings.allowFileAccess = true
        webView.webViewClient = WebViewClient()

        webView.loadUrl(url)

        if (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.O) {
            webView.setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_IMPORTANT, true)
        }
    }
}
