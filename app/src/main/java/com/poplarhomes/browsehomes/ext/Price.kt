package com.poplarhomes.browsehomes.ext

val Double.price: String
    get() {
        return if (this % 1 == 0.0) "$%,.0f".format(this)
        else "$%,.1f".format(this)
    }
