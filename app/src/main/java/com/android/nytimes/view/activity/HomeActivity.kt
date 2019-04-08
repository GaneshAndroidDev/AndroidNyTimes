package com.android.nytimes.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.nytimes.R
import com.android.nytimes.adapter.ArticleAdapter
import com.android.nytimes.databinding.ActivityMainBinding
import com.android.nytimes.presenter.HomePresenter
import com.android.nytimes.presenter.ipresenter.IHomePresenter
import com.android.nytimes.view.iview.IHomeView
import timber.log.Timber

class HomeActivity : BaseActivity<ActivityMainBinding, IHomePresenter>(), IHomeView {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onInitializePresenter(): IHomePresenter {
        return HomePresenter(this)
    }

    override fun setArticlesAdapter(articlesAdapter: ArticleAdapter) {
        bViewDataBinding?.rvHome?.getRecyclerViewInstance()?.layoutManager = LinearLayoutManager(this)
        bViewDataBinding?.rvHome?.getRecyclerViewInstance()?.adapter = articlesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        bViewDataBinding?.rvHome?.getRecyclerViewInstance()?.removeObserver()
    }

    /*This override method use to handler internet connection state.*/
    override fun onConnectionChange(isConnected: Boolean) {
        if(isConnected)
            iPresenter?.getArticlesRequest()
        else
            showNetworkMessage()
    }

    override fun navigateToDetail(bundle: Bundle) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }


}
