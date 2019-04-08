package com.android.nytimes.view.iview

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.android.nytimes.util.CodeSnippet
import com.android.nytimes.util.CustomException

interface IView {
    fun showMessage(message: String?)

    fun showMessage(resId: Int)

    fun showMessage(e: CustomException)

    fun showAlerterMessage(message: String?)

    fun showAlerterMessage(resId: Int)

    fun showAlerterMessage(e: CustomException)

    fun onPresenterCreated()

    fun showProgressbar()

    fun dismissProgressbar()

    fun getActivity(): FragmentActivity?

    fun showSnackBar(message: String)

    fun showSnackBar(view: View, message: String)

    fun showNetworkMessage()

    fun showForceLogoutDialog()

    val codeSnippet: CodeSnippet

    fun onBackPress()

    fun onConnectionChange(isConnected: Boolean) {}

}