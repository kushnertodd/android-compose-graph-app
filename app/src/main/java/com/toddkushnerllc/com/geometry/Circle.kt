package com.toddkushnerllc.geometry

open class Circle(val startX: Int, val startY: Int, val radius: Int)

class Circles() {
    var list: List<Circle> = emptyList()
    fun add(startX: Int, startY: Int, radius: Int) {
        list += Circle(startX, startY, radius)
    }
}
