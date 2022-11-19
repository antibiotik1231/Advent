package com.example.adventapp.ui

import android.os.Parcelable
import com.example.adventapp.domain.entity.Mode
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class UiModel(
    val backgroundImageId: Int,
    val mode: Mode
) : Parcelable {

    companion object {
        val EMPTY = UiModel(0, Mode.DIANA)
    }
}
