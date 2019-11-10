package rifqimfahmi.github.io.simpleslider.indicators

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import rifqimfahmi.github.io.simpleslider.toPx

class DashIndicator: Indicator() {

    // Length of each indicator
    private val length = 16.toPx()
    private val margin = 8.toPx()
    private val paddingBottom = 16.toPx()
    private val lineStrokeWidth = 3.toPx()
    private val paint = Paint()
    private val colorActive = 0xFFFFFFFF
    private val colorInactive = 0x80FFFFFF

    var itemCount = 0

    init {
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            strokeWidth = lineStrokeWidth.toFloat()
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
    }

    fun getX(parent: RecyclerView): Float {
        val totalLength = getTotalLength()
        return (parent.width - totalLength) / 2f
    }

    fun getY(parent: RecyclerView): Float {
        return (parent.height - paddingBottom).toFloat()
    }

    private fun getTotalLength(): Int {
        val indicatorTotalLength = getIndicatorsLength()
        val indicatorSpacingLength = getSpacingLength()
        return indicatorTotalLength + indicatorSpacingLength
    }

    private fun getIndicatorsLength(): Int {
        return itemCount * length
    }

    private fun getSpacingLength(): Int {
        val totalSpace = 0.coerceAtLeast(itemCount - 1)
        return totalSpace * margin
    }

    private fun setActive() {
        paint.color = colorActive.toInt()
    }

    private fun setInActive() {
        paint.color = colorInactive.toInt()
    }

    fun getStartX(indicatorStartX: Float, position: Int): Float {
        val previousIndicatorLength = position * length
        val previousSpacingLength = position * margin
        val previousLength = previousIndicatorLength + previousSpacingLength
        return indicatorStartX + previousLength
    }

    fun getItemLength(): Float {
        return length.toFloat()
    }

    fun getInActivePaint(): Paint {
        setInActive()
        return paint
    }

    fun getActivePaint(): Paint {
        setActive()
        return paint
    }

    fun getItemLengthWithMargin(): Int {
        return length + margin
    }
}