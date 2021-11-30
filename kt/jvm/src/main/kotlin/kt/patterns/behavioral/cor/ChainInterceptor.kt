package kt.patterns.behavioral.cor

interface ChainInterceptor {

    fun call(chain: ChainNode): ChainResponse

}