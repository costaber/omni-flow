package costaber.com.github.omniflow.model.variable

sealed interface Term<T> {
    fun term(): T
}