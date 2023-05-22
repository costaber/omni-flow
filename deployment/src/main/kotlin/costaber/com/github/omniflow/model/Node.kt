package costaber.com.github.omniflow.model

interface Node {
    fun childNodes(): List<Node>

    fun childNodeSize(): Int

    fun childNode(index: Int): Node?

}