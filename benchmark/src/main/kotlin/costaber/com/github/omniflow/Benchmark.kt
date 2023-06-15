package costaber.com.github.omniflow

import costaber.com.github.omniflow.analyzer.JobAnalyzer
import costaber.com.github.omniflow.cloud.provider.aws.renderer.AmazonRenderingContext
import costaber.com.github.omniflow.cloud.provider.aws.strategy.*
import costaber.com.github.omniflow.factory.DefaultNodeRendererStrategyDecider
import costaber.com.github.omniflow.generator.WorkflowGenerator
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.traversor.DepthFirstNodeVisitorTraversor
import costaber.com.github.omniflow.visitor.NodeContextVisitor

val numberOfSteps = listOf(1, 1, 2, 5, 10, 50, 100, 1000, 10_000, 100_000)

fun main(args: Array<String>) {
    numberOfSteps.forEach { size ->
        val workflow = WorkflowGenerator().generate(size)
        analyzeTranslation(workflow)

    }
}

private fun analyzeTranslation(workflow: Workflow) {
    val traversor = DepthFirstNodeVisitorTraversor()
    val contextVisitor = NodeContextVisitor(createNodeRendererStrategyDecider())
    val amazonRenderingContext = AmazonRenderingContext()
    val result = JobAnalyzer().analyze {
        traversor.traverse(contextVisitor, workflow, amazonRenderingContext)
    }
    println("Time Spent: ${result.timeSpent} ms - Memory Usage: ${result.memoryUsage} bytes")
}

private fun createNodeRendererStrategyDecider() =
    DefaultNodeRendererStrategyDecider.Builder()
        .addRendererStrategy(AmazonChoiceStrategyFactory())
        .addRendererStrategy(AmazonConditionStrategyFactory())
        .addRendererStrategy(AmazonEqualToExpressionStrategyFactory())
        .addRendererStrategy(AmazonGreaterThanExpressionStrategyFactory())
        .addRendererStrategy(AmazonGreaterThanOrEqualExpressionStrategyFactory())
        .addRendererStrategy(AmazonLessThanExpressionStrategyFactory())
        .addRendererStrategy(AmazonLessThanOrEqualExpressionStrategyFactory())
        .addRendererStrategy(AmazonPassStrategyFactory())
        .addRendererStrategy(AmazonStateMachineStrategyFactory())
        .addRendererStrategy(AmazonStateStrategyFactory())
        .addRendererStrategy(AmazonTaskStrategyFactory())
        .addRendererStrategy(AmazonVariableStrategyFactory())
        .build()