package kt.patterns.structural.composite

data class PortfolioLeaf(
    private val owners: List<Owner>,
    private val name: String,
): PortfolioComponent {
    override fun getName(): String {
        return name
    }

    override fun getOwners(): List<Owner> {
        return owners
    }

    override fun getValue(): Double {
        return owners.map {
            it.age
        }.sum()
    }
}