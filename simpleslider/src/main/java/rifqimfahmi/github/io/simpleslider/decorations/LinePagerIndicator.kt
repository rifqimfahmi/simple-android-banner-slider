package rifqimfahmi.github.io.simpleslider.decorations

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import rifqimfahmi.github.io.simpleslider.indicators.DashIndicator


class LinePagerIndicator: RecyclerView.ItemDecoration() {

    private val dashIndicator = DashIndicator()

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        super.onDrawOver(c, parent, state)

        val itemCount = parent.adapter?.itemCount ?: return

        dashIndicator.itemCount = itemCount
        val xPosition = dashIndicator.getX(parent)
        val yPosition = dashIndicator.getY(parent)

        drawInactiveIndicators(c, xPosition, yPosition, itemCount)
    }

    private fun drawInactiveIndicators(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int
    ) {
        dashIndicator.setInActive()
        for (position in 0 until itemCount) {
            val startX = dashIndicator.getStartX(indicatorStartX, position)
            val endX = startX + dashIndicator.getItemLength()
            val paint = dashIndicator.paint

            c.drawLine(
                startX,
                indicatorPosY,
                endX,
                indicatorPosY,
                paint
            )
        }
    }

}