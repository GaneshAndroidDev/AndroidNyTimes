package com.android.nytimes.util.sharepreference

import android.content.Context
import com.android.nytimes.util.CodeSnippet

class SharedPrefManager(context: Context) {
    private var codeSnippet: CodeSnippet = CodeSnippet(context)
    var sharedPref: SharedPref = SharedPref(context)
}