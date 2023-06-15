package costaber.com.github.omniflow.analyzer

data class AnalysisResult<T>(
    val result: T,
    val timeSpent: Long,
    val memoryUsage: Long,
)
