package com.toddkushnerllc.diagram

import android.util.Log
import androidx.compose.ui.geometry.Offset
import kotlin.math.sqrt

class Diagram {
    var holds = Holds()
    var edges = Edges()
    var highlighted_hold: Hold? = null
    var highlighted_edge: Edge? = null
    val scroll_threshhold = 30;

    fun unhighlight() {
        val h = highlighted_hold
        if (h != null) {
            h.highlighted = false
            highlighted_hold = null
        }
        val e = highlighted_edge
        if (e != null) {
            e.highlighted = false
            highlighted_edge = null
        }
    }

    fun touched(down: Offset, up: Offset): Boolean {
        val downX = down.x
        val downY = down.y
        val upX = up.x
        val upY = up.y
        Log.d("Diagram.touched()", "downX $downX downY $downY upX $upX upY $upY")
        val dy = upY - downY
        val dx = upX - downX
        val d = sqrt(dx * dx + dy * dy)
        if (d < scroll_threshhold) {
            holds.hold_map.forEach() {
                val hold = it.value
                if (hold.touched(upX, upY)) {
                    unhighlight()
                    hold.highlighted = true
                    highlighted_hold = hold
                    return true
                }
            }
            edges.edge_set.forEach() {
                val edge = it
                if (edge.touched(upX, upY)) {
                    Log.d("Diagram.touched()", "upX $upX upY $upY edge ${edge.to_string()}")
                    if (edge.hold1 == highlighted_hold) {
                        unhighlight()
                        edge.hold2.highlighted = true
                        highlighted_hold = edge.hold2
                        return true
                    } else if (edge.hold2 == highlighted_hold) {
                        unhighlight()
                        edge.hold1.highlighted = true
                        highlighted_hold = edge.hold1
                        return true
                    }
                }
            }
        } else {
            if (highlighted_hold != null) {
                val hold = highlighted_hold!!.scrolled(dx, dy)
                if (hold != null) {
                    unhighlight()
                    hold.highlighted = true
                    highlighted_hold = hold
                    return true
                }
            }
        }
        return false
    }
}
