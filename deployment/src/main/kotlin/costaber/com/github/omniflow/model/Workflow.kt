package costaber.com.github.omniflow.model

data class Workflow(
    val name: String,
    val description: String? = null,
    val steps: Collection<Step> = emptyList(),
    val result: String,
) : Node

//interface Decider {
//    fun decide(element: Element): Xpto
//}
//
//interface Visitor {
//    fun visit(element: Element)
//}
//
//interface Xpto {
//    fun test(clazz: Element): Boolean
//}
//
//class WorkflowCenas : Xpto {
//    override fun test(element: Element): Boolean {
//        return element is Workflow
//    }
//}
//
//class DefaultVisitor : Visitor {
//    override fun visit(workflow: Workflow): String {
//        return """
//            main:
//                params: [input]
//                steps:
//                    ${workflow.steps.map(::visit).joinToString { "\n" }}
//        """.trimIndent()
//    }
//
//    override fun visit(step: Step): String {
//        TODO("Not yet implemented")
//    }
//
//}
//
//fun xpto() {
//    val x: Workflow = null!!
//
//    val y: Visitor = null!!
//
//    y.visit(x)
//}