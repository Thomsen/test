package kt.patterns.structural.adapter

interface PowerTwoSupply {
    fun getPositivePole(): String
    fun setPositivePole(positive: String)

    fun getNegativePole(): String
    fun setNegativePole(negative: String)
}

interface PowerThreeSupply {
    fun getPositivePole(): String
    fun setPositivePole(positive: String)

    fun getNegativePole(): String
    fun setNegativePole(negative: String)

    fun getGroundPole(): String
    fun setGroundPole(ground: String)
}