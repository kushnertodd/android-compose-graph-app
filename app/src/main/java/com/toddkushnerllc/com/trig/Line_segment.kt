package com.toddkushnerllc.trig

import android.util.Log
import kotlin.math.sqrt

class Line_segment {
    val x1: Float
    val y1: Float
    val x2: Float
    val y2: Float
    val d: Float
    val line: Line_polar

    constructor(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) {
        this.x1 = x1
        this.y1 = y1
        this.x2 = x2
        this.y2 = y2
        val dx: Float = x2 - x1
        val dy: Float = y1 - y2
        this.d = sqrt(dx * dx + dy * dy)
        val theta = Angle(dx, dy, d)
        val r = (x2 * y1 - x1 * y2) / d
        line = Line_polar(theta, r)
    }

    fun near_line(x0: Float, y0: Float, line_distance_threshold: Float): Boolean {
        val distance = line.distance_to_line(x0, y0)
        val line_polar_factory = Line_polar_factory()
        val normal_line = line_polar_factory.normal_line(line.theta, line.r, x0, y0)
        val dist_endpoint1 = normal_line.distance_to_line(x1, y1)
        val dist_endpoint2 = normal_line.distance_to_line(x2, y2)
        Log.d(
            "Diagram.touched()",
            "x0 $x0 y0 $y0 distance $distance dist_endpoint1 $dist_endpoint1 dist_endpoint2 $dist_endpoint2 line_distance_threshold $line_distance_threshold ${to_string()}"
        )
        return (distance <= line_distance_threshold)
                && (dist_endpoint1 <= d)
                && (dist_endpoint2 <= d)
    }

    fun to_string(): String = "x1 $x1 y1 $y1 x2 $x2 y2 $y2 dx ${line.to_string()}"
}