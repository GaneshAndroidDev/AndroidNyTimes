package com.android.nytimes.model.dto.common

import com.squareup.moshi.Json

data class Media(@field:Json(name = "type") var type: String? = null,
                 @field:Json(name = "subtype") var subtype: String? = null,
                 @field:Json(name = "caption") var caption: String? = null,
                 @field:Json(name = "copyright") var copyright: String? = null,
                 @field:Json(name = "approved_for_syndication") var approvedForSyndication: String? = null,
                 @field:Json(name = "media-metadata") var mediaMetadataList: MutableList<MediaMetadata>? = null)