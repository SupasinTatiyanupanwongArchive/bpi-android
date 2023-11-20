package dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.usecases

import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.domain.PricesRepositoryContract

class FetchCurrentPriceUseCase(
    private val pricesRepositoryContract: PricesRepositoryContract
) {

    suspend operator fun invoke() = pricesRepositoryContract.fetchCurrentPrice()

}
