package com.android.nytimes.model.dto.response

import com.android.nytimes.model.dto.common.NewsDetail
import com.squareup.moshi.Json

data class NewsResponse(@field:Json(name = "copyright") var copyright: String? = null,
                        @field:Json(name = "num_results") var numResults: String? = null,
                        @field:Json(name = "results") var data: MutableList<NewsDetail>? = null): BaseResponse()