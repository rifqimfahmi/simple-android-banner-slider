package rifqimfahmi.github.io.simpleslider

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import rifqimfahmi.github.io.simpleslider.decorations.LinePagerIndicator

class SimpleSliiderRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        setHasFixedSize(true)
        initLayoutManager()
        initItemDecoration()
        addSnapHelper()
    }

    private fun initLayoutManager() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initItemDecoration() {
        addItemDecoration(LinePagerIndicator())
    }

    private fun addSnapHelper() {
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(this)
    }


}