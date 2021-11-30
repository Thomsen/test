package kt.patterns.behavioral.cor


class ChainCall(
    private val index: Int = 0,
    private val request: ChainRequest,
    private val interceptors: ArrayList<ChainInterceptor> = arrayListOf()
): ChainNode {

    fun addInterceptor(ci: ChainInterceptor) {
        interceptors.add(ci)
    }

    private fun copy(index: Int, request: ChainRequest,
                     interceptors: List<ChainInterceptor>): ChainCall {
        val array = arrayListOf<ChainInterceptor>()
        array.addAll(interceptors)
        return ChainCall(index, request, array)
    }

    fun call() {
        proceed(request)
    }

    override fun request(): ChainRequest {
        return request
    }

    override fun proceed(request: ChainRequest): ChainResponse {
        check(index < interceptors.size) { println("chain all execute end") }

        println("chain execute start")

        val interceptor = interceptors.getOrNull(index) ?: throw NullPointerException("interceptor is null")
        val next = copy(index + 1, request, interceptors)

        val response = interceptor.call(next)

        // don't print when check
        println("chain execute end")

        return response
    }
}