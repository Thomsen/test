package kt.patterns.behavioral

import kt.patterns.behavioral.cor.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith


class TestChainCall {

    lateinit var chainCall: ChainCall

    lateinit var request: ChainRequest

    @Before
    fun init() {
        request = ChainRequest(message = "init")
        chainCall = ChainCall(0, request)
    }

    @Test
    fun testCall() {
        chainCall.addInterceptor(OneChainInterceptor())
        chainCall.addInterceptor(TwoChainInterceptor())

        assertFailsWith(IllegalStateException::class) {
            chainCall.call()
        }
    }

    @After
    fun close() {

    }
}

class OneChainInterceptor: ChainInterceptor {
    override fun call(chain: ChainNode): ChainResponse {
        println("chain one")
        return chain.proceed(chain.request())
    }
}

class TwoChainInterceptor: ChainInterceptor {
    override fun call(chain: ChainNode): ChainResponse {
        println("chain two")
        return chain.proceed(chain.request())
    }
}