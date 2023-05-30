package costaber.com.github.omniflow.model.variable

data class Variable(
    val name: String
) : Term<String> {

    override fun term() = name
}