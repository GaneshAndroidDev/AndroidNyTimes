package com.android.nytimes.adapter.listener


interface BaseRecyclerAdapterListener<T> {

    fun onClickItem(position: Int, data: T?)

}