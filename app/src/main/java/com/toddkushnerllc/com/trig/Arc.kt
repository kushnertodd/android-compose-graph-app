package com.toddkushnerllc.trig

import com.toddkushnerllc.diagram.Hold
import kotlin.math.sqrt

class Arc(val source: Hold, val target: Hold) {
    val x1 = source.center_x
    val y1 = source.center_y
    val x2 = target.center_x
    val y2 = target.center_y
    val dx: Float = x2 - x1
    val dy: Float = y2 - y1
    val d = sqrt(dx * dx + dy * dy)

    fun dot_cos(dx1: Float, dy1: Float, d1: Float) = (dx * dx1 + dy * dy1) / (d * d1)

    fun to_string(): String =
        "source.x $x1 source.y $y1 target.x $x2 target.y $y2 dx $dx dy $dy d $d"// theta_degrees $theta_degrees"
}

class Arcs {
    var arc_set = setOf<Arc>()
    fun add(arc: Arc) {
        arc_set += arc
    }
}