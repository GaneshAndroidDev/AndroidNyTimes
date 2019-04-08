package com.android.nytimes.model.dto.common

import com.squareup.moshi.Json

data class MediaMetadata(@field:Json(name = "url") var url: String? = null,
                         @field:Json(name = "format") var format: String? = null,
                         @field:Json(name = "height") var height: Int? = null,
                         @field:Json(name = "width") var width: Int? = null)

