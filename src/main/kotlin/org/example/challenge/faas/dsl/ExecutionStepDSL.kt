package org.example.challenge.faas.dsl

class ExecutionStepDSL : StepDSL {

    private lateinit var method: String

    fun method(method: String) {
        this.method = method
    }

    fun url(url: String) {

    }

    fun query(vararg queries: Pair<String, String>) {

    }

    fun header(vararg header: Pair<String, String>) {

    }

    fun result(variableName: String) {

    }

}

fun execution(
    init: ExecutionStepDSL.() -> Unit
): ExecutionStepDSL {
    return ExecutionStepDSL().apply(init)
}