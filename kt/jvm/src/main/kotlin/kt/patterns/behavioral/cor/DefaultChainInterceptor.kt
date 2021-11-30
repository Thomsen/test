package kt.patterns.behavioral.cor


class DefaultChainInterceptor: ChainInterceptor {

    override fun call(chain: ChainNode): ChainResponse {
        println("execute default")
        return chain.proceed(chain.request())
    }
}