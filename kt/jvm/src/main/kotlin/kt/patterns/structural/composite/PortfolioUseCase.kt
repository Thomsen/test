package kt.patterns.structural.composite

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PortfolioRepository {
    suspend fun getUserPortfolio(userId: String): PortfolioComponent
}

class PortfolioUseCase(private val repository: PortfolioRepository) {

    suspend fun execute(userId: String): PortfolioComponent {
        return withContext(Dispatchers.IO) {
            repository.getUserPortfolio(userId)
        }
    }
}