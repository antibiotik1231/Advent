package com.example.adventapp.data.repository

import com.example.adventapp.data.mapper.GiphyMapper
import com.example.adventapp.data.network.GiphyApi
import com.example.adventapp.domain.entity.GiphyImage
import io.reactivex.Single
import javax.inject.Inject

internal class GiphyRepository @Inject constructor(
    private val giphyApi: GiphyApi
) {

    fun getGif(tag: String): Single<GiphyImage> =
        giphyApi.getGif(tag = tag)
            .map {
                GiphyMapper.mapDataToDomain(it.data)
            }
}