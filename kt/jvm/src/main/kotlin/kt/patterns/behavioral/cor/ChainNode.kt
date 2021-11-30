package kt.patterns.behavioral.cor


interface ChainNode {
    fun request(): ChainRequest

    fun proceed(request: ChainRequest): ChainResponse
}