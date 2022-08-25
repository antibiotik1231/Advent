package com.example.adventapp.data.network.model

import com.squareup.moshi.Json

internal data class ImageData(
    @field:Json(name = "original")
    val originalImageData: OriginalImageData = OriginalImageData()
)