package com.android.nytimes.adapter.viewholder

import com.android.nytimes.adapter.listener.BaseRecyclerAdapterListener
import com.android.nytimes.databinding.InflateItemArticleBinding
import com.android.nytimes.model.dto.common.NewsDetail
import com.android.nytimes.util.extensions.loadUrl

class ArticleViewHolder(view: InflateItemArticleBinding, var listener: BaseRecyclerAdapterListener<NewsDetail>) : BaseViewHolder<NewsDetail, InflateItemArticleBinding>(view) {

    override fun populateData(data: NewsDetail) {

        viewDataBinding.data = data
        viewDataBinding.position = adapterPosition
        viewDataBinding.listener = listener

        if(data.media!=null && data.media!!.isNotEmpty()
            && data.media!![0].mediaMetadataList!=null && data.media!![0].mediaMetadataList!!.isNotEmpty() &&
            !data.media!![0].mediaMetadataList!![0].url.isNullOrBlank())
        viewDataBinding.ivArticle.loadUrl(data.media!![0].mediaMetadataList!![0].url)
    }

}