package com.android.nytimes.presenter

import android.os.Bundle
import com.android.nytimes.BuildConfig
import com.android.nytimes.adapter.ArticleAdapter
import com.android.nytimes.adapter.listener.BaseRecyclerAdapterListener
import com.android.nytimes.common.Constants.BundleKey.Companion.KEY_URL
import com.android.nytimes.model.ModelRepository
import com.android.nytimes.model.dto.common.NewsDetail
import com.android.nytimes.presenter.ipresenter.IHomePresenter
import com.android.nytimes.util.CustomException
import com.android.nytimes.view.iview.IHomeView
import io.reactivex.android.schedulers.AndroidSchedulers

class HomePresenter(private val iHomeView: IHomeView) : BasePresenter<IHomeView>(iHomeView), IHomePresenter {

    private var articles: MutableList<NewsDetail>? = null
    private var articlesAdapter: ArticleAdapter? = null

    override fun onCreate(bundle: Bundle?) {
        articles = ArrayList()
        getArticlesRequest()
    }

    override fun getArticlesRequest() {

        val section = "all-sections"
        val period = "7"

        iHomeView.showProgressbar()
        iHomeView.getActivity()?.let {
            ModelRepository(it, this)
                .getArticles(section, period, BuildConfig.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    if (result.data != null && result.data!!.isNotEmpty())
                        if (articles != null)
                            articles?.clear()
                    articles?.addAll(result.data!!)
                    setArticlesAdapter()
                }, { error ->
                    iView?.showMessage((error as CustomException).exception)
                })
        }

    }

    private fun setArticlesAdapter() {
        if (articlesAdapter == null) {
            articlesAdapter = ArticleAdapter(articles, articlesAdapterListener)
            iView?.setArticlesAdapter(articlesAdapter!!)
        } else {
            articlesAdapter?.notifyDataSetChanged()
        }


    }


    private val articlesAdapterListener = object : BaseRecyclerAdapterListener<NewsDetail> {

        override fun onClickItem(position: Int, data: NewsDetail?) {
            onItemClick(data)
        }
    }

    private fun onItemClick(data: NewsDetail?) {
        data?.url?.let {
            val bundle = Bundle()
            bundle.putString(KEY_URL, it)
            iView?.navigateToDetail(bundle)
        }
    }


}