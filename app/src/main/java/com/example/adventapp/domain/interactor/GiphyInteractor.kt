package com.example.adventapp.domain.interactor

import com.example.adventapp.data.repository.GiphyRepository
import com.example.adventapp.domain.entity.GiphyImage
import io.reactivex.Single
import javax.inject.Inject

internal class GiphyInteractor @Inject constructor(
    private val giphyRepositoryImpl: GiphyRepository
) {

    fun getGif(tag: String): Single<GiphyImage> {
        return giphyRepositoryImpl.getGif(tag)
    }
}