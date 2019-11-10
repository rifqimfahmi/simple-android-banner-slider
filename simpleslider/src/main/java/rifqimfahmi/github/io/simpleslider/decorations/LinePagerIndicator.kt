package rifqimfahmi.github.io.simpleslider.decorations

import android.graphics.Canvas
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
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
        drawActiveIndicator(c, parent, xPosition, yPosition)
    }

    private fun drawInactiveIndicators(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int
    ) {
        for (position in 0 until itemCount) {
            val startX = dashIndicator.getStartX(indicatorStartX, position)
            val endX = startX + dashIndicator.getItemLength()
            val paint = dashIndicator.getInActivePaint()

            c.drawLine(
                startX,
                indicatorPosY,
                endX,
                indicatorPosY,
                paint
            )
        }
    }


    private fun drawActiveIndicator(
        c: Canvas,
        parent: RecyclerView,
        indicatorStartX: Float,
        indicatorPosY: Float
    ) {
        val layoutManager = parent.layoutManager as LinearLayoutManager
        val currentItem = layoutManager.findFirstVisibleItemPosition()

        if (currentItem == NO_POSITION) return

        val startX = dashIndicator.getStartX(indicatorStartX, currentItem)
        val endX = startX + dashIndicator.getItemLength()
        val paint = dashIndicator.getActivePaint()

        c.drawLine(
            startX,
            indicatorPosY,
            endX,
            indicatorPosY,
            paint
        )
    }

}