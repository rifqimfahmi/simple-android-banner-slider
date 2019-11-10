package rifqimfahmi.github.io.simpleslider

import android.content.res.Resources
import kotlin.math.roundToInt

fun Float.toDp(): Float = this / Resources.getSystem().displayMetrics.density

fun Float.toPx(): Float = this * Resources.getSystem().displayMetrics.density

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).roundToInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).roundToInt()

