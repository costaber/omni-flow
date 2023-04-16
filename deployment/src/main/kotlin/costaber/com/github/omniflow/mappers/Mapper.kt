package costaber.com.github.omniflow.mappers

interface Mapper<T, R> {
    fun map(t: T): R
}