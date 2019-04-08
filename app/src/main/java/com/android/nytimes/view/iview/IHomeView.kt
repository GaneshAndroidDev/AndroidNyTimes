package com.android.nytimes.view.iview

import android.os.Bundle
import com.android.nytimes.adapter.ArticleAdapter

interface IHomeView : IView {
    fun setArticlesAdapter(articlesAdapter: ArticleAdapter)
    fun navigateToDetail(bundle: Bundle)

}