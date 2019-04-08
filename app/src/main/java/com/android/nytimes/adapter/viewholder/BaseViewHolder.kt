package com.android.nytimes.adapter.viewholder


import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.nytimes.util.CodeSnippet


abstract class BaseViewHolder<T, VB : ViewDataBinding> : RecyclerView.ViewHolder {


    internal var codeSnippet = CodeSnippet(itemView.context)

    var data: T? = null
        set(value) {
            field = value
            data?.let { populateData(it) }
        }

    protected lateinit var viewDataBinding: VB

    open  var lastItemPosition = 0

    internal var TAG = javaClass.simpleName

    internal constructor(viewDataBinding: VB) : super(viewDataBinding.root) {
        this.viewDataBinding = viewDataBinding
    }

    internal constructor(itemView: View) : super(itemView)

    internal abstract fun populateData(data: T)

}