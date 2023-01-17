package org.example.challenge.faas.transpiler

interface Transpiler<Deployable> {

    fun transform(deployable: Deployable): String

}