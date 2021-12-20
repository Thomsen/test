package kt.patterns.structural.adapter

class Adapter : PowerTwoSupply {

    private lateinit var threeSupply: PowerThreeSupply

    private lateinit var pPositive: String
    private lateinit var pNegative: String

    constructor(supply: PowerThreeSupply) {
        this.threeSupply = supply
        setPositivePole(this.threeSupply.getPositivePole())
        setNegativePole(this.threeSupply.getNegativePole())
    }

    override fun getPositivePole(): String {
       return pPositive
    }

    override fun setPositivePole(positive: String) {
        pPositive = positive
    }

    override fun getNegativePole(): String {
        return pNegative
    }

    override fun setNegativePole(negative: String) {
        pNegative = negative
    }
}