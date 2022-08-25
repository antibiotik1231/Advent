package com.example.adventapp.data.network

import com.example.adventapp.data.network.model.ServerResponse
import com.example.common.config.Config
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface GiphyApi {

    @GET("v1/gifs/random")
    fun getGif(
        @Query("api_key") apiKey: String = Config.GIPHY_API_KEY,
        @Query("tag") tag: String,
        @Query("rating") rating: String = "g"
    ): Single<ServerResponse>
}
