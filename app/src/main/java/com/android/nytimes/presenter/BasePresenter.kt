package com.android.nytimes.presenter

import android.content.Intent
import com.android.nytimes.presenter.ipresenter.IPresenter
import com.android.nytimes.util.CodeSnippet
import com.android.nytimes.view.iview.IView


abstract class BasePresenter<T : IView>(protected var iView: T?) : IPresenter {

    protected var TAG = javaClass.simpleName


    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }


    override fun isInternetConnected(): Boolean {
        return iView?.codeSnippet?.hasNetworkConnection()!!
    }


    override fun getCodeSnippet(): CodeSnippet {
        return iView?.codeSnippet!!
    }

    override fun showNetworkUnavailable() {
        iView?.showNetworkMessage()
    }

    override fun logoutUser() {
        iView?.showForceLogoutDialog()
    }

    override fun showRetryOption() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgressbar() {
        iView?.dismissProgressbar()
    }


}