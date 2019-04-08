package com.android.nytimes.model.dto.common

import com.squareup.moshi.Json

data class NewsDetail(@field:Json(name = "url") var url: String? = null,
                      @field:Json(name = "adx_keywords") var adxKeywords: String? = null,
                      @field:Json(name = "column") var column: String? = null,
                      @field:Json(name = "section") var section: String? = null,
                      @field:Json(name = "byline") var byline: String? = null,
                      @field:Json(name = "type") var type: String? = null,
                      @field:Json(name = "title") var title: String? = null,
                      @field:Json(name = "abstract") var abstract: String? = null,
                      @field:Json(name = "published_date") var publishedDate: String? = null,
                      @field:Json(name = "source") var source: String? = null,
                      @field:Json(name = "id") var id: String? = null,
                      @field:Json(name = "asset_id") var assetId: String? = null,
                      @field:Json(name = "views") var views: Long? = null,
//                      @field:Json(name = "des_facet") var desFacets: MutableList<String>? = null,
//                      @field:Json(name = "org_facet") var orgFacets: MutableList<String>? = null,
//                      @field:Json(name = "per_facet") var perFacet: MutableList<String>? = null,
//                      @field:Json(name = "geo_facet") var geoFacet: MutableList<String>? = null,
                      @field:Json(name = "media") var media: MutableList<Media>? = null)

