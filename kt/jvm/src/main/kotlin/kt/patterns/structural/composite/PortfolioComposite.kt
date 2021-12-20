package kt.patterns.structural.composite

data class PortfolioComposite(
    private val owners: List<Owner>,
    val children: List<PortfolioComponent>
): PortfolioComponent {
    override fun getName(): String {
        return children.map {
            it.getName()
        }.toString()
    }

    override fun getOwners(): List<Owner> {
        return owners
    }

    override fun getValue(): Double {
        return children.map {
            it.getValue()
        }.sum()
    }
}