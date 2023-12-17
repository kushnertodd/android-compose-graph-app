package com.toddkushnerllc.diagram

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.toddkushnerllc.trig.Arc
import com.toddkushnerllc.trig.Line_segment

val edge_color = Color.GREEN;
val highlighted_edge_color = Color.RED
val edge_touch_distance = 30F

class Edge(val id: Int, val hold1: Hold, val hold2: Hold) {
    val paint = Paint()
    val highlighted_paint = Paint()
    var highlighted: Boolean = false
    var line_segment = Line_segment(hold1.center_x, hold1.center_y, hold2.center_x, hold2.center_y)

    init {
        paint.color = edge_color
        highlighted_paint.color = highlighted_edge_color
        val arc1 = Arc(hold1, hold2)
        hold1.arcs.add(arc1)
        val arc2 = Arc(hold2, hold1)
        hold2.arcs.add(arc2)
        Log.d(
            "Edge",
            "hold1 [${hold1.to_string()}] arc1 [${arc1.to_string()}] hold2 [${hold2.to_string()}] arc2 [${arc2.to_string()}] "
        )
    }

    fun draw(canvas: Canvas, holds: Holds) {
        val draw_paint = if (highlighted) highlighted_paint else paint
        canvas.drawLine(
            hold1.center_x, hold1.center_y,
            hold2.center_x, hold2.center_y, draw_paint
        )
    }

    fun to_string(): String = "hold1: [${hold1.to_string()} hold2: [${hold2.to_string()}"
    fun touched(x: Float, y: Float): Boolean =
        line_segment.near_line(x, y, edge_touch_distance)
}

class Edges() {
    var edge_set = setOf<Edge>()
    fun add(id: Int, id1: Int, id2: Int, holds: Holds) {
        var hold1: Hold? = holds.find(id1)
        var hold2: Hold? = holds.find(id2)
        if (hold1 != null && hold2 != null) {
            edge_set += Edge(id, hold1, hold2)
        }
    }
}

