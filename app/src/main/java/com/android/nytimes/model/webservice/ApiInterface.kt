package com.android.nytimes.model.webservice

import com.android.nytimes.model.dto.response.NewsResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("mostviewed/{section}/{period}.json")
    fun getArticles(@Path("section") section: String, @Path("period") period: String, @Query("api-key") key: String): Call<NewsResponse>

}