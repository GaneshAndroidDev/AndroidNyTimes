package com.android.nytimes.presenter

import android.os.Bundle
import com.android.nytimes.common.Constants.BundleKey.Companion.KEY_URL
import com.android.nytimes.presenter.ipresenter.IWebViewPresenter
import com.android.nytimes.view.iview.IWebViewView

/**
 * Created by Ganesh on 16/10/18.
 */
class WebViewPresenter(iWebViewView: IWebViewView) : BasePresenter<IWebViewView>(iWebViewView), IWebViewPresenter {

    override fun onCreate(bundle: Bundle?) {
        if (bundle != null) {
            if (bundle.containsKey(KEY_URL))
                iView?.loadUrl(bundle.getString(KEY_URL))
        }
    }


}