package rifqimfahmi.github.io.simpleslider.decorations

import android.graphics.Canvas
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import androidx.recyclerview.widget.RecyclerView.State
import rifqimfahmi.github.io.simpleslider.indicators.DashIndicator


class LinePagerIndicator : RecyclerView.ItemDecoration() {

    private val dashIndicator = DashIndicator()

    private val interpolator = AccelerateDecelerateInterpolator()

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        super.onDrawOver(c, parent, state)

        val itemCount = parent.adapter?.itemCount ?: return

        dashIndicator.itemCount = itemCount
        val xPosition = dashIndicator.getX(parent)
        val yPosition = dashIndicator.getY(parent)

        drawInactiveIndicators(c, xPosition, yPosition, itemCount)
        drawActiveIndicator(c, parent, xPosition, yPosition, itemCount)
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
        indicatorPosY: Float,
        itemCount: Int
    ) {
        val layoutManager = parent.layoutManager as LinearLayoutManager
        val itemPosition = layoutManager.findFirstVisibleItemPosition()

        if (itemPosition == NO_POSITION) return

        val itemView = layoutManager.findViewByPosition(itemPosition) ?: return
        val viewLeft = itemView.left
        val viewWidth = itemView.width

        val progress = interpolator.getInterpolation(viewLeft * -1 / viewWidth.toFloat())

        if (progress == 0f) {
            // drawn normal no progress
            val startX = dashIndicator.getStartX(indicatorStartX, itemPosition)
            val endX = startX + dashIndicator.getItemLength()
            val paint = dashIndicator.getActivePaint()
            c.drawLine(
                startX,
                indicatorPosY,
                endX,
                indicatorPosY,
                paint
            )
        } else {
            val itemWidth = dashIndicator.getItemLengthWithMargin()
            var partialStartX = dashIndicator.getStartX(indicatorStartX, itemPosition)
            val partialLength = dashIndicator.getItemLength() * progress
            val paint = dashIndicator.getActivePaint()

            // draw partial left
            c.drawLine(
                partialStartX + partialLength,
                indicatorPosY,
                partialStartX + dashIndicator.getItemLength(),
                indicatorPosY,
                paint
            )

            // draw partial right
            if (itemPosition < itemCount - 1) {
                partialStartX += itemWidth
                c.drawLine(
                    partialStartX,
                    indicatorPosY,
                    partialStartX + partialLength,
                    indicatorPosY,
                    paint
                )
            }
        }
    }

}