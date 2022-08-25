package com.example.adventapp.data.mapper

import com.example.adventapp.data.network.model.GiphyData
import com.example.adventapp.domain.entity.GiphyImage
import com.example.common.config.Config

internal object GiphyMapper {

    fun mapDataToDomain(giphyData: GiphyData?): GiphyImage {
        return GiphyImage(
            url = giphyData?.images?.originalImageData?.url ?: Config.DEFAULT_IMAGE_URL
        )
    }
}