package com.android.nytimes.presenter.ipresenter

import com.android.nytimes.util.CodeSnippet


interface IRepositoryModel {

    fun isInternetConnected(): Boolean

    fun logoutUser()

    fun getCodeSnippet(): CodeSnippet

    fun showRetryOption()

    fun showNetworkUnavailable()

    fun dismissProgressbar()

}