package rifqimfahmi.github.io.simpleslider.decorations

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import androidx.annotation.Dimension.DP



class LinePagerIndicator: RecyclerView.ItemDecoration() {

    private val indicatorLength = 16 * 2
    private val indicatorPadding = 4 * 2
    private val indicatorPaddingBottom = 8 * 2

    private val colorActive = 0xFFFFFFFF
    private val colorInactive = 0x66FFFFFF
    private val mPaint = Paint()
    private val mIndicatorStrokeWidth = 2 * 2

    init {
        mPaint.setStrokeCap(Paint.Cap.ROUND)
        mPaint.setStrokeWidth(mIndicatorStrokeWidth.toFloat())
        mPaint.setStyle(Paint.Style.STROKE)
        mPaint.setAntiAlias(true)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        super.onDrawOver(c, parent, state)

        val itemCount = parent.adapter?.itemCount ?: return

        val totalIndicatorsLength = indicatorLength * itemCount
        val paddingBetweenIndicator = Math.max(0, itemCount - 1) * indicatorPadding
        val totalLength = totalIndicatorsLength + paddingBetweenIndicator

        val indicatorPosX = (parent.width - totalLength) / 2f
        val indicatorPosY: Float = (parent.height - indicatorPaddingBottom).toFloat()

        drawInactiveIndicators(c, indicatorPosX, indicatorPosY, itemCount)
    }

    private fun drawInactiveIndicators(
        c: Canvas, indicatorStartX: Float,
        indicatorPosY: Float, itemCount: Int
    ) {
        mPaint.setColor(colorInactive)

        // width of item indicator including padding
        val itemWidth = indicatorLength + indicatorPadding

        var start = indicatorStartX
        for (i in 0 until itemCount) {
            // draw the line for every item
            c.drawLine(
                start, indicatorPosY,
                start + indicatorLength, indicatorPosY, mPaint
            )
            start += itemWidth
        }
    }
}