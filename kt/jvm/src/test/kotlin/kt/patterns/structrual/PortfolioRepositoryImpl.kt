package kt.patterns.structrual

import kotlinx.coroutines.delay
import kt.patterns.structural.composite.*

class PortfolioRepositoryImpl: PortfolioRepository {
    override suspend fun getUserPortfolio(userId: String): PortfolioComponent {
        delay(2000)

        return when(userId) {
            "1111" -> {
                getSinglePortfolio()
            }
            "2222" -> {
                getMultiplePortfolio()
            }
            else -> {
                getSinglePortfolio()
            }
        }
    }

    private fun getSinglePortfolio(): PortfolioComponent {
        val personalPortfolio = PortfolioLeaf(
            listOf(Owner("Jean", "Kasongo", 22.0)),
            "Personal Portfolio",
        )
        return PortfolioComposite(
            owners = listOf(Owner("Jean", "Kasongo", 44.0)),
            children = listOf(
                personalPortfolio
            )
        )
    }

    private fun getMultiplePortfolio(): PortfolioComponent {
        val personalPortfolio = PortfolioLeaf(
            listOf(Owner("Jean", "Kasongo", 20.0)),
            "Personal Portfolio",
        )

        val familyPortfolio = PortfolioLeaf(
            listOf(
                Owner("Jean", "Kasongo", 12.2),
                Owner("Judith", "Kasongo", 7.4),
                Owner("Martin", "Kasongo", 3.7)
            ),
            "Family Portfolio"
        )

        return PortfolioComposite(
            owners = listOf(Owner("Jean", "Kasongo", 45.1)),
            children = listOf(
                personalPortfolio,
                familyPortfolio
            )
        )
    }

}