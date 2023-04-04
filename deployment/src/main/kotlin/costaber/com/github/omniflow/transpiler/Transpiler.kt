package costaber.com.github.omniflow.transpiler

interface Transpiler<Deployable> {

    fun transform(deployable: Deployable): String

}