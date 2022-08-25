package com.example.adventapp.domain.repository

import com.example.adventapp.domain.entity.GiphyImage
import io.reactivex.Single

internal interface GiphyRepository {

    fun getGif(tag: String): Single<GiphyImage>
}