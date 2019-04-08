package com.android.nytimes.adapter

import android.view.ViewGroup
import com.android.nytimes.R
import com.android.nytimes.adapter.listener.BaseRecyclerAdapterListener
import com.android.nytimes.adapter.viewholder.ArticleViewHolder
import com.android.nytimes.databinding.InflateItemArticleBinding
import com.android.nytimes.model.dto.common.NewsDetail

class ArticleAdapter(data: MutableList<NewsDetail>?, var listener: BaseRecyclerAdapterListener<NewsDetail>) : BaseRecyclerAdapter<NewsDetail, ArticleViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(inflateDataBinding(R.layout.inflate_item_article, parent) as InflateItemArticleBinding, listener)
    }
}