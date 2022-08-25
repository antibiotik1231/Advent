package com.example.adventapp.data.repository

import com.example.adventapp.data.mapper.GiphyMapper
import com.example.adventapp.data.network.GiphyApi
import com.example.adventapp.domain.entity.GiphyImage
import com.example.adventapp.domain.repository.GiphyRepository
import io.reactivex.Single
import javax.inject.Inject

internal class GiphyRepositoryImpl @Inject constructor(
    private val giphyApi: GiphyApi
) : GiphyRepository {

    override fun getGif(tag: String): Single<GiphyImage> =
        giphyApi.getGif(tag = tag)
            .map {
                GiphyMapper.mapDataToDomain(it.data)
            }
}