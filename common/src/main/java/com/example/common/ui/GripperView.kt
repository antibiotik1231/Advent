package com.example.common.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.common.R
import com.example.common.extensions.toPx

class GripperView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    companion object {
        private val HEIGHT = 4.toPx()
        private val WIDTH = 32.toPx()
        private val RADIUS = 2.toPx()
    }

    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.cardview_dark_background)
    }

    private val rect by lazy {
        RectF(
            (width / 2 - WIDTH / 2).toFloat(),
            (height / 2 - HEIGHT / 2).toFloat(),
            (width / 2 + WIDTH / 2).toFloat(),
            (height / 2 + HEIGHT / 2).toFloat()
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(WIDTH, HEIGHT)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(
            rect,
            RADIUS.toFloat(),
            RADIUS.toFloat(),
            paint
        )
    }
}
