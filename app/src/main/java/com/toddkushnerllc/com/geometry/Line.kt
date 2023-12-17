package com.toddkushnerllc.geometry

class Line(val startX: Int, val startY: Int, val endX: Int, val endY: Int)

class Lines() {
    var list: List<Line> = emptyList()
    fun add(startX: Int, startY: Int, endX: Int, endY: Int) {
        list += Line(startX, startY, endX, endY)
    }
}
