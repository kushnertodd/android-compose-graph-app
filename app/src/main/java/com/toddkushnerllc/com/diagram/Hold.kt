package com.toddkushnerllc.diagram

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.toddkushnerllc.trig.Arcs
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sqrt

val hold_color = Color.BLUE
val highlighted_hold_color = Color.CYAN
val hold_radius = 30
val hold_touch_distance = 65
val cos_threshold = cos(20 * PI / 180)

class Hold(
    val id: Int,
    val center_x: Float,
    val center_y: Float,
    val radius: Int,
    var highlighted: Boolean = false
) {
    val paint = Paint()
    val highlighted_paint = Paint()
    val arcs = Arcs()

    init {
        paint.color = hold_color
        highlighted_paint.color = highlighted_hold_color
    }

    fun draw(canvas: Canvas) {
        val draw_paint = if (highlighted) highlighted_paint else paint
        canvas.drawCircle(center_x, center_y, radius.toFloat(), draw_paint)
    }

    fun scrolled(dx: Float, dy: Float): Hold? {
        Log.d("Hold.scrolled", "dx $dx dy $dy")
        val d = sqrt(dx * dx + dy * dy)
        var max_cos = -1.0F
        var max_hold: Hold? = null
        arcs.arc_set.forEach {
            val arc = it
            val arc_cos = arc.dot_cos(dx, dy, d)
            if (arc_cos > max_cos) {
                max_cos = arc_cos
                max_hold = arc.target
            }
        }
        if (max_cos > cos_threshold)
            return max_hold
        else
            return null
    }

    fun to_string(): String =
        "id: $id center_x $center_x center_y $center_y"

    fun touched(x: Float, y: Float): Boolean {
        val dx = center_x - x
        val dy = center_y - y
        val d = sqrt((dx * dx + dy * dy).toDouble())
        return d < hold_touch_distance
    }
}

class Holds {
    var hold_map = HashMap<Int, Hold>()
    fun add(id: Int, center_x: Float, center_y: Float) {
        hold_map[id] = Hold(id, center_x, center_y, hold_radius)
    }

    fun find(id: Int): Hold? = if (hold_map.containsKey(id)) hold_map[id] else null
}
