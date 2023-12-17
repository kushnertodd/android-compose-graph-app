package com.toddkushnerllc.trig

class Angle_factory {
    fun to_phi(theta: Angle): Angle = Angle(theta.sine, theta.cosine) // pi/2 - theta
    fun normal(theta: Angle): Angle = Angle(-theta.sine, theta.cosine) // theta + pi/2
}

class Angle {
    val cosine: Float
    val sine: Float

    constructor(cosine: Float, sine: Float) {
        this.cosine = cosine
        this.sine = sine
    }

    constructor(dx: Float, dy: Float, d: Float) {
        cosine = dy / d
        sine = dx / d
    }

    fun to_string() = "cosine $cosine sine $sine"
}