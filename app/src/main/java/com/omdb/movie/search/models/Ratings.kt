package com.omdb.movie.search.models

import com.google.gson.annotations.SerializedName

data class Ratings(
    @SerializedName("Source") var Source: String? = null,
    @SerializedName("Value") var Value: String? = null
)
