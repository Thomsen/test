package kt.patterns.behavioral

import kt.patterns.behavioral.cor.ChainCall
import kt.patterns.behavioral.cor.ChainRequest

fun main() {
    val request = ChainRequest(message = "init")
    val chainCall = ChainCall(0, request)

    chainCall.call()
}