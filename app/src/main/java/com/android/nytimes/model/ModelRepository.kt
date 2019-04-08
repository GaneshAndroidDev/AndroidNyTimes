package com.android.nytimes.model

import android.content.Context
import com.android.nytimes.common.Constants
import com.android.nytimes.common.Constants.InternalHttpCode.Companion.BAD_REQUEST_CODE
import com.android.nytimes.common.Constants.InternalHttpCode.Companion.FAILURE_CODE
import com.android.nytimes.common.Constants.InternalHttpCode.Companion.INTERNAL_SERVER_ERROR_CODE
import com.android.nytimes.common.Constants.InternalHttpCode.Companion.TIMEOUT_CODE
import com.android.nytimes.common.Constants.InternalHttpCode.Companion.UNAUTHORIZED_CODE
import com.android.nytimes.model.dto.response.BaseResponse
import com.android.nytimes.model.dto.response.NewsResponse
import com.android.nytimes.model.webservice.ApiClient
import com.android.nytimes.presenter.ipresenter.IRepositoryModel
import com.android.nytimes.util.CustomException
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


open class ModelRepository(var context: Context, var iRepositoryModel: IRepositoryModel) {

    var apiClient = ApiClient(context)

    protected fun <T : BaseResponse> enqueue(observable: Observable<T>): Observable<T> {

        return Observable.create<T> { emitter: ObservableEmitter<T> ->
            run {

                observable.subscribeOn(Schedulers.newThread())
                    .subscribeWith(object : CallbackWrapper<T>(apiClient) {
                        override fun success(response: T) {
                            Timber.d("Success: ${response.status}")
                            emitter.onNext(response)
                        }

                        override fun failure(e: Throwable) {
                            Timber.d("failure: $e")
                            when ((e as CustomException).code) {
                                TIMEOUT_CODE -> {
                                    emitter.onError(Throwable(e.exception))
                                }
                                BAD_REQUEST_CODE -> {
                                    emitter.onError(Throwable(e.exception))
                                }
                                INTERNAL_SERVER_ERROR_CODE -> {
                                    emitter.onError(Throwable(e.exception))
                                }
                                UNAUTHORIZED_CODE -> {
                                    emitter.onError(e)
                                }
                                FAILURE_CODE -> {
                                    emitter.onError(Throwable(e.exception))
                                }
                                else -> emitter.onError(Throwable(e.exception))
                            }
//

                        }
                    })


            }
        }

    }


    private fun <T : BaseResponse> enqueue(callback: Call<T>): Observable<T> {

        if (iRepositoryModel.isInternetConnected()) {
            return Observable.create<T> {
                callback.enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        dismissProcess()
                        if (response.body() != null && response.isSuccessful) {
                            val result = response.body()
                            if (response.code() == 200) {
                                result?.let { it1 -> it.onNext(it1) }
                            } else {
                                it.onError(CustomException(response.code(), result))
                            }
                        } else if (response.body() != null) {
                            it.onError(CustomException(response.code(), response.body()))
                        } else if (response.code() == Constants.InternalHttpCode.UNAUTHORIZED_CODE) {
                            iRepositoryModel.logoutUser()
                        } else {
                            it.onError(
                                CustomException(
                                    response.code(),
                                    Constants.HttpErrorMessage.INTERNAL_SERVER_ERROR
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<T>?, t: Throwable?) {
                        dismissProcess()
                        try {
                            Timber.d("onFailure : $t")
                            t?.let { it1 -> it.onError(CustomException(501, t.localizedMessage)) }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }

                })
            }
        } else {
            iRepositoryModel.showNetworkUnavailable()
            dismissProcess()
            return Observable.never()
        }


    }

    private fun dismissProcess() {
        iRepositoryModel.dismissProgressbar()
    }


    fun getArticles(section: String, period: String, key: String): Observable<NewsResponse> =
        enqueue(apiClient.getApiInterface().getArticles(section, period, key))


}