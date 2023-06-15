package costaber.com.github.omniflow.analyzer

class JobAnalyzer {

    fun <T> analyze(task: () -> T): AnalysisResult<T> {
        // Snapshot before the task
        val runtime = Runtime.getRuntime()
        System.gc()
        val memoryBeforeTask = runtime.totalMemory() - runtime.freeMemory()
        val timeBeforeTask = System.currentTimeMillis()
        // Task to be measured
        // Store result to bot be collected during GC run
        val result = task()
        // Snapshot after the task
        System.gc()
        val memoryAfterTask = runtime.totalMemory() - runtime.freeMemory()
        val timeAfterTask = System.currentTimeMillis()
        return AnalysisResult(
            timeSpent = timeAfterTask - timeBeforeTask,
            memoryUsage = memoryAfterTask - memoryBeforeTask,
            result = result
        )
    }

}