package com.toddkushnerllc.trig

import kotlin.math.abs

class Line_polar_factory {
    fun normal_line(theta: Angle, r: Float, x: Float, y: Float): Line_polar {
        val angle_factory = Angle_factory()
        val normal_angle = angle_factory.normal(theta)
        val r_normal = x * normal_angle.cosine + y * normal_angle.sine
        return Line_polar(normal_angle, r_normal)
    }

    fun parallel_line(theta: Angle, r: Float, x: Float, y: Float): Line_polar {
        val r = x * theta.cosine + y * theta.sine
        return Line_polar(theta, r)
    }
}

class Line_polar {
    val theta: Angle
    val r: Float

    constructor(theta: Angle, r: Float) {
        this.theta = theta
        this.r = r
    }

    fun distance_to_line(x0: Float, y0: Float): Float {
        val r0 = x0 * theta.cosine + y0 * theta.sine
        return abs(r - r0)
    }

    fun to_string(): String = "theta ${theta.to_string()} r $r"
}
