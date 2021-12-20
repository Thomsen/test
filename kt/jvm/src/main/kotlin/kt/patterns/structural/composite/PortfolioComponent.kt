package kt.patterns.structural.composite

data class Owner(val name: String, val lastName: String, val age: Double)

interface PortfolioComponent {

    fun getName(): String

    fun getOwners(): List<Owner>

    fun getValue(): Double
}