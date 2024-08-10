package org.example

class Node(var value: Int) {
    var previous: Node? = null
    var next: Node? = null
}

class Tree {
    var first: Node? = null

    private fun addFirst(value: Int) {
        first = Node(value)
    }

    private fun add2Left(node: Node?, value: Int) {
        node?.let { t ->
            t.previous?.let { previous ->
                if (previous!!.value > value) {
                    /***
                     * Caso exista no a ESQUERDA
                     * e valor recebido seja MAIOR
                     * adiciona a ESQUERDA do no
                     */
                    add2Left(previous, value)
                } else {
                    /***
                     * Caso exista no a ESQUERDA
                     * e valor recebido seja MENOR
                     * adiciona a DIREITA do no
                     */
                    add2Right(previous, value)
                }
            } ?: run {
                /**
                 * Caso nao exista no anterior cria novo
                 */
                t.previous = Node(value)
            }
        }
    }

    private fun add2Right(node: Node?, value: Int) {
        node?.let { t ->
            t.next?.let { next ->
                if (next!!.value > value) {
                    /***
                     * Caso exista no a DIREITA
                     * e valor recebido seja MAIOR
                     * adiciona a DIREITA do no
                     */
                    add2Left(next, value)
                } else {
                    /***
                     * Caso exista no a DIREITA
                     * e valor recebido seja MENOR
                     * adiciona a DIREITA do no
                     */
                    add2Right(next, value)
                }
            } ?: run {
                /**
                 * Caso nao exista no seguinte cria
                 */
                t.next = Node(value)
            }
        }
    }

    fun add(value: Int) {
        if (first == null) {
            addFirst(value)
            return
        }

        if (first?.value == value) {
            return
        }

        val firstNode: Node? = first

        firstNode?.value?.let {
            if (it > value) {
                add2Left(firstNode, value)
            } else {
                add2Right(firstNode, value)
            }
        }
    }

    private fun printNodeRecursively(node: Node?) {
        node?.let {
            if (it.previous != null) {
                printNodeRecursively(it.previous)
            }

            println(it.value)

            if (it.next != null) {
                printNodeRecursively(it.next)
            }
        }
    }

    fun show() {
        printNodeRecursively(first)
    }
}

fun main() {
    var tree = Tree()

    tree.add(2)
    tree.add(3)
    tree.add(1)

    tree.add(17)
    tree.add(12)
    tree.add(6)

    tree.show()
}