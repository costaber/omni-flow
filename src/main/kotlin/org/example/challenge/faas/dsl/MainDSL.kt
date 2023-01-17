package org.example.challenge.faas.dsl

class MainDSL {
}

fun scope(init: MainDSL.() -> Unit): MainDSL {
    return MainDSL().apply(init)
}