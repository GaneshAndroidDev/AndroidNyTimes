package com.android.nytimes.model.webservice

import android.content.Context
import com.android.nytimes.BuildConfig
import com.android.nytimes.common.Constants.ApiHeaderKey.Companion.AUTHORIZATION
import com.android.nytimes.util.sharepreference.SharedPrefManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient(var context: Context) {

    fun getClient(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY   // set your desired log level

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.connectTimeout(2, TimeUnit.MINUTES) //Connection time out set limit
        httpClient.readTimeout(2, TimeUnit.MINUTES)  //Connection read time out set limit

        // addItem your other interceptors …
        // addItem logging as last interceptor
        if (BuildConfig.DEBUG)
            httpClient.addInterceptor(logging)  // <-- this is the important line!
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()) /*Use can mention your desired parser over here.!*/
            .build()
    }

    fun getApiInterface() : ApiInterface = getClient().create(ApiInterface::class.java)
}
