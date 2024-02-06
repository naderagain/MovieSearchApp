package com.omdb.movie.search.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("Search"       ) var Search       : ArrayList<Movie> = arrayListOf(),
    @SerializedName("totalResults" ) var totalResults : String?           = null,
    @SerializedName("Response"     ) var Response     : String?           = null
)