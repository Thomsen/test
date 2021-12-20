package kt.patterns.structural.adapter

class BullPowerSupply : PowerThreeSupply {
    private lateinit var pPositive: String
    private lateinit var pNegative: String
    private lateinit var pGround: String

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

    override fun getGroundPole(): String {
        return pGround
    }

    override fun setGroundPole(ground: String) {
        pGround = ground
    }


}