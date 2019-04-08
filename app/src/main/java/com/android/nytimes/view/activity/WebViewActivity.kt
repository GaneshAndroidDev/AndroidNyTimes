package com.android.nytimes.view.activity

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.nytimes.R
import com.android.nytimes.databinding.ActivityWebViewBinding
import com.android.nytimes.presenter.WebViewPresenter
import com.android.nytimes.presenter.ipresenter.IWebViewPresenter
import com.android.nytimes.view.iview.IWebViewView

/**
 * Created by Ganesh on 16/10/18.
 */
class WebViewActivity : BaseActivity<ActivityWebViewBinding, IWebViewPresenter>(), IWebViewView {

    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }

    override fun onInitializePresenter(): IWebViewPresenter {
        return WebViewPresenter(this)
    }

    override fun onPresenterCreated() {
        initializeWebView()
    }

    /**
     * Initializing WebView here
     * */
    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView() {

        // Enable Javascript to run in WebView
        bViewDataBinding?.webView?.settings?.javaScriptEnabled = true
        bViewDataBinding?.webView?.settings?.domStorageEnabled = true

        // Allow Zoom in/out controls
        bViewDataBinding?.webView?.settings?.builtInZoomControls = false

        // Zoom out the best fit your screen
        bViewDataBinding?.webView?.settings?.loadWithOverviewMode = true
        bViewDataBinding?.webView?.settings?.useWideViewPort = true

        // Show the progress bar
        bViewDataBinding?.webView?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress < 100) {
                    showProgressbar()
                }

                if (progress >= 50) {
                    dismissProgressbar()
                }
            }
        }

        bViewDataBinding?.webView?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            //Show loader on url load
            override fun onLoadResource(view: WebView, url: String) {

            }

            override fun onPageFinished(view: WebView, url: String) {
            }
        }
    }


    /**
     * To Load Url
     * */
    override fun loadUrl(url: String?) {
        initializeWebView()
        if (!url.isNullOrBlank())
            bViewDataBinding?.webView?.loadUrl(url)
    }

    /**
     * For Default Back button
     * */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bViewDataBinding?.webView!=null && bViewDataBinding?.webView!!.canGoBack())
                run { bViewDataBinding?.webView?.goBack() }
            else {
                finish()
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }


}